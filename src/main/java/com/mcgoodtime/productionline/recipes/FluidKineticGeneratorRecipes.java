/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mcgoodtime.productionline.recipes;

import ic2.api.recipe.MachineRecipe;
import ic2.api.recipe.MachineRecipeResult;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

/**
 * Created by BestOwl on 2015.11.8.0008.
 *
 * @author BestOwl
 */
public class FluidKineticGeneratorRecipes extends RecipeBase {
    public static final FluidKineticGeneratorRecipes instance = new FluidKineticGeneratorRecipes();

    private FluidKineticGeneratorRecipes() {
        this.register("lava", 10);
        this.register("oil", 10);
        this.register("fuel", 5);
        this.register("biomass", 20);
        this.register("bioethanol", 10);
        this.register("ic2biogas", 10);
    }

    public void register(String fluidName, int amount) {
        if (FluidRegistry.getFluid(fluidName) != null) {
            processList.add(new RecipePartFluidKineticGenerator(FluidRegistry.getFluidStack(fluidName, amount)));
        }
    }

    /**
     * Returns the process result of an item.
     *
     */
    @Override
    public ItemStack getProcessResult(ItemStack itemStack) {
        return null;
    }

    /**
     * @return Whether this item can process
     */
    @Override
    public boolean canProcess(ItemStack itemStack) {/*
        FluidStack fluidStack = FluidContainerRegistry.getFluidForFilledItem(itemStack);
        if (fluidStack != null) {
            for (RecipePart recipePart : this.processList) {
                if (((RecipePartFluidKineticGenerator) recipePart).fluidStack.isFluidEqual(fluidStack)) {
                    return true;
                }
            }
        }
    */
        return false;
    }

    /**
     * Get required amount of process
     *
     * @param itemStack Input item
     * @return Required amount of process
     */
    @Override
    public int getRequiredProcessAmount(ItemStack itemStack) {
        return 1;
    }

    @Override
    public List<RecipePart> getProcessRecipesList() {
        return this.processList;
    }

    /**
     * @param itemStack Input item
     */
    @Override
    public RecipePart getRecipePart(ItemStack itemStack) {
        return null;
    }

    @Override
    public MachineRecipeResult apply(Object o, boolean b) {
        return null;
    }

    @Override
    public Iterable<? extends MachineRecipe> getRecipes() {
        return null;
    }

    public class RecipePartFluidKineticGenerator extends RecipePart {

        public FluidStack fluidStack;

        public RecipePartFluidKineticGenerator(FluidStack fluidStack) {
            this.fluidStack = fluidStack;
        }
    }
}
