package com.mcgoodtime.gti.common.recipes;

import com.mcgoodtime.gti.common.init.GtiItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/18.
 */
public class HeatDryerRecipes {
    public static final HeatDryerRecipes instance = new HeatDryerRecipes();

    /** The default value after the process */
    private final float XP = 0.15F;
    /** The list of smelting results. */
    private List<Recipes> processList = new ArrayList<Recipes>();

    private HeatDryerRecipes() {

    }

    public void register(ItemStack input, ItemStack output, double requireEnergy, float xp) {
        processList.add(new Recipes(input, output, requireEnergy, xp));
    }

    /**
     * Returns the process result of an item.
     */
    public Recipes getRecipes(ItemStack itemStack) {
        for (Recipes recipes : processList) {
            if (recipes.input.isItemEqual(itemStack)) {
                return recipes;
            }
        }
        return null;
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
     * Get required amount of process
     * @param itemStack Input item
     * @return Required amount of process
     */
    public int getRequiredProcessAmount(ItemStack itemStack) {
        for (Recipes recipes : processList) {
            if (recipes.input.isItemEqual(itemStack)) {
                return recipes.input.stackSize;
            }
        }
        return 1;
    }

    public static class Recipes {
        public ItemStack input;
        public ItemStack output;
        /** Value of EU consumption */
        public double requiresEnergy;
        public double xp;

        private Recipes(ItemStack input, ItemStack output, double requiresEnergy, float xp) {
            this.input = input;
            this.output = output;
            this.requiresEnergy = requiresEnergy;
            this.xp = xp;
        }
    }
}


