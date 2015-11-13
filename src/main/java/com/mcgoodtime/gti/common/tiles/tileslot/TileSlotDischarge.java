package com.mcgoodtime.gti.common.tiles.tileslot;

import com.mcgoodtime.gti.common.tiles.TileContainer;
import ic2.api.item.ElectricItem;
import ic2.core.item.ItemBatterySU;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * Created by BestOwl on 2015.11.12.0012.
 *
 * @author BestOwl
 */
public class TileSlotDischarge extends TileSlot {

    public TileSlotDischarge(TileContainer tile, SlotMode mode) {
        super(tile, mode);
    }

    /**
     * Whether the current item can be inputted.
     *
     * @param itemStack Input item.
     */
    @SuppressWarnings("NumericOverflow")
    @Override
    public boolean canInput(ItemStack itemStack) {
        return itemStack != null && (!(itemStack.getItem() != Items.redstone && !(itemStack.getItem() instanceof ItemBatterySU)) || ElectricItem.manager.discharge(itemStack, 1.0D / 0.0, 1, true, true, true) > 0.0D);
    }
}
