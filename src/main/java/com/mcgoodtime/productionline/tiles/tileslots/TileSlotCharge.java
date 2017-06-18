package com.mcgoodtime.productionline.tiles.tileslots;

import com.mcgoodtime.productionline.tiles.TileContainer;
import com.mcgoodtime.productionline.tiles.TileElectricContainer;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.energy.tile.IEnergyTile;
import ic2.api.item.ElectricItem;
import net.minecraft.item.ItemStack;

/**
 * Created by BestOwl on 2015.11.28.0028.
 *
 * @author BestOwl
 */
public class TileSlotCharge extends TileSlot {

    public TileSlotCharge(IEnergyTile tile, SlotMode mode) {
        super((TileContainer) tile, mode);
    }

    /**
     * Whether the current item can be inputted.
     *
     * @param itemStack Input item.
     */
    @SuppressWarnings("NumericOverflow")
    @Override
    public boolean canInput(ItemStack itemStack) {
        if (this.tile instanceof IEnergySink) {
            return ElectricItem.manager.charge(itemStack, 1.0D / 0.0, ((IEnergySink) this.tile).getSinkTier(), true, true) > 0.0D;
        } else if (this.tile instanceof IEnergySource) {
            return ElectricItem.manager.charge(itemStack, 1.0D / 0.0, ((IEnergySource) this.tile).getSourceTier(), true, true) > 0.0D;
        }
        return false;
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
