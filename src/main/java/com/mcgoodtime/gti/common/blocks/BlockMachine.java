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
package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.init.GtiBlocks;
import com.mcgoodtime.gti.common.items.ItemBlockGti;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.gti.common.tiles.TileGti;
import com.mcgoodtime.gti.common.tiles.TileHeatDryer;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import ic2.core.block.BlockTextureStitched;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mcgoodtime.gti.common.core.Gti.MOD_ID;
import static com.mcgoodtime.gti.common.core.Gti.RESOURCE_DOMAIN;

/**
 * Created by BestOwl on 2015.11.22.0022.
 *
 * @author BestOwl
 * @since 0.2
 */
public class BlockMachine extends BlockContainerGti implements IMultiMetaBlock {
    private static List<String> internalNameList = new ArrayList<String>();
    public IIcon textures[][];

    static {
        internalNameList.add("CarbonizeFurnace");
        internalNameList.add("HeatDryer");
    }

    public BlockMachine() {
        super(Material.iron, "BlockMachine");
        GameRegistry.registerBlock(this, ItemBlockGti.class, this.blockName);
        for (int i = 0; i < this.getMaxMeta(); i++) {
            GameRegistry.registerTileEntity(this.getTileEntityClass(i), internalNameList.get(i));
        }

        GtiBlocks.carbonizeFurnace = new ItemStack(this, 1, 0);
        GtiBlocks.heatDryer = new ItemStack(this, 1, 1);
    }

    @Override
    protected Class<? extends TileGti> getTileEntityClass(int meta) {
        switch (meta) {
            case 0: return TileCarbonizeFurnace.class;
            case 1: return TileHeatDryer.class;
            default: return null;
        }
    }

    /**
     * World only
     */
    @Override
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        if (iBlockAccess.getTileEntity(x, y, z) instanceof TileGti) {
            int facing = ((TileGti) iBlockAccess.getTileEntity(x, y, z)).facing;
            boolean isBurn = ((TileGti) iBlockAccess.getTileEntity(x, y, z)).active;

            int i = DIRECTION[facing][side];
            if (isBurn) {
                i += 6;
            }

            return textures[iBlockAccess.getBlockMetadata(x, y, z)][i];
        }
        return null;
    }

    /**
     * Hand only
     * side:
     * 1:top  5:east  3:south
     * 0:low  4:west  2:north
     *
     */
    @Override
    public IIcon getIcon(int side, int meta) {
        return this.textures[meta][DIRECTION[3][side]];
    }

    @Override
    public void registerBlockIcons(IIconRegister iir) {
        this.textures = new IIcon[this.getMaxMeta()][12];

        for (int meta = 0; meta < this.getMaxMeta(); meta++) {
            for(int burn = 0; burn < 2; ++burn) {
                for(int side = 0; side < 6; ++side) {
                    int subIndex = burn * 6 + side;
                    String name = RESOURCE_DOMAIN + ":" + "block" + this.getInternalName(meta) + ":" + subIndex;
                    BlockTextureStitched texture = new BlockTextureStitched(name, subIndex);
                    this.textures[meta][subIndex] = texture;
                    ((TextureMap)iir).setTextureEntry(name, texture);
                }
            }
        }
    }

    @Override
    public Item getItemDropped(int var1, Random random, int var2) {
        return IC2Items.getItem("machine").getItem();
    }

    public int getMaxMeta() {
        return internalNameList.size();
    }

    /**
     * Returns the unlocalized name of this block. This version accepts an ItemStack so different stacks can have
     * different names based on their meta or NBT.
     */
    public String getBlockName(ItemStack itemStack) {
        return "tile." + MOD_ID + ".block." + this.getInternalName(itemStack.getItemDamage());
    }

    /**
     * Get block's unlocalized name
     * @return unlocalized name
     */
    @Override
    public String getBlockName(int meta) {
        return "tile." + MOD_ID + ".block." + this.getInternalName(meta);
    }

    public String getInternalName(int meta) {
        return internalNameList.get(meta);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void getSubBlocks(Item item, CreativeTabs creativeTabs, List list) {
        for(int meta = 0; meta < this.getMaxMeta(); ++meta) {
            ItemStack stack = new ItemStack(this, 1, meta);
            list.add(stack);
        }
    }
}
