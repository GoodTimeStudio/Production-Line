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

import cpw.mods.fml.common.registry.GameRegistry;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.core.Ic2Items;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

/**
 * The class for loading all the recipes of GoodTime-Industrial. Migrated from old loaders.
 *
 * @author liach
 */
public class Recipes {
    private static final float XP = 2F;

    /** Load recipes of GoodTime-Industrial */
    public static void init() {
        //vanilla recipe registery
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.diamondApple),
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
        GameRegistry.addSmelting(GtiBlocks.oreIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(GtiItems.cleanedCrushedIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(GtiItems.dustIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(GtiItems.crushedIridium, new ItemStack(GtiItems.ingotIridium), XP);

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
}
