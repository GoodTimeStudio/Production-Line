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

    public ContainerGti(EntityPlayer player, T tile) {
        this.tile = tile;
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
            slotItem.stackSize += i;
            item.stackSize -= i;
            return true;
        }
        else {
            int i = this.getTransferAmount(item.stackSize, 0, max);
            slot.putStack(StackUtil.copyWithSize(item, i));
            item.stackSize -= i;
            return true;
        }
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
}
