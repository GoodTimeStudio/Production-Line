package com.mcgoodtime.productionline.common.items;

import ic2.core.util.ItemComparableItemStack;
import net.minecraft.item.ItemStack;

/**
 * Utilities
 */
public final class ItemStacks {

    public static ItemComparableItemStack viewOf(ItemStack stack) {
        return new ItemComparableItemStack(stack, true);
    }

    public static ItemComparableItemStack typeOf(ItemStack stack) {
        return new ItemComparableItemStack(stack, false);
    }

    public static ItemStack copyStack(ItemStack in, int amount) {
        ItemStack ret = in.copy();
        ret.stackSize = amount;
        return ret;
    }

    private ItemStacks() {}
}
