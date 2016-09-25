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
/*
package com.mcgoodtime.productionline.common.blocks;

import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.core.GuiHandler;
import com.mcgoodtime.productionline.common.init.PLBlocks;
import com.mcgoodtime.productionline.common.items.ItemBlockPL;
import com.mcgoodtime.productionline.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.productionline.common.tiles.TilePL;
import com.mcgoodtime.productionline.common.tiles.TileHeatDryer;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import ic2.core.block.BlockTextureStitched;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mcgoodtime.productionline.common.core.ProductionLine.MOD_NAME;
import static com.mcgoodtime.productionline.common.core.ProductionLine.RESOURCE_DOMAIN;

/**
 * Created by BestOwl on 2015.11.22.0022.
 *
 * @author BestOwl
 * @since 0.2
 *//*
public class BlockMachine extends BlockContainerPL implements IMultiMetaBlock {
    private static List<String> internalNameList = new ArrayList<String>();
    public IIcon textures[][];

    static {
        internalNameList.add("CarbonizeFurnace");
        internalNameList.add("HeatDryer");
    }

    public BlockMachine() {
        super(Material.iron, "BlockMachine");
        this.setHardness(2.0F);
        for (int i = 0; i < this.getMaxMeta(); i++) {
            GameRegistry.registerTileEntity(this.getTileEntityClass(i), internalNameList.get(i));
        }

        PLBlocks.carbonizeFurnace = new ItemStack(this, 1, 0);
        PLBlocks.heatDryer = new ItemStack(this, 1, 1);
    }

    @Override
    public Class<? extends ItemBlock> getItemBlockClass() {
        return ItemBlockPL.class;
    }

    /**
     * Called upon block activation (right click on the block.)
     *//*
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f1, float f2, float f3) {
        if (!world.isRemote) {
            GuiHandler.EnumGui gui = this.getGui(world.getBlockMetadata(x, y, z));
            if (gui != null) {
                player.openGui(ProductionLine.instance, gui.ordinal(), world, x, y, z);
            }
        } else {
            player.isInvisibleToPlayer(player);
        }
        return true;
    }

    private GuiHandler.EnumGui getGui(int meta) {
        switch (meta) {
            case 0: return GuiHandler.EnumGui.CarbonizeFurnace;
            case 1: return GuiHandler.EnumGui.HeatDryer;
            default: return null;
        }
    }

    @Override
    protected Class<? extends TilePL> getTileEntityClass(int meta) {
        switch (meta) {
            case 0: return TileCarbonizeFurnace.class;
            case 1: return TileHeatDryer.class;
            default: return null;
        }
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        switch (world.getBlockMetadata(x, y, z)) {
            case 0:
                if ( ((TilePL)world.getTileEntity(x, y, z)).active) {
                    float f2;
                    float fmod;
                    float f1mod;
                    float f2mod;

                    float f = (float)x + 1.0F;
                    float f1 = (float)y + 1.0F;
                    f2 = (float)z + 1.0F;

                    for(int i = 0; i < 4; ++i) {
                        fmod = -0.2F - random.nextFloat() * 0.6F;
                        f1mod = -0.1F + random.nextFloat() * 0.2F;
                        f2mod = -0.2F - random.nextFloat() * 0.6F;
                        world.spawnParticle("smoke", (double)(f + fmod), (double)(f1 + f1mod), (double)(f2 + f2mod), 0.0D, 0.0D, 0.0D);
                    }
                }
        }
    }

    //-----------------------
    //-----------------------

    /**
     * World only
     *//*
    @Override
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        if (iBlockAccess.getTileEntity(x, y, z) instanceof TilePL) {
            int facing = ((TilePL) iBlockAccess.getTileEntity(x, y, z)).facing;
            boolean isBurn = ((TilePL) iBlockAccess.getTileEntity(x, y, z)).active;

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
     *//*
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

    @Override
    public int getMaxMeta() {
        return internalNameList.size();
    }

    /**
     * Returns the unlocalized name of this block. This version accepts an ItemStack so different stacks can have
     * different names based on their meta or NBT.
     *//*
    public String getBlockName(ItemStack itemStack) {
        return "tile." + MOD_NAME + ".block." + this.getInternalName(itemStack.getItemDamage());
    }

    /**
     * Get block's unlocalized name
     * @return unlocalized name
     *//*
    @Override
    public String getBlockName(int meta) {
        return "tile." + MOD_NAME + ".block." + this.getInternalName(meta);
    }

    @Override
    public String getInternalName(int meta) {
        return internalNameList.get(meta);
    }
}
*/