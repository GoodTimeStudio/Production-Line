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
public class TileSlotOutput extends TileSlot {

    public TileSlotOutput(TileContainer tile, SlotMode mode) {
        super(tile, mode);
    }

    /**
     * Whether the current item can be inputted.
     *
     * @param itemStack Input item.
     */
    @Override
    public boolean canInput(ItemStack itemStack) {
        return false;
    }
}
