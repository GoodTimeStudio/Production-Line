/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
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
package com.mcgoodtime.gti.common.recipes;

import com.mcgoodtime.gti.common.init.GtiItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * List of CarbonizeFurnace's Recipes
 *
 * @author BestOwl
 */
public class CarbonizeFurnaceRecipes {
    public static final CarbonizeFurnaceRecipes instance = new CarbonizeFurnaceRecipes();

    /** The default value after the process */
    private final float XP = 0.15F;
    /** The list of smelting results. */
    private List<Recipes> processList = new ArrayList<Recipes>();

    private CarbonizeFurnaceRecipes() {
        register(new ItemStack(Blocks.log), new ItemStack(Items.coal, 2, 1), 2000, XP);
        register(new ItemStack(Blocks.log2), new ItemStack(Items.coal, 2, 1), 2000, XP);
        register(new ItemStack(Blocks.planks, 2), new ItemStack(Items.coal, 1, 1), 1500, XP);
        register(new ItemStack(Items.reeds, 4), new ItemStack(GtiItems.bambooCharcoal), 1500, XP);
        register(new ItemStack(Items.water_bucket), new ItemStack(GtiItems.salt), 1100, XP);
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