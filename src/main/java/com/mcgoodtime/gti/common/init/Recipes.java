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

        //vanilla recipe registry;

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
                GtiItems.roller,
                " A",
                "ABA",
                " A",
                'A', Items.iron_ingot,
                'B', IC2Items.getItem("ironFence")
        );
        GameRegistry.addRecipe(
                GtiItems.heatInsulationMaterial,
                "AAA",
                "AAA",
                "BBB",
                'A', IC2Items.getItem("rubber"),
                'B', IC2Items.getItem("advIronIngot"));

        GameRegistry.addRecipe(
                new ItemStack(GtiBlocks.airBrakeCasing),
                "AAA",
                "BBB",
                "ACA",
                'A', IC2Items.getItem("plateiron"),
                'B', GtiItems.airBrakeUnit,
                'C', Items.bucket
        );
        GameRegistry.addRecipe(
                GtiItems.airBrakeUnit,
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
                GtiItems.dustIridium,
                GtiItems.smallDustIridium,
                GtiItems.smallDustIridium,
                GtiItems.smallDustIridium,
                GtiItems.smallDustIridium,
                GtiItems.smallDustIridium,
                GtiItems.smallDustIridium,
                GtiItems.smallDustIridium,
                GtiItems.smallDustIridium,
                GtiItems.smallDustIridium
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
                GtiItems.getItems(GtiItems.redstoneModule, 2),
                "ABA",
                "BCB",
                "ABA",
                'A', Items.redstone,
                'B', IC2Items.getItem("tinCableItem"),
                'C', IC2Items.getItem("plateiron")
        );

        GameRegistry.addRecipe(
                GtiItems.getItems(GtiItems.electronicCircuitCore, 3),
                "AAA",
                "BBB",
                "BBB",
                'A', IC2Items.getItem("plateiron"),
                'B', IC2Items.getItem("tinCableItem")
        );
        GameRegistry.addRecipe(
                GtiItems.getItems(GtiItems.electronicCircuitControl, 2),
                " A ",
                "BCB",
                'A', Blocks.lever,
                'B', IC2Items.getItem("tinCableItem"),
                'C', Blocks.stone_button
        );
        GameRegistry.addRecipe(
                GtiItems.lazuliModule,
                "ABA",
                "CDC",
                "ABA",
                'A', IC2Items.getItem("goldCableItem"),
                'B', GtiItems.heatInsulationPlate,
                'C', Blocks.lapis_block,
                'D', GtiItems.redstoneModule
        );
        GameRegistry.addRecipe(
                GtiItems.pulseElectronicCircuitCore,
                "AAA",
                "AAA",
                "BC ",
                'A', GtiItems.redstoneModule,
                'B', GtiItems.heatInsulationPlate,
                'C', GtiItems.electronicCircuitCore
        );
        GameRegistry.addRecipe(
                GtiItems.pulseElectronicCircuitControl,
                "AAA",
                "BAC",
                "BDC",
                'A', GtiItems.redstoneModule,
                'B', GtiItems.pulseElectronicCircuitCore,
                'C', GtiItems.heatInsulationPlate,
                'D', GtiItems.electronicCircuitControl
        );
        GameRegistry.addRecipe(
                GtiItems.cyclotronParticleAccelerator,
                "A A",
                "BCB",
                "ADA",
                'A', IC2Items.getItem("glassFiberCableItem"),
                'B', GtiItems.redstoneModule,
                'C', GtiItems.lazuliModule,
                'D', IC2Items.getItem("teslaCoil")
        );
        GameRegistry.addShapelessRecipe(
                GtiItems.calculateUnit,
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
                GtiItems.calculateChunk,
                GtiItems.calculateUnit,
                GtiItems.calculateUnit,
                GtiItems.calculateUnit,
                GtiItems.calculateUnit,
                GtiItems.calculateUnit,
                GtiItems.calculateUnit,
                GtiItems.calculateUnit,
                GtiItems.calculateUnit,
                GtiItems.calculateUnit
        );
        GameRegistry.addShapelessRecipe(
                GtiItems.calculateArray,
                GtiItems.calculateChunk,
                GtiItems.calculateChunk,
                GtiItems.calculateChunk,
                GtiItems.calculateChunk,
                GtiItems.calculateChunk,
                GtiItems.calculateChunk,
                GtiItems.calculateChunk,
                GtiItems.calculateChunk,
                GtiItems.calculateChunk
        );
        GameRegistry.addShapelessRecipe(
                GtiItems.floatPointCalculationsRegion,
                GtiItems.calculateArray,
                GtiItems.calculateArray,
                GtiItems.calculateArray,
                GtiItems.calculateArray,
                GtiItems.calculateArray,
                GtiItems.calculateArray,
                GtiItems.calculateArray,
                GtiItems.calculateArray,
                GtiItems.calculateArray
        );
        GameRegistry.addRecipe(
                GtiItems.parallelSpaceConverter,
                "A A",
                " B ",
                "A A",
                'A', GtiItems.floatPointCalculationsRegion,
                'B', GtiItems.enderCalculationCrystal
        );
        GameRegistry.addRecipe(
                GtiItems.uuMatterCore,
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
                GtiItems.obsidianMechanicalFrame,
                "AAA",
                "A A",
                "AAA",
                'A', GtiItems.obsidianPlateGravityField
        );
        GameRegistry.addRecipe(
                GtiItems.obsidianMechanicalCasing,
                "ABA",
                "ACA",
                'A', GtiItems.pulseElectronicCircuitCore,
                'B', IC2Items.getItem("advancedMachine"),
                'C', GtiItems.obsidianMechanicalFrame
        );
        GameRegistry.addRecipe(
                GtiItems.enderCalculationCrystal,
                "AAA",
                "BCB",
                "BCB",
                'A', IC2Items.getItem("lapotronCrystal"),
                'B', GtiItems.calculateArray,
                'C', Items.ender_pearl
        );
        GameRegistry.addRecipe(
                GtiItems.millTeeth,
                "ABC",
                'A', Items.flint,
                'B', Blocks.stone,
                'C', Blocks.brick_block
        );
        GameRegistry.addRecipe(
                GtiItems.millWheel,
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
        GameRegistry.addRecipe(
                GtiItems.carbonTube,
                "AB",
                "B",
                'A', Items.redstone,
                'B', IC2Items.getItem("coalDust")
        );
        GameRegistry.addRecipe(
                GtiItems.rigidPaper,
                "A",
                "B",
                "A",
                'A', Items.paper,
                'B', Items.sugar
        );
        GameRegistry.addShapelessRecipe(
                new ItemStack(Items.book),
                GtiItems.rigidPaper,
                GtiItems.rigidPaper,
                Items.leather
        );
        GameRegistry.addRecipe(
                GtiItems.getItems(GtiItems.rigidPaperPack, 2),
                " A ",
                "A A",
                " A ",
                'A', GtiItems.rigidPaper
        );

        //smelting registry
        GameRegistry.addSmelting(GtiBlocks.oreIridium, GtiItems.ingotIridium, XP);
        GameRegistry.addSmelting(GtiItems.cleanedCrushedIridium, GtiItems.ingotIridium, XP);
        GameRegistry.addSmelting(GtiItems.dustIridium, GtiItems.ingotIridium, XP);
        GameRegistry.addSmelting(GtiItems.crushedIridium, GtiItems.ingotIridium, XP);
        GameRegistry.addSmelting(IC2Items.getItem("iridiumOre"), GtiItems.ingotIridium, XP);

        //ic2 recipe registry
        ic2.api.recipe.Recipes.compressor.addRecipe(
        		new RecipeInputItemStack(GtiItems.getItems(GtiItems.smallCompressedWaterHyacinth, 8)),
        		null,
        		new ItemStack(GtiBlocks.compressedWaterHyacinth)
        );
        ic2.api.recipe.Recipes.compressor.addRecipe(
        		new RecipeInputItemStack(new ItemStack(GtiBlocks.waterHyacinth, 8)),
        		null,
        		GtiItems.smallCompressedWaterHyacinth
        );
        ic2.api.recipe.Recipes.metalformerRolling.addRecipe(
                new RecipeInputItemStack(new ItemStack(Items.diamond)),
                null,
                GtiItems.diamondPlate
        );
        ic2.api.recipe.Recipes.metalformerRolling.addRecipe(
               		new RecipeInputItemStack(GtiItems.heatInsulationMaterial),
               		null,
               		GtiItems.heatInsulationPlate
        );
        ic2.api.recipe.Recipes.compressor.addRecipe(
                new RecipeInputItemStack(GtiItems.getItems(GtiItems.diamondPlate, 9)),
                null,
                GtiItems.denseDiamondPlate
        );
        ic2.api.recipe.Recipes.compressor.addRecipe(
                new RecipeInputItemStack(GtiItems.getItems(GtiItems.smallDustIridium, 8)),
                null,
                GtiItems.ingotIridium
        );
        ic2.api.recipe.Recipes.macerator.addRecipe(
                new RecipeInputItemStack(new ItemStack(GtiBlocks.oreIridium)),
                null,
                GtiItems.getItems(GtiItems.crushedIridium, 2)
        );
        ic2.api.recipe.Recipes.macerator.addRecipe(
                new RecipeInputItemStack(GtiItems.ingotIridium),
                null,
                GtiItems.dustIridium
        );
        ic2.api.recipe.Recipes.compressor.addRecipe(
                new RecipeInputItemStack(IC2Items.getItem("diamondDust"), 3),
                null,
                GtiItems.carbonCrystal
        );
        ic2.api.recipe.Recipes.compressor.addRecipe(
                new RecipeInputItemStack(IC2Items.getItem("denseplateobsidian"), 8),
                null,
                GtiItems.obsidianPlateGravityField
        );

        ItemStack doubleSmallTinDust = Ic2Items.smallTinDust.copy();
        doubleSmallTinDust.stackSize = 2;
        ic2.api.recipe.Recipes.oreWashing.addRecipe(
                new RecipeInputItemStack(GtiItems.crushedIridium),
                null,
                GtiItems.cleanedCrushedIridium,
                doubleSmallTinDust
        );
        ic2.api.recipe.Recipes.centrifuge.addRecipe(
                new RecipeInputItemStack(GtiItems.cleanedCrushedIridium),
                null,
                GtiItems.dustIridium,
                GtiItems.getItems(GtiItems.smallDustIridium, 2)
        );
        ic2.api.recipe.Recipes.cannerBottle.addRecipe(
                new RecipeInputItemStack(GtiItems.rigidPaperPack),
                new RecipeInputItemStack(new ItemStack(GtiItems.salt, 9)),
                new ItemStack(GtiItems.packagedSalt)
        );
    }

    private static void disable() {
        disableRecipes(Ic2Items.massFabricator);
        disableRecipes(IC2Items.getItem("iridiumPlate"));
    }

    /**
     * Disable recipes.
     * @param itemStack Disable all recipes of this item.
     */
    @SuppressWarnings("unchecked")
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

    @SuppressWarnings({"unchecked", "SuspiciousMethodCalls"})
    public static void disableSmelting(ItemStack itemStack) {
        Map<List<Integer>, ItemStack> smelting = FurnaceRecipes.smelting().getSmeltingList();
        smelting.remove(itemStack);
    }
}
