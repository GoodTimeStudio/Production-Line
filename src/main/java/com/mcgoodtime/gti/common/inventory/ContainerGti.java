package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.tiles.TileContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

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
}
