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
package com.mcgoodtime.gti.common.tiles;

import com.mcgoodtime.gti.common.recipes.CarbonizeFurnaceRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.Ic2Items;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.ArrayList;
import java.util.List;

/**
 * CarbonizeFurnace TileEntity
 *
 * @author BestOwl
 */
public class TileCarbonizeFurnace extends TileElectricContainer {
    /** containerItemsList: 0 = input, 1 = power, 2 & 3 = output */

    /** The number of remaining battery */
    public double energy;
    /** The number of that can storage battery */
    public final int maxEnergy = 200;
    /** The number of ticks that the furnace will keep burning */
    public int requireEnergy;
    /** The number of ticks that the current item has been process for */
    public int progress;

    @Override
    public int getSizeInventory() {
        return 4;
    }

    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.name : "Carbonize Furnace";
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.energy = nbt.getDouble("Energy");
        this.requireEnergy = nbt.getShort("requireEnergy");
        this.progress = nbt.getShort("Progress");

        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound tag = nbttaglist.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < this.containerItemsList.size()) {
                this.containerItemsList.set(slot, ItemStack.loadItemStackFromNBT(tag));
            }
        }

        if (nbt.hasKey("CustomName", 8)) {
            this.name = nbt.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setDouble("Energy", energy);
        nbt.setShort("requireEnergy", (short) requireEnergy);
        nbt.setShort("Progress", (short) progress);

        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.containerItemsList.size(); ++i) {
            if (this.containerItemsList.get(i) != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                this.containerItemsList.get(i).writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        nbt.setTag("Items", itemList);

        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.name);
        }
    }

    /**
     * Returns an integer between 0 and the passed value representing
     * how close the current item is to being completely cooked
     */
    @SideOnly(Side.CLIENT)
    public int getProgressScaled(int i) {
        return this.progress * i /200;
    }

    /**
     * Returns an integer between 0 and the passed value representing how
     * much remaining battery
     */
    @SideOnly(Side.CLIENT)
    public int getRemainingBatteryScaled(int i) {
        return (int) (this.requireEnergy * i / this.energy);
    }

    public boolean isProcessing() {
        return this.requireEnergy > 0;
    }

    @Override
    public void updateEntity() {

    }

    /**
     * @return Whether this Item can process
     */
    private boolean canProcess() {
        if (this.containerItemsList.get(0) == null) {
            return false;
        } else {
            ItemStack itemStack = CarbonizeFurnaceRecipes.instance.getProcessResult(this.containerItemsList.get(0));
            if (itemStack != null) {
                if (this.containerItemsList.get(2) == null || this.containerItemsList.get(3) == null) {
                    return true;
                } else {

                    if (this.containerItemsList.get(2).isItemEqual(itemStack)) {
                        int result = containerItemsList.get(2).stackSize + itemStack.stackSize;
                        if (result <= getInventoryStackLimit() && result <= this.containerItemsList.get(2).getMaxStackSize()) {
                            return true;
                        }
                    }

                    if (this.containerItemsList.get(3).isItemEqual(itemStack)) {
                        int result = containerItemsList.get(3).stackSize + itemStack.stackSize;
                        if (result <= getInventoryStackLimit() && result <= this.containerItemsList.get(3).getMaxStackSize()) {
                            return true;
                        }
                    }

                }

            }
            return false;
        }
    }

    public void processItem() {
        if (this.canProcess()) {
            ItemStack outputItem = CarbonizeFurnaceRecipes.instance.getProcessResult(this.containerItemsList.get(0));

            if (this.containerItemsList.get(2) == null) {
                this.containerItemsList.set(2, outputItem.copy());
            }
            else if (this.containerItemsList.get(2).isItemEqual(outputItem)) {
                this.containerItemsList.get(2).stackSize += outputItem.stackSize;
            }
            else if (this.containerItemsList.get(3) == null) {
                this.containerItemsList.set(3, outputItem.copy());
            }
            else if (this.containerItemsList.get(3).isItemEqual(outputItem)) {
                this.containerItemsList.get(3).stackSize += outputItem.stackSize;
            }

            this.containerItemsList.get(0).stackSize -= CarbonizeFurnaceRecipes.instance.getRequiredProcessAmount(this.containerItemsList.get(0));

            if (this.containerItemsList.get(0).stackSize <= 0) {
                this.containerItemsList.set(0, null);
            }
        }
    }

    @Override
    public ItemStack decrStackSize(int slot, int num) {
        if (this.containerItemsList.get(slot) != null) {
            ItemStack itemstack;

            if (this.containerItemsList.get(slot).stackSize <= num) {
                itemstack = this.containerItemsList.get(slot);
                this.containerItemsList.set(slot, null);
                return itemstack;
            } else {
                itemstack = this.containerItemsList.get(slot).splitStack(num);

                if (this.containerItemsList.get(slot).stackSize == 0) {
                    this.containerItemsList.set(slot, null);
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack) {
        return (!(slot == 2 || slot == 3)) && (slot != 1 || isElectricPower(itemStack));

    }

    @Override
    public double getEnergy() {
        return 0;
    }

    @Override
    public boolean useEnergy(double v) {
        return false;
    }

    @Override
    public void setRedstonePowered(boolean b) {

    }

    @Override
    public List<ItemStack> getCompatibleUpgradeList() {
        ArrayList<ItemStack> itemStacks = new ArrayList<ItemStack>();
        itemStacks.add(Ic2Items.ejectorUpgrade);
        itemStacks.add(Ic2Items.pullingUpgrade);
        return itemStacks;
    }
}
