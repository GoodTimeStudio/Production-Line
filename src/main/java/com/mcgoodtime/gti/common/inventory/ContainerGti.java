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
package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.tiles.TileContainer;
import ic2.core.util.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by BestOwl on 2015.10.31.0031.
 *
 * Gti base container.
 * @author BestOwl
 */
public abstract class ContainerGti<T extends IInventory> extends Container {

    public T tile;

    public ContainerGti(EntityPlayer player, T tile, int ySide) {
        this.tile = tile;
        int yOffset = ySide - 166;
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18 + yOffset));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player.inventory, i, 8 + i * 18, 142 + yOffset));
        }
    }

    public ContainerGti(EntityPlayer player, T tile) {
        this(player, tile, 166);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected final Slot addSlotToContainer(Slot slot) {
        int num = this.tile.getSizeInventory();

        while (this.inventorySlots.size() < num) {
            this.inventorySlots.add(null);
            this.inventoryItemStacks.add(null);
        }
        if (slot.inventory instanceof TileContainer) {
            slot.slotNumber = slot.getSlotIndex();
            this.inventorySlots.set(slot.getSlotIndex(), slot);
        } else {
            slot.slotNumber = this.inventorySlots.size();
            this.inventorySlots.add(slot);
            this.inventoryItemStacks.add(null);
        }
        return slot;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return this.tile.isUseableByPlayer(player);
    }

    public T getTileEntity() {
        return this.tile;
    }

    public boolean transferItemStack(ItemStack item, Slot slot) {
        int max = Math.min(item.getMaxStackSize(), slot.getSlotStackLimit());
        max = Math.min(max, slot.inventory.getInventoryStackLimit());
        ItemStack slotItem = slot.getStack();

        if (slotItem != null) {
            if (!item.isItemEqual(slotItem)) {
                return false;
            }

            int i = this.getTransferAmount(item.stackSize, slotItem.stackSize, max);
            if (i != 0) {
                slotItem.stackSize += i;
                item.stackSize -= i;
                return true;
            }
        }
        else {
            int i = this.getTransferAmount(item.stackSize, 0, max);
            if (i != 0) {
                slot.putStack(StackUtil.copyWithSize(item, i));
                item.stackSize -= i;
                return true;
            }
        }
        return false;
    }

    private int getTransferAmount(int amount, int currentAmount, int maxAmount) {
        if (amount <= maxAmount) {
            if (amount + currentAmount > maxAmount) {
                return maxAmount - currentAmount;
            } else {
                return amount;
            }
        }
        return 0;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int index) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack stack = slot.getStack();
            itemstack = stack.copy();

            if (this.tile instanceof TileContainer) {
                TileContainer container = (TileContainer) this.tile;

                if (index <= container.tileSlots.size()) {

                    boolean flag = false;
                    for (int i = container.tileSlots.size() + 27; i < this.inventorySlots.size(); i++) {
                        flag = this.transferItemStack(stack, (Slot) this.inventorySlots.get(i));
                        if (flag) {
                            break;
                        }
                    }

                    if (!flag) {
                        for (int i = container.tileSlots.size(); i < this.inventorySlots.size() - 9; i++) {
                            flag = this.transferItemStack(stack, (Slot) this.inventorySlots.get(i));
                            if (flag) {
                                break;
                            }
                        }
                    }

                }

                else {
                    boolean flag = false;
                    for (int i = 0; i < container.tileSlots.size(); i++) {
                        if (container.tileSlots.get(i) != null && container.tileSlots.get(i).canInput(stack)) {
                            flag = this.transferItemStack(stack, (Slot) this.inventorySlots.get(i));
                            if (flag) {
                                break;
                            }
                        }
                    }

                    if (!flag) {

                        if (index >= this.inventorySlots.size() - 9) {
                            for (int i = container.tileSlots.size(); i < this.inventorySlots.size() - 9; i++) {
                                if (this.transferItemStack(stack, (Slot) this.inventorySlots.get(i))) {
                                    break;
                                }
                            }
                        }
                        else {
                            for (int i = container.tileSlots.size() + 27; i < this.inventorySlots.size(); i++) {
                                if (this.transferItemStack(stack, (Slot) this.inventorySlots.get(i))) {
                                    break;
                                }
                            }
                        }

                    }
                }

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

            slot.onPickupFromSlot(entityPlayer, stack);
        }

        return itemstack;
    }
}
