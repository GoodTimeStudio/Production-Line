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
package com.mcgoodtime.gti.common.init;

import com.mcgoodtime.gti.common.core.Gti;
import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.core.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;

import java.util.List;
import java.util.Map;

/**
 * The class for loading all the recipes of GoodTime-Industrial. Migrated from old loaders.
 *
 * @author liach
 */
public class Recipes {
    private static final float XP = 2F;

    /** Load recipes of GoodTime-Industrial.*/
    public static void init() {
        //vanilla recipe registry
        GameRegistry.addRecipe(
                new ItemStack(GtiBlocks.carbonizeFurnace),
                "ABA",
                "CDC",
                "EEE",
                'A', ic2.api.item.IC2Items.getItem("electronicCircuit"),
                'B', GtiItems.airBrakeCasing,
                'C', GtiItems.airBrakeUnit,
                'D', ic2.api.item.IC2Items.getItem("electroFurnace"),
                'E', ic2.api.item.IC2Items.getItem("plateiron")
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.airBrakeCasing),
                "AAA",
                "BBB",
                "ACA",
                'A', ic2.api.item.IC2Items.getItem("plateiron"),
                'B', GtiItems.airBrakeUnit,
                'C', Items.bucket
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.airBrakeUnit),
                "XY#",
                "YY#",
                "#YY",
                'X', ic2.api.item.IC2Items.getItem("advIronIngot"),
                'Y', ic2.api.item.IC2Items.getItem("rubber")
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.diamondApple, 1, 0),
                "DDD",
                "DAD",
                "DDD",
                'D', GtiItems.diamondPlate,
                'A', Items.apple
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.diamondApple, 1, 1),
                "DDD",
                "DAD",
                "DDD",
                'D', GtiItems.denseDiamondPlate,
                'A', Items.apple
        );
        GameRegistry.addShapelessRecipe(
                new ItemStack(GtiItems.dustIridium),
                new ItemStack(GtiItems.smallDustIridium),
                new ItemStack(GtiItems.smallDustIridium),
                new ItemStack(GtiItems.smallDustIridium),
                new ItemStack(GtiItems.smallDustIridium),
                new ItemStack(GtiItems.smallDustIridium),
                new ItemStack(GtiItems.smallDustIridium),
                new ItemStack(GtiItems.smallDustIridium),
                new ItemStack(GtiItems.smallDustIridium),
                new ItemStack(GtiItems.smallDustIridium)
        );

        //smelting registry
        GameRegistry.addSmelting(GtiBlocks.oreIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(GtiItems.cleanedCrushedIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(GtiItems.dustIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(GtiItems.crushedIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(Blocks.reeds, new ItemStack(GtiItems.bambooCharcoal), XP);

        //ic2 recipe registry
        ic2.api.recipe.Recipes.metalformerRolling.addRecipe(
                new RecipeInputItemStack(new ItemStack(Items.diamond)),
                null,
                new ItemStack(GtiItems.diamondPlate)
        );
        ic2.api.recipe.Recipes.compressor.addRecipe(
                new RecipeInputItemStack(new ItemStack(GtiItems.diamondPlate, 9)),
                null,
                new ItemStack(GtiItems.denseDiamondPlate)
        );
        ic2.api.recipe.Recipes.compressor.addRecipe(
                new RecipeInputItemStack(new ItemStack(GtiItems.smallDustIridium, 8)),
                null,
                new ItemStack(GtiItems.ingotIridium)
        );
        ic2.api.recipe.Recipes.macerator.addRecipe(
                new RecipeInputItemStack(new ItemStack(GtiBlocks.oreIridium)),
                null,
                new ItemStack(GtiItems.crushedIridium, 2)
        );
        ic2.api.recipe.Recipes.macerator.addRecipe(
                new RecipeInputItemStack(new ItemStack(GtiItems.ingotIridium)),
                null,
                new ItemStack(GtiItems.dustIridium)
        );

        ItemStack doubleSmallTinDust = Ic2Items.smallTinDust.copy();
        doubleSmallTinDust.stackSize = 2;
        ic2.api.recipe.Recipes.oreWashing.addRecipe(
                new RecipeInputItemStack(new ItemStack(GtiItems.crushedIridium)),
                null,
                new ItemStack(GtiItems.cleanedCrushedIridium),
                doubleSmallTinDust
        );
        ic2.api.recipe.Recipes.centrifuge.addRecipe(
                new RecipeInputItemStack(new ItemStack(GtiItems.cleanedCrushedIridium)),
                null,
                new ItemStack(GtiItems.dustIridium),
                new ItemStack(GtiItems.smallDustIridium, 2)
        );
    }

    public static void disableRecipes(ItemStack itemStack) {
        List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < recipeList.size(); i++) {
            IRecipe iRecipe = recipeList.get(i);
            ItemStack recipesResult = iRecipe.getRecipeOutput();
            if (ItemStack.areItemStacksEqual(itemStack, recipesResult)) {
                recipeList.remove(i--);
            }
        }
    }

    public static void disableSmelting(ItemStack itemStack) {
        Map<List<Integer>, ItemStack> smelting = FurnaceRecipes.smelting().getSmeltingList();
        smelting.remove(itemStack);
    }
}
