package com.mcgoodtime.gti.common.tiles.tileslots;

import com.mcgoodtime.gti.common.tiles.TileElectricContainer;
import ic2.api.item.ElectricItem;
import net.minecraft.item.ItemStack;

/**
 * Created by BestOwl on 2015.11.28.0028.
 *
 * @author BestOwl
 */
public class TileSlotCharge extends TileSlot {

    public TileSlotCharge(TileElectricContainer tile, SlotMode mode) {
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
        return ElectricItem.manager.charge(itemStack, 1.0D / 0.0, ((TileElectricContainer) this.tile).tier, true, true) > 0.0D;
    }

    public double charge(double amount) {
        if(amount <= 0.0D) {
            throw new IllegalArgumentException("Amount must be > 0.");
        } else {
            ItemStack itemStack = this.getStack();
            return itemStack == null ? 0.0D : ElectricItem.manager.charge(itemStack, amount, ((TileElectricContainer) this.tile).tier, false, false);
        }
    }
}
