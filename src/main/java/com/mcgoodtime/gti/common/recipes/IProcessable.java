package com.mcgoodtime.gti.common.recipes;

import net.minecraft.item.ItemStack;
import java.util.List;

/**
 * Created by BestOwl on 2015.11.10.0010.
 *
 * @author BestOwl
 */
public interface IProcessable<T> {

    /**
     * Returns the process result of an item.
     */
    ItemStack getProcessResult(ItemStack itemStack);

    /**
     * @return Whether this item can process
     */
    boolean canProcess(ItemStack itemStack);

    /**
     * Get required amount of process
     * @param itemStack Input item
     * @return Required amount of process
     */
    int getRequiredProcessAmount(ItemStack itemStack);

    List<T> getProcessRecipesList();

    /**
     * @param itemStack Input item
     */
    T getRecipe(ItemStack itemStack);
}
