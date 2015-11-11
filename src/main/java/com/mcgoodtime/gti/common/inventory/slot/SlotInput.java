package com.mcgoodtime.gti.common.inventory.slot;

import com.mcgoodtime.gti.common.tiles.tileslot.TileSlotInput;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * Created by BestOwl on 2015.11.10.0010.
 *
 * @author BestOwl
 */
public class SlotInput extends Slot {

    public TileSlotInput input;

    public SlotInput(TileSlotInput input, IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
        this.input = input;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack itemStack) {
        return this.input.canInput(itemStack);
    }
}
