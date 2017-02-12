/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mcgoodtime.productionline.common.items;

import com.mcgoodtime.productionline.common.blocks.IBlockType;
import com.mcgoodtime.productionline.common.blocks.IMultiIDBlock;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

import static com.mcgoodtime.productionline.common.core.ProductionLine.MOD_ID;

/**
 * Created by BestOwl on 2015.11.22.0022.
 *
 * @author BestOwl
 * @since 0.2
 */
public class ItemBlockPL extends ItemBlock {

    public ItemBlockPL(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.hasSubtypes = true;
    }

    /**
     * Returns the unlocalized name of this item. This version accepts an ItemStack so different stacks can have
     * different names based on their damage or NBT.
     *
     */
    @Override
    public String getUnlocalizedName(ItemStack stack) {
        String internal = null;
        if (this.block instanceof IMultiIDBlock) {
            Object objects[] = ((IMultiIDBlock) this.block).getBlockTypeContainer().getAllowedValues().toArray();
            if (stack.getItemDamage() < objects.length) {
                Object obj = objects[stack.getItemDamage()];
                if (obj instanceof IBlockType) {
                    internal = ((IBlockType) obj).getTypeName();
                }
            }
        }

        if (internal != null) {
            return "tile." + MOD_ID + ".block." + internal;
        }
        else {
            return super.getUnlocalizedName(stack);
        }
    }

    /**
     * Returns the metadata of the block which this Item (ItemBlock) can place
     */
    @Override
    public int getMetadata(int meta)
    {
        return meta;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
        if (this.block instanceof IMultiIDBlock) {
            for (int i = 0; i < ((IMultiIDBlock) this.block).getBlockTypeContainer().getAllowedValues().size(); i++) {
                list.add(new ItemStack(this, 1, i));
            }
        }
    }

}
