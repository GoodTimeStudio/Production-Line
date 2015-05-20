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

import com.mcgoodtime.gti.common.items.ItemWaterHyacinth;
import cpw.mods.fml.common.registry.GameRegistry;
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
        GameRegistry.registerBlock(this, ItemWaterHyacinth.class, "WaterHyacinth");
    }

    public int getRenderType() {
        return 23;
    }

    public void updateTick(World world, int x, int y, int z, Random rand) {
        int changedX = rand.nextInt(5) - 3 + x;
        int changedZ = rand.nextInt(5) - 3 + z;
        if ((world.isAirBlock(changedX, y, changedZ)) && (canPlaceBlockOn(world.getBlock(changedX, y - 1, changedZ)))) {
            world.setBlock(x + changedX, y, z + changedZ, this);
        }
    }

    protected boolean canPlaceBlockOn(Block placedOn) {
        return placedOn == Blocks.water;
    }

    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        return y >= 0 && y < 256 ? world.getBlock(x, y - 1, z).getMaterial() == Material.water
                && world.getBlockMetadata(x, y - 1, z) == 0 : false;
    }
    
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return AxisAlignedBB.getBoundingBox((double)x + this.minX, (double)y + this.minY, (double)z + this.minZ,
                (double)x + this.maxX, (double)y + this.maxY, (double)z + this.maxZ);
    }

    public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_,
                                        AxisAlignedBB p_149743_5_, List p_149743_6_, Entity p_149743_7_) {
        if (p_149743_7_ == null || !(p_149743_7_ instanceof EntityBoat)) {
            super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_,
                    p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
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
    public int colorMultiplier(IBlockAccess p_149720_1_, int p_149720_2_, int p_149720_3_, int p_149720_4_) {
        return 2129968;
    }
}
