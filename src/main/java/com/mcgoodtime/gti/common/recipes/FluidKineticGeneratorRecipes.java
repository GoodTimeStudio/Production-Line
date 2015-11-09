package com.mcgoodtime.gti.common.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BestOwl on 2015.11.8.0008.
 *
 * @author BestOwl
 */
public class FluidKineticGeneratorRecipes {
    public static final FluidKineticGeneratorRecipes instance = new FluidKineticGeneratorRecipes();
    /** The list of smelting results. */
    private List<Recipes> processList = new ArrayList<Recipes>();

    private FluidKineticGeneratorRecipes() {
        register(FluidRegistry.getFluidStack("lava", 10));
    }

    public void register(FluidStack input) {
        processList.add(new Recipes(input));
    }

    public static class Recipes {
        public FluidStack fluidStack;

        public Recipes(FluidStack input) {
            this.fluidStack = input;
        }
    }
}
