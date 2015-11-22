package com.mcgoodtime.gti.common.recipes;

import com.mcgoodtime.gti.common.init.GtiBlocks;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by JAVA0 on 2015/11/18.
 *
 */
public class HeatDryerRecipes implements IProcessable<HeatDryerRecipes.Recipes> {
    public static final HeatDryerRecipes instance = new HeatDryerRecipes();

    /** The list of smelting results. */
    private List<Recipes> processList = new ArrayList<Recipes>();

    private HeatDryerRecipes() {
        /* The default value after the process */
        float XP = 0.15F;
        this.register(new ItemStack(GtiBlocks.waterHyacinth, 4), GtiBlocks.dehydratedWaterHyacinthblock, XP);
    }

    public void register(ItemStack input, ItemStack output, float xp) {
        processList.add(new Recipes(input, output, xp));
    }


    /**
     * Returns the process result of an item.
     */
    public ItemStack getProcessResult(ItemStack itemStack) {
        for (Recipes recipes : processList) {
            if (recipes.input.isItemEqual(itemStack)) {
                return recipes.output;
            }
        }
        return null;
    }

    /**
     * @return Whether this item can process
     */
    @Override
    public boolean canProcess(ItemStack itemStack) {
        for (Recipes recipes : this.processList) {
            if (recipes.input.isItemEqual(itemStack)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get required amount of process
     * @param itemStack Input item
     * @return Required amount of process
     */
    public int getRequiredProcessAmount(ItemStack itemStack) {
        for (Recipes recipes : this.processList) {
            if (recipes.input.isItemEqual(itemStack)) {
                return recipes.input.stackSize;
            }
        }
        return 1;
    }

    @Override
    public List<Recipes> getProcessRecipesList() {
        return this.processList;
    }

    /**
     * @param itemStack Input item
     */
    @Override
    public Recipes getRecipe(ItemStack itemStack) {
        for (Recipes recipes : this.processList) {
            if (recipes.input.isItemEqual(itemStack)) {
                return recipes;
            }
        }
        return null;
    }

    public static class Recipes {
        public ItemStack input;
        public ItemStack output;
        public double xp;

        private Recipes(ItemStack input, ItemStack output, float xp) {
            this.input = input;
            this.output = output;
            this.xp = xp;
        }
    }
}
