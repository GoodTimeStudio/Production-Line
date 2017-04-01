/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2017 GoodTime Studio <https://github.com/GoodTimeStudio>
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

import com.mcgoodtime.productionline.common.recipes.PackagerRecipes;
import com.mcgoodtime.productionline.common.tiles.tileslots.*;
import ic2.core.upgrade.IUpgradeItem;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

/**
 * Created by BestOwl on 2017/3/25.
 *
 * @author BestOwl
 */
public class TilePackager extends TileMachine {

    public final int requireEnergy = 500;
    public int progress;

    public TilePackager() {
        super(3, 300, 1);
        this.tileSlots.add(new TileSlotInput(this, PackagerRecipes.instance));
        this.tileSlots.add(new TileSlotPackage(this));
        this.tileSlots.add(new TileSlotDischarge(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotOutput(this));
        this.tileSlots.add(new TileSlotUpgrade(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotUpgrade(this, TileSlot.SlotMode.NULL));
    }

    @Nonnull
    @Override
    public String getName() {
        return "Packager";
    }

    @Override
    public void update() {
        super.update();

        if (!this.world.isRemote) {
            boolean needUpdate = false;

            if (this.canProcess() && this.energy >= this.energyTick) {
                this.setActive(true);
                this.energy -= this.energyTick;
                this.progress += this.energyTick;

                if (this.progress >= this.requireEnergy) {
                    this.progress = 0;
                    this.processItem();
                    needUpdate = true;
                }
            } else {
                this.setActive(false);
                this.progress = 0;
            }

            for (TileSlot tileSlot : this.tileSlots) {
                if (tileSlot instanceof TileSlotUpgrade) {
                    ItemStack stack = tileSlot.getStack();
                    if(stack != null && stack.getItem() instanceof IUpgradeItem && ((IUpgradeItem)stack.getItem()).onTick(stack, this)) {
                        needUpdate = true;
                    }
                }
            }

            if (needUpdate) {
                this.markDirty();
            }
        }
    }

    private boolean canProcess() {
        if (this.getStackInSlot(0) == null) {
            return false;
        } else {
            ItemStack input = this.getStackInSlot(0);
            ItemStack outputStack = PackagerRecipes.instance.getProcessResult(input);
            if (outputStack != null) {
                if (!(input.stackSize >= PackagerRecipes.instance.getRequiredProcessAmount(input))) {
                    return false;
                }

                if (this.getStackInSlot(1) != null) { // check package
                    if (this.getStackInSlot(3) == null) { // output slot
                        return true;
                    } else {
                        if (this.getStackInSlot(3).isItemEqual(outputStack)) {
                            int result = this.getStackInSlot(3).stackSize + outputStack.stackSize;
                            if (result <= getInventoryStackLimit() && result <= this.getStackInSlot(3).getMaxStackSize()) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }

    public void processItem() {
        if (this.canProcess()) {
            ItemStack outputItem = PackagerRecipes.instance.getProcessResult(this.getStackInSlot(0));

            if (this.getStackInSlot(3) == null) {
                this.setInventorySlotContents(3, outputItem.copy());
            }
            else if (this.getStackInSlot(3).isItemEqual(outputItem)) {
                this.getStackInSlot(3).stackSize += outputItem.stackSize;
            }

            this.getStackInSlot(0).stackSize -= PackagerRecipes.instance.getRequiredProcessAmount(this.getStackInSlot(0));

            if (this.getStackInSlot(0).stackSize <= 0) {
                this.setInventorySlotContents(0, null);
            }

            this.getStackInSlot(1).stackSize--;

            if (this.getStackInSlot(1).stackSize <= 0) {
                this.setInventorySlotContents(1, null);
            }
        }
    }
}
