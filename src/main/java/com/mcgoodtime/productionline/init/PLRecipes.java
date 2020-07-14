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
package com.mcgoodtime.productionline.init;

import com.mcgoodtime.productionline.items.ItemStacks;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.List;
import java.util.Map;

import ic2.api.item.IC2Items;

//import ic2.core.AdvRecipe;

/**
 * The class for loading all the recipes of GoodTime-Industrial. Migrated from old loaders.
 *
 * @author liach
 */
public class PLRecipes {
    private static final float XP = 2F;

    /**
     * Load recipes of GoodTime-Industrial.
     */
    public static void init() {
        //disable recipes
        //disable();

        //vanilla recipe registry;
/*
        GameRegistry.addRecipe(
                ItemStacks.copyStack(PLBlocks.opticalGlass, 5),
                "ABA",
                "BAB",
                "ABA",
                'A', Blocks.QUARTZ_BLOCK,
                'B', Blocks.GLASS
        );
        GameRegistry.addRecipe(
                PLItems.advSolarLensUnit,
                "A A",
                "A A",
                " A ",
                'A', PLBlocks.opticalGlass
        );
        GameRegistry.addRecipe(
                PLItems.advSolarLensGroup,
                "A A",
                "ABA",
                "CAC",
                'A', PLItems.advSolarLensUnit,
                'B', IC2Items.getItem("dust", "diamond"),
                'C', IC2Items.getItem("crafting", "carbon_plate")
        );
        GameRegistry.addRecipe(
                PLItems.advSolarLensCluster,
                "ABA",
                "ACA",
                "DAD",
                'A', PLItems.advSolarLensGroup,
                'B', PLItems.dustIridium,
                'C', IC2Items.getItem("dust", "energium")
        );
        GameRegistry.addRecipe(
                PLItems.heatInsulationMaterial,
                "AAA",
                "AAA",
                "BBB",
                'A', IC2Items.getItem("rubber"),
                'B', IC2Items.getItem("advIronIngot"));

        GameRegistry.addRecipe(
                new ItemStack(PLItems.diamondApple, 1, 1),
                "DDD",
                "DAD",
                "DDD",
                'D', PLItems.diamondApple,
                'A', Blocks.DIAMOND_BLOCK
        );*/

        //smelting registry
        GameRegistry.addSmelting(PLBlocks.oreIridium, PLItems.ingotIridium, XP);
        GameRegistry.addSmelting(PLItems.dustIridium, PLItems.ingotIridium, XP);
        GameRegistry.addSmelting(IC2Items.getItem("iridiumOre"), PLItems.ingotIridium, XP);

        //ic2 recipe registry
        /*
        Recipes.compressor.addRecipe(
        		new RecipeInputItemStack(PLItems.getItems(PLItems.smallCompressedWaterHyacinth, 8)),
        		null,
        		PLBlocks.compressedWaterHyacinth
        );
        Recipes.compressor.addRecipe(
        		new RecipeInputItemStack(new ItemStack(PLBlocks.waterHyacinth, 8)),
        		null,
        		PLItems.smallCompressedWaterHyacinth
        );
        Recipes.metalformerRolling.addRecipe(
                new RecipeInputItemStack(new ItemStack(Items.diamond)),
                null,
                PLItems.diamondPlate
        );
        Recipes.metalformerRolling.addRecipe(
               		new RecipeInputItemStack(PLItems.heatInsulationMaterial),
               		null,
               		PLItems.heatInsulationPlate
        );
        Recipes.compressor.addRecipe(
                new RecipeInputItemStack(PLItems.getItems(PLItems.diamondPlate, 9)),
                null,
                PLItems.denseDiamondPlate
        );
        Recipes.compressor.addRecipe(
                new RecipeInputItemStack(PLItems.getItems(PLItems.smallDustIridium, 8)),
                null,
                PLItems.ingotIridium
        );
        Recipes.macerator.addRecipe(
                new RecipeInputItemStack(new ItemStack(PLBlocks.oreIridium)),
                null,
                PLItems.getItems(PLItems.crushedIridium, 2)
        );
        Recipes.macerator.addRecipe(
                new RecipeInputItemStack(PLItems.ingotIridium),
                null,
                PLItems.dustIridium
        );
        Recipes.compressor.addRecipe(
                new RecipeInputItemStack(IC2Items.getItem("diamondDust"), 3),
                null,
                PLItems.carbonCrystal
        );
        Recipes.compressor.addRecipe(
                new RecipeInputItemStack(IC2Items.getItem("denseplateobsidian"), 8),
                null,
                PLItems.obsidianPlateGravityField
        );
        NBTTagCompound oreWash = new NBTTagCompound();
        oreWash.setInteger("amount", 1000);
        Recipes.oreWashing.addRecipe(
                new RecipeInputItemStack(PLItems.crushedIridium),
                oreWash,
                PLItems.cleanedCrushedIridium,
                StackUtil.copyWithSize(Ic2Items.smallTinDust, 2)
        );
        Recipes.centrifuge.addRecipe(
                new RecipeInputItemStack(PLItems.cleanedCrushedIridium),
                null,
                PLItems.dustIridium,
                PLItems.getItems(PLItems.smallDustIridium, 2)
        );
        Recipes.cannerBottle.addRecipe(
                new RecipeInputItemStack(PLItems.rigidPaperPack),
                new RecipeInputItemStack(new ItemStack(PLItems.salt, 9)),
                new ItemStack(PLItems.packagedSalt)
        );
        AdvRecipe.addAndRegister(
                PLBlocks.cseu,
                "ABA",
                "CCC",
                "CCC",
                'A', IC2Items.getItem("insulatedGoldCableItem"),
                'B', IC2Items.getItem("advancedMachine"),
                'C', PLItems.ceu
        );*/
    }
/*
    private static void disable() {
        disableRecipes(Ic2Items.massFabricator);
        disableRecipes(IC2Items.getItem("iridiumPlate"));
    }*/

    /**
     * Disable recipes.
     *
     * @param itemStack Disable all recipes of this item.
     */
    @SuppressWarnings("unchecked")
    public static void disableRecipes(ItemStack itemStack) {/*
        List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
        for (int i = 0; i < recipeList.size(); i++) {
            IRecipe iRecipe = recipeList.get(i);
            ItemStack recipesResult = iRecipe.getRecipeOutput();
            if (ItemStack.areItemStacksEqual(itemStack, recipesResult)) {
                recipeList.remove(i--);
            }
        }*/
    }

    @SuppressWarnings({"unchecked", "SuspiciousMethodCalls"})
    public static void disableSmelting(ItemStack itemStack) {
        Map<ItemStack, ItemStack> smelting = FurnaceRecipes.instance().getSmeltingList();
        smelting.remove(itemStack);
    }
}
