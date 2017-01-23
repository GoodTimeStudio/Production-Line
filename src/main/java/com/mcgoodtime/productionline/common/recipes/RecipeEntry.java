package com.mcgoodtime.productionline.common.recipes;

import com.mcgoodtime.productionline.common.inventory.ItemStackView;

/**
 *
 */
public interface RecipeEntry {

    ItemStackView getInput();

    ItemStackView getOutput();

}
