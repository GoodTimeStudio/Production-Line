package com.mcgoodtime.gti.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by BestOwl on 2015.10.28.0028.
 *
 * @author BestOwl
 */
public class SlotOutput extends Slot {

    private EntityPlayer player;

    public SlotOutput(EntityPlayer player, IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
        this.player = player;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     *
     * @param itemStack slot
     */
    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return false;
    }
}
