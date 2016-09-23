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

import com.mcgoodtime.productionline.common.init.PLBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.util.BlockSnapshot;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.event.ForgeEventFactory;

/**
 * The item of water hyacinth in GoodTime-Industrial.
 *
 * @author liach
 */
public class ItemWaterHyacinth extends ItemColored {
    public ItemWaterHyacinth(Block block) {
        super(block, false);
        this.setUnlocalizedName("productionline.item.WaterHyacinth");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemStack, int damage) {
        return PLBlocks.waterHyacinth.getRenderColor(itemStack.getItemDamage());
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, player, true);
        if (movingobjectposition == null) {
            return itemStack;
        } else {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                int i = movingobjectposition.blockX;
                int j = movingobjectposition.blockY;
                int k = movingobjectposition.blockZ;

                if (!world.canMineBlock(player, i, j, k)) {
                    return itemStack;
                }

                if (!player.canPlayerEdit(i, j, k, movingobjectposition.sideHit, itemStack)) {
                    return itemStack;
                }

                if (world.getBlock(i, j, k).getMaterial() == Material.water && world.getBlockMetadata(i, j, k) == 0
                        && world.isAirBlock(i, j + 1, k)) {
                    // special case for handling block placement with water lilies, moved to water hyacinth
                    BlockSnapshot blocksnapshot = BlockSnapshot.getBlockSnapshot(world, i, j + 1, k);
                    world.setBlock(i, j + 1, k, PLBlocks.waterHyacinth);
                    if (ForgeEventFactory.onPlayerBlockPlace(player, blocksnapshot, ForgeDirection.UP).isCanceled()) {
                        blocksnapshot.restore(true, false);
                        return itemStack;
                    }

                    if (!player.capabilities.isCreativeMode) {
                        itemStack.stackSize -- ;
                    }
                }
            }
            return itemStack;
        }
    }
}
