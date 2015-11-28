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
package com.mcgoodtime.gti.common.tiles;

import com.mcgoodtime.gti.common.recipes.CarbonizeFurnaceRecipes;
import com.mcgoodtime.gti.common.tiles.tileslots.*;
import ic2.api.tile.IWrenchable;
import ic2.core.Ic2Items;
import ic2.core.block.IUpgradableBlock;
import ic2.core.item.IUpgradeItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

/**
 * CarbonizeFurnace TileEntity
 *
 * @author BestOwl
 */
public class TileCarbonizeFurnace extends TileElectricContainer implements IUpgradableBlock, IWrenchable {
    /** containerItemsList: 0 = input, 1 = power, 2 & 3 = output, 4 & 5 = upgrades */

    /** The number of ticks that the furnace will keep burning */
    public double requireEnergy;
    /** The number of ticks that the current item has been process for */
    public int progress;

    public TileCarbonizeFurnace() {
        super(3, 300, 1);
        this.tileSlots.add(new TileSlotInput(this, CarbonizeFurnaceRecipes.instance));
        this.tileSlots.add(new TileSlotDischarge(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotOutput(this, TileSlot.SlotMode.OUTPUT));
        this.tileSlots.add(new TileSlotOutput(this, TileSlot.SlotMode.OUTPUT));
        this.tileSlots.add(new TileSlotUpgrade(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotUpgrade(this, TileSlot.SlotMode.NULL));
    }

    @Override
    public String getInventoryName() {
        return "CarbonizeFurnace";
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.requireEnergy = nbt.getShort("requireEnergy");
        this.progress = nbt.getShort("Progress");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("requireEnergy", (short) requireEnergy);
        nbt.setShort("Progress", (short) progress);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.worldObj.isRemote) {
            boolean needUpdate = false;

            if (canProcess() && this.energy >= this.energyTick) {
                this.requireEnergy = CarbonizeFurnaceRecipes.instance.getRecipe(this.getStackInSlot(0)).requiresEnergy;
                this.setActive(true);
                this.energy -= this.energyTick;
                this.progress += this.energyTick;

                if (this.progress >= this.requireEnergy) {
                    this.requireEnergy = 0;
                    this.progress = 0;
                    this.processItem();
                    needUpdate = true;
                }
            } else {
                this.setActive(false);
                this.requireEnergy = 0;
                this.progress = 0;
            }

            for(int i = 4; i < 6; i++) {
                ItemStack stack = this.getStackInSlot(i);
                if(stack != null && stack.getItem() instanceof IUpgradeItem && ((IUpgradeItem)stack.getItem()).onTick(stack, this)) {
                    needUpdate = true;
                }
            }

            if (needUpdate) {
                this.markDirty();
            }
        }
    }

    /**
     * @return Whether this Item can process
     */
    private boolean canProcess() {
        if (this.getStackInSlot(0) == null) {
            return false;
        } else {
            ItemStack itemStack = CarbonizeFurnaceRecipes.instance.getProcessResult(this.getStackInSlot(0));
            if (itemStack != null) {
                if (!(itemStack.stackSize > CarbonizeFurnaceRecipes.instance.getRequiredProcessAmount(itemStack))) {
                    return false;
                }

                if (this.getStackInSlot(2) == null || this.getStackInSlot(3) == null) {
                    return true;
                } else {

                    if (this.getStackInSlot(2).isItemEqual(itemStack)) {
                        int result = this.getStackInSlot(2).stackSize + itemStack.stackSize;
                        if (result <= getInventoryStackLimit() && result <= this.getStackInSlot(2).getMaxStackSize()) {
                            return true;
                        }
                    }

                    if (this.getStackInSlot(3).isItemEqual(itemStack)) {
                        int result = this.getStackInSlot(3).stackSize + itemStack.stackSize;
                        if (result <= getInventoryStackLimit() && result <= this.getStackInSlot(3).getMaxStackSize()) {
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
            ItemStack outputItem = CarbonizeFurnaceRecipes.instance.getProcessResult(this.getStackInSlot(0));

            if (this.getStackInSlot(2) == null) {
                this.setInventorySlotContents(2, outputItem.copy());
            }
            else if (this.getStackInSlot(2).isItemEqual(outputItem)) {
                this.getStackInSlot(2).stackSize += outputItem.stackSize;
            }
            else if (this.getStackInSlot(3) == null) {
                this.setInventorySlotContents(3, outputItem.copy());
            }
            else if (this.getStackInSlot(3).isItemEqual(outputItem)) {
                this.getStackInSlot(3).stackSize += outputItem.stackSize;
            }

            this.getStackInSlot(0).stackSize -= CarbonizeFurnaceRecipes.instance.getRequiredProcessAmount(this.getStackInSlot(0));

            if (this.getStackInSlot(0).stackSize <= 0) {
                this.setInventorySlotContents(0, null);
            }
        }
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return direction != ForgeDirection.UP && super.acceptsEnergyFrom(emitter, direction);
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean useEnergy(double amount) {
        if(this.energy >= amount) {
            this.energy -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setRedstonePowered(boolean redstonePowered) {
    }

    @Override
    public List<ItemStack> getCompatibleUpgradeList() {
        List<ItemStack> list = new ArrayList<ItemStack>();
        list.add(Ic2Items.ejectorUpgrade);
        list.add(Ic2Items.pullingUpgrade);
        return list;
    }

    @Override
    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int i) {
        return i != this.facing && i != 0 && i != 1;
    }

    @Override
    public short getFacing() {
        return this.facing;
    }

    @Override
    public void setFacing(short i) {
        super.setFacing(i);
    }

    @Override
    public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public float getWrenchDropRate() {
        return 1.0F;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 1, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
    }
}
