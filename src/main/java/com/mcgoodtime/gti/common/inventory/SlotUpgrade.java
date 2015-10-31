package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.tiles.TileGti;
import ic2.core.block.IUpgradableBlock;
import ic2.core.util.StackUtil;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.Iterator;

/**
 * Created by BestOwl on 2015.10.31.0031.
 * Upgrade slot.
 */
public class SlotUpgrade extends Slot {

    public SlotUpgrade(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    @Override
    public boolean isItemValid(ItemStack itemStack) {
        if (this.inventory instanceof IUpgradableBlock) {
            for (ItemStack itemstack : ((IUpgradableBlock) this.inventory).getCompatibleUpgradeList()) {
                if (StackUtil.isStackEqual(itemstack, itemStack)) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns the maximum stack size for a given slot (usually the same as getInventoryStackLimit(), but 1 in the case
     * of armor slots)
     */
    @Override
    public int getSlotStackLimit() {
        return 4;
    }
}
