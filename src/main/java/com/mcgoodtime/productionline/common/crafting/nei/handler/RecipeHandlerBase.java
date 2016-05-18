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
package com.mcgoodtime.productionline.common.crafting.nei.handler;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.recipes.IProcessable;
import com.mcgoodtime.productionline.common.recipes.RecipePart;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by BestOwl on 2015.12.19.0019.
 *
 * @author BestOwl
 */
public abstract class RecipeHandlerBase extends TemplateRecipeHandler {

    public abstract IProcessable getRecipesList();

    public abstract String getRecipeNameForCrafting();

    public abstract String getRecipeID();

    @Override
    public abstract Class<? extends GuiContainer> getGuiClass();

    @Override
    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals(this.getRecipeID())) {
            for (RecipePart recipes : this.getRecipesList().getProcessRecipesList()) {
                this.arecipes.add(new CachedRecipePart(recipes.input, recipes.output));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        for (RecipePart recipes : this.getRecipesList().getProcessRecipesList()) {
            if(NEIServerUtils.areStacksSameTypeCrafting(recipes.output, result)) {
                this.arecipes.add(new CachedRecipePart(recipes.input, recipes.output));
            }
        }
    }

    @Override
    public void loadUsageRecipes(ItemStack ingredient) {
        for (RecipePart recipes : this.getRecipesList().getProcessRecipesList()) {
            if (recipes.input.isItemEqual(ingredient)) {
                this.arecipes.add(new CachedRecipePart(recipes.input, recipes.output));
            }
        }
    }

    @Override
    public abstract String getOverlayIdentifier();

    @Override
    public void loadTransferRects() {
        this.transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), this.getRecipeID()));
    }

    @Override
    public final String getRecipeName() {
        return StatCollector.translateToLocal(ProductionLine.GUI_PREFIX + this.getRecipeNameForCrafting());
    }

    @Override
    public void drawExtras(int recipe) {
        this.drawProgressBar(51, 25, 176, 0, 14, 14, 48, 3);
        this.drawProgressBar(74, 23, 176, 14, 24, 16, 48, 0);
    }

    protected Position getInputPosition() {
        return new Position(51, 5);
    }

    protected Position getOutputPosition() {
        return new Position(111, 24);
    }

    public class CachedRecipePart extends CachedRecipe {
        protected PositionedStack input;
        protected PositionedStack result;

        public CachedRecipePart(ItemStack input, ItemStack result) {
            super();
            Position position;
            position = RecipeHandlerBase.this.getInputPosition();
            this.input = new PositionedStack(input, position.x, position.y);
            position = RecipeHandlerBase.this.getOutputPosition();
            this.result = new PositionedStack(result, position.x, position.y);
        }

        @Override
        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(RecipeHandlerBase.this.cycleticks / 48, Collections.singletonList(this.input));
        }

        @Override
        public PositionedStack getResult() {
            return this.result;
        }

        @Override
        public PositionedStack getOtherStack() {
            return null;
        }
    }

    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
