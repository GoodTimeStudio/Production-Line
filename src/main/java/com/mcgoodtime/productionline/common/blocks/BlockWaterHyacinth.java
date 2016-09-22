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

import static com.mcgoodtime.productionline.common.core.ProductionLine.creativeTabGti;

import com.google.common.collect.Lists;
import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.init.PLItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Yeah the water hyacinth block... For fun.
 *
 * @author liach
 */
public class BlockWaterHyacinth extends BlockBush implements IGrowable {

    private static final int MAX_GROWTH = 6;

    public BlockWaterHyacinth() {
        super(Material.plants);
        this.setBlockName("productionline.block.WaterHyacinth");
        this.setCreativeTab(creativeTabGti);
        this.setBlockTextureName(ProductionLine.RESOURCE_DOMAIN + ":" + "BlockWaterHyacinth");
        this.setHardness(0.0F);
        this.setResistance(0.0F);
        this.setStepSound(soundTypeGrass);
        this.setTickRandomly(true);
        float f = 0.5F;
        float f1 = 0.015625F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }

    @Override public int quantityDropped(Random p_149745_1_) {
        return 0;
    }

    @Override
    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return null;
    }

    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        int amount = (world.getBlockMetadata(x, y, z) == MAX_GROWTH ? (int) (Math.random() * 5) : 0) + 1;
        ArrayList<ItemStack> ret = Lists.newArrayList();
        ret.add(new ItemStack(PLItems.waterHyacinth, amount));
        return ret;
    }

    @Override
    public int getRenderType() {
        return 23;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        int t = world.getBlockMetadata(x, y, z);
        if (t < MAX_GROWTH) {
            if (rand.nextDouble() < 0.2D) {
                world.setBlockMetadataWithNotify(x, y, z, t + 1, 3);
            }
            return;
        }
        int changedX = rand.nextInt(5) - 3 + x;
        int changedZ = rand.nextInt(5) - 3 + z;
        for (int y0 = y - 1; y0 <= y + 1; y0++) {
            if ((world.isAirBlock(changedX, y0, changedZ)) && (canBlockStay(world, changedX, y0, changedZ))) {
                world.setBlock(changedX, y0, changedZ, this);
            }
        }
    }

    @Override
    protected boolean canPlaceBlockOn(Block placedOn) {
        return placedOn.getMaterial() == Material.water;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return y >= 0 && y < 256 && world.getBlock(x, y - 1, z).getMaterial() == Material.water
                && world.getBlockMetadata(x, y - 1, z) == 0;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ,
                (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z,
                                        AxisAlignedBB alignedBB, List list, Entity entity) {
        if (entity == null || !(entity instanceof EntityBoat)) {
            super.addCollisionBoxesToList(world, x, y,
                    z, alignedBB, list, entity);
        }
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Water;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (!world.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
            return;
        }
        if (!world.isRemote && entity instanceof EntityLivingBase) {
            int meta = world.getBlockMetadata(x, y, z);
            if (Math.random() < 0.6D) {
                if (meta > 0) {
                    world.setBlockMetadataWithNotify(x, y, z, meta - 1, 3);
                } else {
                    world.setBlockToAir(x, y, z);
                    for (ItemStack drop : getDrops(world, x, y, z, 1, 0)) {
                        dropBlockAsItem(world, x, y, z, drop);
                    }
                }
            }

        }
    }

    //canUseBoneMeal
    @Override
    public boolean func_149851_a(World world, int x, int y, int z, boolean client) {
        if (client) {
            return false;
        }
        int meta = world.getBlockMetadata(x, y, z);
        return meta < MAX_GROWTH;
    }

    //willBoneMealBeSuccessful
    @Override
    public boolean func_149852_a(World world, Random random, int x, int y, int z) {
        return world.getBlockMetadata(x, y, z) < MAX_GROWTH;
    }

    //grow
    @Override
    public void func_149853_b(World world, Random random, int x, int y, int z) {
        world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 1, 3);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return 2129968;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_) {
        return 2129968;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess iBlockAccess, int x, int y, int z) {
        return 2129968;
    }
}
