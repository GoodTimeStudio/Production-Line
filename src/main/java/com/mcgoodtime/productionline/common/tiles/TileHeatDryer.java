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
/*
package com.mcgoodtime.productionline.common.tiles;

import com.mcgoodtime.productionline.common.recipes.HeatDryerRecipes;
import com.mcgoodtime.productionline.common.tiles.tileslots.*;
import ic2.api.tile.IWrenchable;
import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import ic2.core.upgrade.UpgradableProperty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Java0 on 2015/11/18.
 *
 *//*
public class TileHeatDryer extends TileElectricContainer implements IUpgradableBlock, IWrenchable {

    public final int requireEnergy = 500;
    public int progress;

    public TileHeatDryer() {
        super(3, 300, 1);
        this.tileSlots.add(new TileSlotInput(this, HeatDryerRecipes.instance));
        this.tileSlots.add(new TileSlotDischarge(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotOutput(this));
        this.tileSlots.add(new TileSlotUpgrade(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotUpgrade(this, TileSlot.SlotMode.NULL));
    }

    @Override
    public String getInventoryName() {
        return "HeatDryer";
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.worldObj.isRemote) {
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
            ItemStack itemStack = HeatDryerRecipes.instance.getProcessResult(this.getStackInSlot(0));
            if (itemStack != null) {
                if (!(itemStack.stackSize >= HeatDryerRecipes.instance.getRequiredProcessAmount(itemStack))) {
                    return false;
                }
                if (this.getStackInSlot(2) == null) {
                    return true;
                } else {
                    if (this.getStackInSlot(2).isItemEqual(itemStack)) {
                        int result = this.getStackInSlot(2).stackSize + itemStack.stackSize;
                        if (result <= getInventoryStackLimit() && result <= this.getStackInSlot(2).getMaxStackSize()) {
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
            ItemStack outputItem = HeatDryerRecipes.instance.getProcessResult(this.getStackInSlot(0));

            if (this.getStackInSlot(2) == null) {
                this.setInventorySlotContents(2, outputItem.copy());
            }
            else if (this.getStackInSlot(2).isItemEqual(outputItem)) {
                this.getStackInSlot(2).stackSize += outputItem.stackSize;
            }

            this.getStackInSlot(0).stackSize -= HeatDryerRecipes.instance.getRequiredProcessAmount(this.getStackInSlot(0));

            if (this.getStackInSlot(0).stackSize <= 0) {
                this.setInventorySlotContents(0, null);
            }
        }
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return super.acceptsEnergyFrom(emitter, direction);
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
    public Set<UpgradableProperty> getUpgradableProperties() {
        return EnumSet.of(UpgradableProperty.ItemConsuming, UpgradableProperty.ItemProducing);
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
}*/