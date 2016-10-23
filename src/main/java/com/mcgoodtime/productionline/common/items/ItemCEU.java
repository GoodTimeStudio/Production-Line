package com.mcgoodtime.productionline.common.items;

import ic2.api.item.IBoxable;
import net.minecraft.item.ItemStack;

/**
 * Created by BestOwl on 2015.12.6.0006.
 *
 * @author BestOwl
 */
public class ItemCEU extends ItemElectricPL implements IBoxable {

    public ItemCEU() {
        super("CEU", 1, 20000);
    }

    @Override
    public boolean canProvideEnergy(ItemStack itemStack) {
        return true;
    }

    /**
     * Determine whether an item can be stored in a toolbox or not.
     *
     * @param itemstack item to be stored
     * @return Whether to store the item in the toolbox or not
     */
    @Override
    public boolean canBeStoredInToolbox(ItemStack itemstack) {
        return true;
    }
}
