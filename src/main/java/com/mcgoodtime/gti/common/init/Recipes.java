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
import ic2.api.item.IC2Items;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.core.Ic2Items;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
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
        //disable recipes
        disable();

        //vanilla recipe registry
        GameRegistry.addRecipe(
                new ItemStack(GtiBlocks.carbonizeFurnace),
                "ABA",
                "CDC",
                "EEE",
                'A', IC2Items.getItem("electronicCircuit"),
                'B', GtiBlocks.airBrakeCasing,
                'C', GtiItems.airBrakeUnit,
                'D', IC2Items.getItem("electroFurnace"),
                'E', IC2Items.getItem("plateiron")
        );
        
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.roller),
                " A",
                "ABA",
                " A",
                'A', Items.iron_ingot,
                'B', IC2Items.getItem("ironFence")
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.heatInsulationMaterial),
                "AAA",
                "AAA",
                "BBB",
                'A', IC2Items.getItem("rubber"),
                'B', IC2Items.getItem("advIronIngot"));

        GameRegistry.addRecipe(
                new ItemStack(GtiItems.airBrakeCasing),
                "AAA",
                "BBB",
                "ACA",
                'A', IC2Items.getItem("plateiron"),
                'B', GtiItems.airBrakeUnit,
                'C', Items.bucket
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.airBrakeUnit),
                "AY ",
                "YY ",
                " YY",
                'A', IC2Items.getItem("advIronIngot"),
                'Y', IC2Items.getItem("rubber")
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
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.ironTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', Items.iron_ingot
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.bronzeTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("bronzeIngot")
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.leadTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("leadIngot")
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.refinedIronTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("advIronIngot")
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.advancedAlloyTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("advancedAlloy")
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.carbonTreetap),
                " XY",
                "XXX",
                "XY ",
                'X', IC2Items.getItem("carbonPlate"),
                'Y', IC2Items.getItem("carbonMesh")
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.redstoneModule, 2),
                "ABA",
                "BCB",
                "ABA",
                'A', Items.redstone,
                'B', IC2Items.getItem("tinCableItem"),
                'C', IC2Items.getItem("plateiron")
        );

        GameRegistry.addRecipe(
                new ItemStack(GtiItems.electronicCircuitCore, 3),
                "AAA",
                "BBB",
                "BBB",
                'A', IC2Items.getItem("plateiron"),
                'B', IC2Items.getItem("tinCableItem")
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.electronicCircuitControl, 2),
                " A ",
                "BCB",
                'A', Blocks.lever,
                'B', IC2Items.getItem("tinCableItem"),
                'C', Blocks.stone_button
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.lazuliModule),
                "ABA",
                "CDC",
                "ABA",
                'A', IC2Items.getItem("goldCableItem"),
                'B', GtiItems.heatInsulationPlate,
                'C', Blocks.lapis_block,
                'D', GtiItems.redstoneModule
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.pulseElectronicCircuitCore),
                "AAA",
                "AAA",
                "BC ",
                'A', GtiItems.redstoneModule,
                'B', GtiItems.heatInsulationPlate,
                'C', GtiItems.electronicCircuitCore
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.pulseElectronicCircuitControl),
                "AAA",
                "BAC",
                "BDC",
                'A', GtiItems.redstoneModule,
                'B', GtiItems.pulseElectronicCircuitCore,
                'C', GtiItems.heatInsulationPlate,
                'D', GtiItems.electronicCircuitControl
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.cyclotronParticleAccelerator),
                "A A",
                "BCB",
                "ADA",
                'A', IC2Items.getItem("glassFiberCableItem"),
                'B', GtiItems.redstoneModule,
                'C', GtiItems.lazuliModule,
                'D', IC2Items.getItem("teslaCoil")
        );
        GameRegistry.addShapelessRecipe(
                new ItemStack(GtiItems.calculateUnit),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button),
                new ItemStack(Blocks.stone_button)
        );
        GameRegistry.addShapelessRecipe(
                new ItemStack(GtiItems.calculateChunk),
                new ItemStack(GtiItems.calculateUnit),
                new ItemStack(GtiItems.calculateUnit),
                new ItemStack(GtiItems.calculateUnit),
                new ItemStack(GtiItems.calculateUnit),
                new ItemStack(GtiItems.calculateUnit),
                new ItemStack(GtiItems.calculateUnit),
                new ItemStack(GtiItems.calculateUnit),
                new ItemStack(GtiItems.calculateUnit),
                new ItemStack(GtiItems.calculateUnit)
        );
        GameRegistry.addShapelessRecipe(
                new ItemStack(GtiItems.calculateArray),
                new ItemStack(GtiItems.calculateChunk),
                new ItemStack(GtiItems.calculateChunk),
                new ItemStack(GtiItems.calculateChunk),
                new ItemStack(GtiItems.calculateChunk),
                new ItemStack(GtiItems.calculateChunk),
                new ItemStack(GtiItems.calculateChunk),
                new ItemStack(GtiItems.calculateChunk),
                new ItemStack(GtiItems.calculateChunk),
                new ItemStack(GtiItems.calculateChunk)
        );
        GameRegistry.addShapelessRecipe(
                new ItemStack(GtiItems.floatPointCalculationsRegion),
                new ItemStack(GtiItems.calculateArray),
                new ItemStack(GtiItems.calculateArray),
                new ItemStack(GtiItems.calculateArray),
                new ItemStack(GtiItems.calculateArray),
                new ItemStack(GtiItems.calculateArray),
                new ItemStack(GtiItems.calculateArray),
                new ItemStack(GtiItems.calculateArray),
                new ItemStack(GtiItems.calculateArray),
                new ItemStack(GtiItems.calculateArray)
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.parallelSpaceConverter),
                "A A",
                " B ",
                "A A",
                'A', GtiItems.floatPointCalculationsRegion,
                'B', GtiItems.enderCalculationCrystal
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.uuMatterCore),
                "ABA",
                "CDC",
                "ABA",
                'A', GtiItems.cyclotronParticleAccelerator,
                'B', GtiItems.lazuliModule,
                'C', GtiItems.pulseElectronicCircuitControl,
                'D', GtiItems.parallelSpaceConverter
        );
        GameRegistry.addRecipe(
                Ic2Items.massFabricator,
                "ACA",
                "BDB",
                "ACA",
                'A', GtiItems.lazuliModule,
                'B', IC2Items.getItem("teleporter"),
                'C', GtiItems.pulseElectronicCircuitControl,
                'D', GtiItems.uuMatterCore
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.obsidianMechanicalFrame),
                "AAA",
                "A A",
                "AAA",
                'A', GtiItems.obsidianPlateGravityField
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.obsidianMechanicalCasing),
                "ABA",
                "ACA",
                'A', GtiItems.pulseElectronicCircuitCore,
                'B', IC2Items.getItem("advancedMachine"),
                'C', GtiItems.obsidianMechanicalFrame
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.enderCalculationCrystal),
                "AAA",
                "BCB",
                "BCB",
                'A', IC2Items.getItem("lapotronCrystal"),
                'B', GtiItems.calculateArray,
                'C', Items.ender_pearl
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.millTeeth),
                "ABC",
                'A', Items.flint,
                'B', Blocks.stone,
                'C', Blocks.brick_block
        );
        GameRegistry.addRecipe(
                new ItemStack(GtiItems.millWheel),
                "AAA",
                "ABA",
                "AAA",
                'A', GtiItems.millTeeth,
                'B', IC2Items.getItem("plateiron")
        );
        GameRegistry.addRecipe(
                IC2Items.getItem("iridiumPlate"),
                "ABA",
                "BCB",
                "ABA",
                'A', GtiItems.ingotIridium,
                'B', IC2Items.getItem("advancedAlloy"),
                'C', GtiItems.denseDiamondPlate
        );

        //smelting registry
        GameRegistry.addSmelting(GtiBlocks.oreIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(GtiItems.cleanedCrushedIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(GtiItems.dustIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(GtiItems.crushedIridium, new ItemStack(GtiItems.ingotIridium), XP);
        GameRegistry.addSmelting(Blocks.log2, new ItemStack(GtiItems.bambooCharcoal), XP);
        GameRegistry.addSmelting(IC2Items.getItem("iridiumOre"), new ItemStack(GtiItems.ingotIridium), XP);

        //ic2 recipe registry
        ic2.api.recipe.Recipes.compressor.addRecipe(
        		new RecipeInputItemStack(new ItemStack(GtiItems.smallCompressedWaterHyacinth, 8)),
        		null,
        		new ItemStack(GtiBlocks.compressedWaterHyacinth)
        );
        ic2.api.recipe.Recipes.compressor.addRecipe(
        		new RecipeInputItemStack(new ItemStack(GtiBlocks.waterHyacinth, 8)),
        		null,
        		new ItemStack(GtiItems.smallCompressedWaterHyacinth)
        );
        ic2.api.recipe.Recipes.metalformerRolling.addRecipe(
                new RecipeInputItemStack(new ItemStack(Items.diamond)),
                null,
                new ItemStack(GtiItems.diamondPlate)
        );
        ic2.api.recipe.Recipes.metalformerRolling.addRecipe(
               		new RecipeInputItemStack(new ItemStack(GtiItems.heatInsulationMaterial)),
               		null,
               		new ItemStack (GtiItems.heatInsulationPlate));
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
        ic2.api.recipe.Recipes.compressor.addRecipe(
                new RecipeInputItemStack(IC2Items.getItem("diamondDust"), 3),
                null,
                new ItemStack(GtiItems.carbonCrystal)
        );
        ic2.api.recipe.Recipes.compressor.addRecipe(
                new RecipeInputItemStack(IC2Items.getItem("denseplateobsidian"), 8),
                null,
                new ItemStack(GtiItems.obsidianPlateGravityField)
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

    private static void disable() {
        disableRecipes(Ic2Items.massFabricator);
        disableRecipes(IC2Items.getItem("iridiumPlate"));
    }

    /**
     * Disable recipes.
     * @param itemStack Disable all recipes of this item. 绂佺敤杩欎釜鐗╁搧鐨勬墍鏈夊悎鎴�
     */
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
