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

package com.mcgoodtime.productionline.tiles;

import com.mcgoodtime.productionline.recipes.CarbonizeFurnaceRecipes;
import com.mcgoodtime.productionline.recipes.RecipePart;
import com.mcgoodtime.productionline.tiles.tileslots.*;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.upgrade.IUpgradeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;

/**
 * CarbonizeFurnace TileEntity
 *
 * @author BestOwl
 */
public class TileCarbonizeFurnace extends TileMachine {

    /** The number of ticks that the furnace will keep burning */
    public double requireEnergy;
    /** The number of ticks that the current item has been process for */
    public int progress;
    public int requireAmount;

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
    public String getName() {
        return "CarbonizeFurnace";
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.requireEnergy = nbt.getShort("requireEnergy");
        this.progress = nbt.getShort("Progress");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        nbt = super.writeToNBT(nbt);
        nbt.setShort("requireEnergy", (short) requireEnergy);
        nbt.setShort("Progress", (short) progress);
        return nbt;
    }

    @Override
    public void update() {
        super.update();
        if (!this.world.isRemote) {
            boolean needUpdate = false;

            if (canProcess() && this.energy >= this.energyTick) {
                RecipePart recipePart = CarbonizeFurnaceRecipes.instance.getRecipePart(this.getStackInSlot(0));
                this.requireEnergy = ((CarbonizeFurnaceRecipes.RecipePartCarbonizeFurnace) recipePart).requiresEnergy;
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
            ItemStack stack = this.getStackInSlot(0);
            ItemStack outputItemStack = CarbonizeFurnaceRecipes.instance.getProcessResult(stack);
            if (outputItemStack != null) {
                if (!(stack.getCount() >= CarbonizeFurnaceRecipes.instance.getRequiredProcessAmount(stack))) {
                    return false;
                }

                if (this.getStackInSlot(2) == null || this.getStackInSlot(3) == null) {
                    return true;
                } else {

                    if (this.getStackInSlot(2).isItemEqual(outputItemStack)) {
                        int result = this.getStackInSlot(2).getCount() + outputItemStack.getCount();
                        if (result <= getInventoryStackLimit() && result <= this.getStackInSlot(2).getMaxStackSize()) {
                            return true;
                        }
                    }

                    if (this.getStackInSlot(3).isItemEqual(outputItemStack)) {
                        int result = this.getStackInSlot(3).getCount() + outputItemStack.getCount();
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
                this.getStackInSlot(2).grow(outputItem.getCount());
            }
            else if (this.getStackInSlot(3) == null) {
                this.setInventorySlotContents(3, outputItem.copy());
            }
            else if (this.getStackInSlot(3).isItemEqual(outputItem)) {
                this.getStackInSlot(3).grow(outputItem.getCount());
            }

            this.getStackInSlot(0).shrink(CarbonizeFurnaceRecipes.instance.getRequiredProcessAmount(this.getStackInSlot(0)));

            if (this.getStackInSlot(0).getCount() <= 0) {
                this.setInventorySlotContents(0, null);
            }
        }
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return enumFacing != EnumFacing.UP && super.acceptsEnergyFrom(iEnergyEmitter, enumFacing);
    }

}
