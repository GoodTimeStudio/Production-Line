/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
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

import static com.mcgoodtime.gti.common.core.CreativeTabGti.creativeTabGti;

import com.mcgoodtime.gti.common.core.Gti;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLilyPad;
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
 * @author liach
 */
public class BlockWaterHyacinth extends BlockLilyPad {

    public BlockWaterHyacinth() {
        super();
        float var1 = 0.5F;
        float var2 = 0.015625F;
        this.setBlockName("gti.WaterHyacinth");
        this.setBlockBounds(0.5F - var1, 0.0F, 0.5F - var1, 0.5F + var1, var2, 0.5F + var1);
        this.setCreativeTab(creativeTabGti);
        this.setBlockTextureName(Gti.RESOURCE_DOMAIN + ":" + "BlockWaterHyacinth");
        this.setTickRandomly(true);
        GameRegistry.registerBlock(this, "gti.WaterHyacinth");
    }

    public void updateTick(World world, int x, int y, int z, Random rand) {
        int changedX = rand.nextInt(5) - 3;
        int changedZ = rand.nextInt(5) - 3;
        if (world.getBlock(x + changedX, y - 1, z + changedZ) == Blocks.water) {
            world.setBlock(x + changedX, y, z + changedZ, this);
        }
    }

    protected boolean canPlaceBlockOn(Block block) {
        return block == Blocks.water;
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor() {
        return 2129968;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(int renderColor) {
        return 2129968;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
        return 2129968;
    }

    public boolean canBlockStay(World world, int x, int y, int z) {
        return y >= 0 && y < 256 ? world.getBlock(x, y - 1, z).getMaterial() == Material.water && world.getBlockMetadata(x, y - 1, z) == 0 : false;
    }

    public int getRenderType() {
        return 23;
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List intersectList, Entity collisionEntity) {
        if (collisionEntity == null || !(collisionEntity instanceof EntityBoat)) {
            super.addCollisionBoxesToList(world, x, y, z, aabb, intersectList, collisionEntity);
        }

    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_getCollisionBoundingBoxFromPool_1_, int p_getCollisionBoundingBoxFromPool_2_, int p_getCollisionBoundingBoxFromPool_3_, int p_getCollisionBoundingBoxFromPool_4_) {
        return AxisAlignedBB.getBoundingBox((double)p_getCollisionBoundingBoxFromPool_2_ + this.minX, (double)p_getCollisionBoundingBoxFromPool_3_ + this.minY, (double)p_getCollisionBoundingBoxFromPool_4_ + this.minZ, (double)p_getCollisionBoundingBoxFromPool_2_ + this.maxX, (double)p_getCollisionBoundingBoxFromPool_3_ + this.maxY, (double)p_getCollisionBoundingBoxFromPool_4_ + this.maxZ);
    }

}
