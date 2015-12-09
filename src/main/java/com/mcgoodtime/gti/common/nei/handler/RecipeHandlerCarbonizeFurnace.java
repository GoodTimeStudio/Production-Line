package com.mcgoodtime.gti.common.nei.handler;

import codechicken.nei.NEIServerUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import com.mcgoodtime.gti.client.gui.GuiCarbonizeFurnace;
import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.recipes.CarbonizeFurnaceRecipes;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import java.awt.*;
import java.util.Collections;
import java.util.List;

/**
 * Created by BestOwl on 2015.11.29.0029.
 *
 * @author BestOwl
 */
public class RecipeHandlerCarbonizeFurnace extends TemplateRecipeHandler {

    public void loadTransferRects() {
        this.transferRects.add(new RecipeTransferRect(new Rectangle(74, 23, 24, 18), "carbonizeSmelting"));
    }

    public Class<? extends GuiContainer> getGuiClass() {
        return GuiCarbonizeFurnace.class;
    }

    public String getRecipeName() {
        return StatCollector.translateToLocal(Gti.GUI_PREFIX + "CarbonizeFurnace");
    }

    public void loadCraftingRecipes(String outputId, Object... results) {
        if (outputId.equals("carbonizeSmelting")) {
            for (CarbonizeFurnaceRecipes.Recipes recipes : CarbonizeFurnaceRecipes.instance.getProcessRecipesList()) {
                this.arecipes.add(new SmeltingPair(recipes.input, recipes.output));
            }
        } else {
            super.loadCraftingRecipes(outputId, results);
        }
    }

    public void loadCraftingRecipes(ItemStack result) {
        for (CarbonizeFurnaceRecipes.Recipes recipes : CarbonizeFurnaceRecipes.instance.getProcessRecipesList()) {
            if(NEIServerUtils.areStacksSameTypeCrafting(recipes.output, result)) {
                this.arecipes.add(new SmeltingPair(recipes.input, recipes.output));
            }
        }
    }

    public void loadUsageRecipes(ItemStack ingredient) {
        for (CarbonizeFurnaceRecipes.Recipes recipes : CarbonizeFurnaceRecipes.instance.getProcessRecipesList()) {
            if (recipes.input.isItemEqual(ingredient)) {
                this.arecipes.add(new SmeltingPair(recipes.input, recipes.output));
            }
        }
    }

    public String getGuiTexture() {
        return Gti.RESOURCE_DOMAIN + ":" + "textures/gui/GuiCarbonizeFurnace.png";
    }

    public void drawExtras(int recipe) {
        this.drawProgressBar(51, 25, 176, 0, 14, 14, 48, 3);
        this.drawProgressBar(74, 23, 176, 14, 24, 16, 48, 0);
    }

    public String getOverlayIdentifier() {
        return "carbonizeSmelting";
    }

    public class SmeltingPair extends CachedRecipe {
        PositionedStack ingred;
        PositionedStack result;

        public SmeltingPair(ItemStack ingred, ItemStack result) {
            super();
            this.ingred = new PositionedStack(ingred, 51, 5);
            this.result = new PositionedStack(result, 111, 24);
        }

        public List<PositionedStack> getIngredients() {
            return this.getCycledIngredients(RecipeHandlerCarbonizeFurnace.this.cycleticks / 48, Collections.singletonList(this.ingred));
        }

        public PositionedStack getResult() {
            return this.result;
        }

        public PositionedStack getOtherStack() {
            return null;
        }
    }
}
