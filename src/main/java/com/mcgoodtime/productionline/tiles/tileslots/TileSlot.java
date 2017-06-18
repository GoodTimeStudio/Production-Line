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
package com.mcgoodtime.productionline.tiles.tileslots;

import com.mcgoodtime.productionline.tiles.TileContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by BestOwl on 2015.11.9.0009.
 *
 * @author BestOwl
 */
public class TileSlot {

    public final SlotMode slotMode;

    public final TileContainer tile;
    protected ItemStack item;

    public TileSlot(TileContainer tile, SlotMode mode) {
        this.tile = tile;
        this.slotMode = mode;
    }

    public void putStack(ItemStack itemStack) {
        this.item = itemStack;
    }

    public ItemStack getStack() {
        return this.item;
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        if (this.item != null) {
            this.item.writeToNBT(nbtTagCompound);
        }
        nbt.setTag("TileSlot", nbtTagCompound);
        return nbt;
    }

    public void readFromNBT(NBTTagCompound nbt) {
        this.item = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("TileSlot"));
    }

    /**
     * Whether the current item can be inputted.
     * @param itemStack Input item.
     */
    public boolean canInput(ItemStack itemStack) {
        return true;
    }

    public enum SlotMode {
        INPUT,
        OUTPUT,
        INOUT,
        NULL
    }
}
