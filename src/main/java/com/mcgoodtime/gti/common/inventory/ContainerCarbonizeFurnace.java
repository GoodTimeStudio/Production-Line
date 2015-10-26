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
package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.recipes.CarbonizeFurnaceRecipes;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.gti.common.tiles.TileContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

/*
 * Created by suhao on 2015.7.4.
 */
public class ContainerCarbonizeFurnace extends Container {

    public TileCarbonizeFurnace tile;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;

    public ContainerCarbonizeFurnace(EntityPlayer player, TileCarbonizeFurnace tile) {
        this.tile = tile;
        this.addSlotToContainer(new Slot(tile, 0, 56, 16));
        this.addSlotToContainer(new Slot(tile, 1, 56, 53));
        this.addSlotToContainer(new SlotFurnace(player, tile, 2, 113, 35));
        this.addSlotToContainer(new SlotFurnace(player, tile, 3, 131, 35));
        int i;

        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tile.progress);
        crafting.sendProgressBarUpdate(this, 1, this.tile.requireEnergy);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object crafter : this.crafters) {
            ICrafting icrafting = (ICrafting) crafter;

            if (this.lastCookTime != this.tile.progress) {
                icrafting.sendProgressBarUpdate(this, 0, this.tile.progress);
            }

            if (this.lastBurnTime != this.tile.requireEnergy) {
                icrafting.sendProgressBarUpdate(this, 1, this.tile.requireEnergy);
            }

            if (this.lastItemBurnTime != this.tile.maxEnergy) {
                icrafting.sendProgressBarUpdate(this, 2, this.tile.maxEnergy);
            }
        }

        this.lastCookTime = this.tile.progress;
        this.lastBurnTime = this.tile.requireEnergy;
        this.lastItemBurnTime = this.tile.maxEnergy;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int slot, int var) {
        if (slot == 0) {
            this.tile.progress = var;
        }

        if (slot == 1) {
            this.tile.requireEnergy = var;
        }

        if (slot == 2) {
            this.tile.energy = var;
        }
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     * @param i The number of slot.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();

            if (i == 2) {
                if (!this.mergeItemStack(stack, 4, 39, true)) {
                    return null;
                }

                slot.onSlotChange(stack, itemstack);
            }
            else if (i == 3) {
                if (!this.mergeItemStack(stack, 4, 39, true)) {
                    return null;
                }
            }
            else if (i != 1 && i != 0) {
                if (CarbonizeFurnaceRecipes.instance.getProcessResult(stack) != null) {
                    if (!this.mergeItemStack(stack, 0, 1, false)) {
                        return null;
                    }
                }
                else if (TileContainer.isElectricPower(stack)) {
                    if (!this.mergeItemStack(stack, 1, 2, false)) {
                        return null;
                    }
                }
                else if (i >= 3 && i < 30) {
                    if (!this.mergeItemStack(stack, 30, 39, false))
                    {
                        return null;
                    }
                }
                else if (i >= 30 && i < 39 && !this.mergeItemStack(stack, 4, 30, false)) {
                    return null;
                }
            }
            else if (!this.mergeItemStack(stack, 4, 39, false)) {
                return null;
            }

            if (stack.stackSize == 0) {
                slot.putStack(null);
            }
            else {
                slot.onSlotChanged();
            }

            if (stack.stackSize == itemstack.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(player, stack);
        }

        return itemstack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tile.isUseableByPlayer(player);
    }

    public TileCarbonizeFurnace getTileEntity() {
        return this.tile;
    }
}
