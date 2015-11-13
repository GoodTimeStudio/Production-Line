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

import com.mcgoodtime.gti.common.inventory.slot.SlotInput;
import com.mcgoodtime.gti.common.inventory.slot.SlotOutput;
import com.mcgoodtime.gti.common.inventory.slot.SlotUpgrade;
import com.mcgoodtime.gti.common.recipes.CarbonizeFurnaceRecipes;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.gti.common.tiles.tileslot.TileSlotInput;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.item.IUpgradeItem;
import ic2.core.slot.SlotDischarge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/*
 * Created by suhao on 2015.7.4.
 */
public class ContainerCarbonizeFurnace extends ContainerGti<TileCarbonizeFurnace> {

    private int lastProgress;
    private int lastRequireEnergy;
    private int lastEnergy;

    public ContainerCarbonizeFurnace(EntityPlayer player, TileCarbonizeFurnace tile) {
        super(player, tile);
        this.addSlotToContainer(new SlotInput((TileSlotInput) tile.tileSlots.get(0), tile, 0, 56, 16));
        this.addSlotToContainer(new SlotDischarge(tile, 1, 1, 56, 53));
        this.addSlotToContainer(new SlotOutput(player, tile, 2, 113, 35));
        this.addSlotToContainer(new SlotOutput(player, tile, 3, 131, 35));
        this.addSlotToContainer(new SlotUpgrade(tile, 4, 153, 26));
        this.addSlotToContainer(new SlotUpgrade(tile, 5, 153, 44));
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafting) {
        super.addCraftingToCrafters(crafting);
        crafting.sendProgressBarUpdate(this, 0, this.tile.progress);
        crafting.sendProgressBarUpdate(this, 1, (int) this.tile.requireEnergy);
        crafting.sendProgressBarUpdate(this, 2, (int) this.tile.energy);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (Object crafter : this.crafters) {
            ICrafting icrafting = (ICrafting) crafter;

            if (this.lastProgress != this.tile.progress) {
                icrafting.sendProgressBarUpdate(this, 0, this.tile.progress);
            }

            if (this.lastRequireEnergy != this.tile.requireEnergy) {
                icrafting.sendProgressBarUpdate(this, 1, (int) this.tile.requireEnergy);
            }

            if (this.lastEnergy != this.tile.energy) {
                icrafting.sendProgressBarUpdate(this, 2, (int) this.tile.energy);
            }
        }

        this.lastProgress = this.tile.progress;
        this.lastRequireEnergy = (int) this.tile.requireEnergy;
        this.lastEnergy = this.tile.maxEnergy;
    }

    /**
     * Sends two ints to the client-side Container. Used for furnace burning time, smelting progress, brewing progress,
     * and enchanting level. Normally the first int identifies which variable to update, and the second contains the new
     * value. Both are truncated to shorts in non-local SMP.
     * @param i Identifies which variable to update
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int var) {
        if (i == 0) {
            this.tile.progress = var;
        }

        if (i == 1) {
            this.tile.requireEnergy = var;
        }

        if (i == 2) {
            this.tile.energy = var;
        }
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     * @param i The number of slot.
     *//*
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int i) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(i);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();

            if (i == 2) {
                if (!this.mergeItemStack(stack, 6, 39, true)) {
                    return null;
                }

                slot.onSlotChange(stack, itemstack);
            }
            else if (i == 3) {
                if (!this.mergeItemStack(stack, 6, 39, true)) {
                    return null;
                }
            }
            else if (i == 4) {
                if (!this.mergeItemStack(stack, 6, 39, true)) {
                    return null;
                }
            }
            else if (i == 5) {
                if (!this.mergeItemStack(stack, 6, 39, true)) {
                    return null;
                }
            }
            else if (i != 1 && i != 0) {
                if (CarbonizeFurnaceRecipes.instance.getProcessResult(stack) != null) {
                    if (!this.mergeItemStack(stack, 0, 1, false)) {
                        return null;
                    }
                }
                else if (this.tile.isDischargeItem(stack)) {
                    if (!this.mergeItemStack(stack, 1, 2, false)) {
                        return null;
                    }
                }
                else if (stack.getItem() instanceof IUpgradeItem) {
                    for (ItemStack itemStack1 : this.tile.getCompatibleUpgradeList()) {
                        if (stack.isItemEqual(itemStack1)) {
                            if (stack.stackSize <= 4) {
                                if (!this.mergeItemStack(stack, 4, 5, false)) {
                                    return null;
                                }
                                else if (!this.mergeItemStack(stack, 5, 6, false)) {
                                    return null;
                                }
                            }
                        }
                    }
                }
                else if (i >= 6 && i < 33) {
                    if (!this.mergeItemStack(stack, 33, 39, false))
                    {
                        return null;
                    }
                }
                else if (i >= 33 && i < 39 && !this.mergeItemStack(stack, 6, 30, false)) {
                    return null;
                }
            }
            else if (!this.mergeItemStack(stack, 6, 39, false)) {
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
    }*/
}
