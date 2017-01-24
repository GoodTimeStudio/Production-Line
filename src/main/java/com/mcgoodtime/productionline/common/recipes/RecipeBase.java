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
package com.mcgoodtime.productionline.common.recipes;

import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeOutput;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BestOwl on 2015.12.19.0019.
 *
 * @author BestOwl
 */
public abstract class RecipeBase implements IProcessable, IMachineRecipeManager {

    /** The list of recipes. */
    protected List<RecipePart> processList = new ArrayList<RecipePart>();

    /**
     * Returns the process result of an item.
     */
    @Override
    public ItemStack getProcessResult(ItemStack itemStack) {
        for (RecipePart recipes : processList) {
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
        for (RecipePart recipes : this.processList) {
            if (recipes.input.isItemEqual(itemStack)) {
                return true;
            }
        }
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
        for (RecipePart recipes : this.processList) {
            if (recipes.input.isItemEqual(itemStack)) {
                return recipes.input.stackSize;
            }
        }
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
        for (RecipePart recipes : this.processList) {
            if (recipes.input.isItemEqual(itemStack)) {
                return recipes;
            }
        }
        return null;
    }

    @Override
    public boolean addRecipe(IRecipeInput iRecipeInput, NBTTagCompound nbtTagCompound, boolean b, ItemStack... itemStacks) {
        processList.add(new RecipePart(iRecipeInput.getInputs().get(0), itemStacks[0])); //TODO Implement this
        return true;
    }

    @Override
    public RecipeOutput getOutputFor(ItemStack itemStack, boolean b) {
        return new RecipeOutput(new NBTTagCompound(), getRecipePart(itemStack).output);
    }

    @Override
    public Iterable<RecipeIoContainer> getRecipes() {
        throw new UnsupportedOperationException("not supported");
    }

    @Override
    public boolean isIterable() {
        return false;
    }
}
