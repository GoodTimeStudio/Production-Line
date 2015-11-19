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

import static com.mcgoodtime.gti.common.core.Gti.creativeTabGti;

import com.mcgoodtime.gti.common.core.Gti;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

/**
 * Yeah the water hyacinth block... For fun.
 *
 * @author liach
 */
public class BlockWaterHyacinth extends BlockBush {

    public BlockWaterHyacinth() {
        super(Material.plants);
        this.setBlockName("gti.block.WaterHyacinth");
        this.setCreativeTab(creativeTabGti);
        this.setBlockTextureName(Gti.RESOURCE_DOMAIN + ":" + "BlockWaterHyacinth");
        this.setHardness(0.0F);
        this.setResistance(0.0F);
        this.setStepSound(soundTypeGrass);
        this.setTickRandomly(true);
        float f = 0.5F;
        float f1 = 0.015625F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
    }

    @Override
    public int getRenderType() {
        return 23;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random rand) {
        int changedX = rand.nextInt(5) - 3 + x;
        int changedZ = rand.nextInt(5) - 3 + z;
        if ((world.isAirBlock(changedX, y, changedZ)) && (this.canPlaceBlockOn(world.getBlock(changedX, y - 1, changedZ)))) {
            world.setBlock(changedX, y, changedZ, this);
        }
    }

    @Override
    protected boolean canPlaceBlockOn(Block placedOn) {
        return placedOn == Blocks.water;
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

    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return 2129968;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int p_149741_1_) {
        return 2129968;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess iBlockAccess, int x, int y, int z) {
        return 2129968;
    }
}
