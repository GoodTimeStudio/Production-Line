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
package com.mcgoodtime.productionline.common.inventory;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

import javax.annotation.Nullable;

/**
 * Inventory utilities.
 */
public final class Inventories {

    public static final Item AIR = Item.getItemFromBlock(Blocks.AIR);
    private static final Random RANDOM = new Random();

    public static void spill(World world, double x, double y, double z, IInventory inventory) {
        for (int i = 0; i < inventory.getSizeInventory(); i++) {
            spill(world, x, y, z, inventory.removeStackFromSlot(i));
        }
    }

    public static void spill(World world, BlockPos pos, IInventory inventory) {
        spill(world, pos.getX(), pos.getY(), pos.getZ(), inventory);
    }

    public static void spill(World world, double x, double y, double z, @Nullable ItemStack stack) {
        if (stack == null)
            return;
        float f = RANDOM.nextFloat() * 0.8F + 0.1F;
        float f1 = RANDOM.nextFloat() * 0.8F + 0.1F;
        float f2 = RANDOM.nextFloat() * 0.8F + 0.1F;

        while (stack.stackSize > 0) {
            int j1 = RANDOM.nextInt(21) + 10;

            if (j1 > stack.stackSize) {
                j1 = stack.stackSize;
            }

            stack.stackSize -= j1;
            EntityItem entityitem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(stack.getItem(), j1, stack.getItemDamage()));

            if (stack.hasTagCompound()) {
                entityitem.getEntityItem().setTagCompound(stack.getTagCompound().copy());
            }

            float f3 = 0.05F;
            entityitem.motionX = (double) ((float) RANDOM.nextGaussian() * f3);
            entityitem.motionY = (double) ((float) RANDOM.nextGaussian() * f3 + 0.2F);
            entityitem.motionZ = (double) ((float) RANDOM.nextGaussian() * f3);
            world.spawnEntity(entityitem);
        }
    }

    public static void spill(World world, BlockPos pos, @Nullable ItemStack stack) {
        spill(world, pos.getX(), pos.getY(), pos.getZ(), stack);
    }

    private Inventories() {
    }
}
