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
package com.mcgoodtime.productionline.common.blocks;

import com.mcgoodtime.productionline.common.core.PLConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.MovingObjectPositionsition;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import java.util.List;
import java.util.Random;

import static com.mcgoodtime.productionline.common.core.ProductionLine.*;

/**
 * Created by suhao on 2015-6-10-0010.
 *
 * @author suhao
 */
public class BlockPL extends Block {
    public String internalName;

    public BlockPL(Material material, String name, float hardness, float resistance,
                   String harvestLevelToolClass, int harvestLevel) {
        this(material, name);
        this.setHardness(hardness);
        this.setResistance(resistance);
        this.setHarvestLevel(harvestLevelToolClass, harvestLevel);
    }

    public BlockPL(Material material, String name) {
        super(material);
        this.setUnlocalizedName(MOD_ID + ".block." + name);
        //this.setBlockTextureName(RESOURCE_DOMAIN + ":" + "block" + name);
        this.setCreativeTab(creativeTabGti);
        this.internalName = name;
        // TODO register after construction
        PLConfig.gtiLogger.log(Level.INFO, name + Integer.toString(Block.getIdFromBlock(this)));
    }

    /**
     * Called when a user uses the creative pick block button on this block
     *
     * @param target The full target the player is looking at
     * @param player @return A ItemStack to add to the player's inventory, Null if nothing should be added.
     */
    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
        if (this instanceof IMultiMetaBlock) {
            Block block = world.getBlockState(pos).getBlock();
            return new ItemStack(this, 1, block.getMetaFromState(world.getBlockState(pos)));
        }
        return super.getPickBlock(state, target, world, pos, player);
    }

    /**
     * This returns a complete list of items dropped from this block.
     *
     * @param world   The current world
     * @param pos     Block position in world
     * @param state   Current state
     * @param fortune Breakers fortune level
     * @return A ArrayList containing all items this block drops
     */
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        return super.getDrops(world, pos, state, fortune);
    }

    /**
     * Get the Item that this Block should drop when harvested.
     *
     */
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return super.getItemDropped(state, rand, fortune);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        if (this instanceof IMultiMetaBlock) {
            for(int meta = 0; meta < ((IMultiMetaBlock) this).getMaxMeta(); ++meta) {
                ItemStack stack = new ItemStack(this, 1, meta);
                list.add(stack);
            }
        } else {
            super.getSubBlocks(item, creativeTabs, list);
        }
    }

    public Class<? extends ItemBlock> getItemBlockClass() {
        return ItemBlock.class;
    }
}
