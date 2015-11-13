package com.mcgoodtime.gti.common.tiles.tileslot;

import com.mcgoodtime.gti.common.tiles.TileContainer;
import ic2.core.block.IUpgradableBlock;
import ic2.core.item.IUpgradeItem;
import net.minecraft.item.ItemStack;

/**
 * Created by BestOwl on 2015.11.12.0012.
 *
 * @author BestOwl
 */
public class TileSlotUpgrade extends TileSlot {

    public TileSlotUpgrade(TileContainer tile, SlotMode mode) {
        super(tile, mode);
    }

    /**
     * Whether the current item can be inputted.
     *
     * @param itemStack Input item.
     */
    @Override
    public boolean canInput(ItemStack itemStack) {
        if (itemStack.getItem() instanceof IUpgradeItem && this.tile instanceof IUpgradableBlock) {
            for (ItemStack stack : ((IUpgradableBlock) this.tile).getCompatibleUpgradeList()) {
                if (stack.isItemEqual(itemStack)) {
                    return true;
                }
            }
        }
        return false;
    }
}
