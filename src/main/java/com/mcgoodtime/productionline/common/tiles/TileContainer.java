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
package com.mcgoodtime.productionline.common.tiles;

import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by suhao on 2015.10.25.0025.
 *
 * @author BestOwl
 */
public abstract class TileContainer extends TilePL implements ISidedInventory, ITickable {

    /** The ItemStacks that hold the itemsList in the container */
    public final List<TileSlot> tileSlots = new ArrayList<>();

    protected String name;

    @Override
    public int getSizeInventory() {
        return this.tileSlots.size();
    }

    @Override
    @Nonnull
    public abstract String getName();

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (index < 0 || index >= tileSlots.size()) {
            return null;
        }
        TileSlot slot = tileSlots.get(index);
        if (slot != null) {
            ItemStack stack = slot.getStack();
            slot.putStack(null);
            return stack;
        }
        return null;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        tileSlots.forEach(slot -> slot.putStack(null));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);

        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound tag = nbttaglist.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");

            if (slot >= 0 && slot < this.getSizeInventory()) {
                this.tileSlots.get(i).readFromNBT(tag);
            }
        }

        if (nbt.hasKey("CustomName", 8)) {
            this.name = nbt.getString("CustomName");
        }
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        NBTTagCompound nbt = super.writeToNBT(compound);

        NBTTagList slotList = new NBTTagList();
        for (int i = 0; i < this.getSizeInventory(); ++i) {
            if (this.tileSlots.get(i) != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                this.tileSlots.get(i).writeToNBT(tag);
                slotList.appendTag(tag);
            }
        }
        nbt.setTag("Items", slotList);

        if (this.hasCustomName()) {
            nbt.setString("CustomName", this.name);
        }
        return nbt;
    }

    /**
     * Returns an array containing the indices of the slots that can be accessed by automation
     * on the given side of this block.
     */
    @Override
    @Nonnull
    public int[] getSlotsForFace(EnumFacing side) {
        int[] ret = new int[this.getSizeInventory()];
        for (int i = 0; i < ret.length; i++){
            ret[i] = i;
        }
        return ret;
    }

    /**
     * Returns true if automation can insert the given item in the given
     * slot from the given side. Args: Slot, item, side
     */
    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, EnumFacing side) {
        return this.isItemValidForSlot(slot, itemStack);
    }

    /**
     * Returns true if automation can extract the given item in the given
     * slot from the given side. Args: Slot, item, side
     */
    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, EnumFacing side) {
        TileSlot tileSlot = this.tileSlots.get(slot);
        return tileSlot.slotMode == TileSlot.SlotMode.OUTPUT || tileSlot.slotMode == TileSlot.SlotMode.INOUT || itemStack.getItem().hasContainerItem(itemStack);
    }


    /**
     * Returns the stack in slot
     * @param slot The number of slot
     */
    @Override
    public ItemStack getStackInSlot(int slot) {
        return this.tileSlots.get(slot).getStack();
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number
     * (second arg) of items and returns them in a new stack.
     */
    @Override
    public ItemStack decrStackSize(int slot, int num) {
        if (this.tileSlots.get(slot).getStack() != null) {
            ItemStack slotItem;
            ItemStack itemstack;

            slotItem = this.tileSlots.get(slot).getStack();

            if (slotItem.stackSize <= num) {
                this.tileSlots.get(slot).putStack(null);
                return slotItem;
            } else {
                itemstack = slotItem.splitStack(num);

                if (slotItem.stackSize == 0) {
                    this.tileSlots.get(slot).putStack(null);
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

//    /**
//     * When some containers are closed they call this on each slot, then drop whatever
//     * it returns as an EntityItem like when you close a workbench GUI.
//     */
//    @Override
//    public ItemStack getStackInSlotOnClosing(int index) {
//        ItemStack itemstack = this.tileSlots.get(index).getStack();
//        if (itemstack != null) {
//            this.tileSlots.get(index).putStack(null);
//        }
//        return itemstack;
//    }

    /**
     * Sets the given item stack to the specified slot in the inventory
     * (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack itemStack) {
        TileSlot slot = this.tileSlots.get(index);
        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit()) {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
        slot.putStack(itemStack);
    }

    /**
     * @return if the inventory is named
     */
    @Override
    public boolean hasCustomName() {
        return this.name != null && this.name.length() > 0;
    }

    /**
     * @return the maximum stack size for a inventory slot.
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        return !this.isInvalid() && entityPlayer.getDistanceSq(pos) <= 4096;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    /**
     * Return true if automation is allowed to insert the given
     * stack (ignoring stack size) into the given slot.
     * @param slot The number of slot.
     */
    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        TileSlot tileSlot = this.tileSlots.get(slot);
        return (tileSlot.slotMode == TileSlot.SlotMode.INPUT || tileSlot.slotMode == TileSlot.SlotMode.INOUT) && tileSlot.canInput(itemStack);
    }

}
