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
package com.mcgoodtime.productionline.common.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
//import ic2.core.AdvRecipe;
import ic2.core.Ic2Items;
import ic2.core.util.StackUtil;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;
import java.util.Map;

/**
 * The class for loading all the recipes of GoodTime-Industrial. Migrated from old loaders.
 *
 * @author liach
 */
public class PLRecipes {
    private static final float XP = 2F;

    /** Load recipes of GoodTime-Industrial.*/
    public static void init() {
        //disable recipes
        //disable();

        //vanilla recipe registry;

        GameRegistry.addRecipe(
                PLBlocks.carbonizeFurnace,
                "ABA",
                "CDC",
                "EEE",
                'A', IC2Items.getItem("electronicCircuit"),
                'B', PLBlocks.airBrakeCasing,
                'C', PLItems.airBrakeUnit,
                'D', IC2Items.getItem("electroFurnace"),
                'E', IC2Items.getItem("plateiron")
        );

        GameRegistry.addRecipe(
                PLItems.roller,
                " A",
                "ABA",
                " A",
                'A', Items.IRON_INGOT,
                'B', IC2Items.getItem("ironFence")
        );
        GameRegistry.addRecipe(
                PLItems.heatInsulationMaterial,
                "AAA",
                "AAA",
                "BBB",
                'A', IC2Items.getItem("rubber"),
                'B', IC2Items.getItem("advIronIngot"));

        GameRegistry.addRecipe(
                new ItemStack(PLBlocks.airBrakeCasing),
                "AAA",
                "BBB",
                "ACA",
                'A', IC2Items.getItem("plateiron"),
                'B', PLItems.airBrakeUnit,
                'C', Items.BUCKET
        );
        GameRegistry.addRecipe(
                PLItems.airBrakeUnit,
                "AY ",
                "YY ",
                " YY",
                'A', IC2Items.getItem("advIronIngot"),
                'Y', IC2Items.getItem("rubber")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.diamondApple, 1, 0),
                "DDD",
                "DAD",
                "DDD",
                'D', PLItems.denseDiamondPlate,
                'A', Items.APPLE
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.diamondApple, 1, 1),
                "DDD",
                "DAD",
                "DDD",
                'D', PLItems.diamondApple,
                'A', Blocks.DIAMOND_BLOCK
        );
        GameRegistry.addShapelessRecipe(
                PLItems.dustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium,
                PLItems.smallDustIridium
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.ironTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', Items.IRON_INGOT
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.bronzeTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("bronzeIngot")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.leadTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("leadIngot")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.refinedIronTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("advIronIngot")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.advancedAlloyTreetap),
                " X ",
                "XXX",
                "X  ",
                'X', IC2Items.getItem("advancedAlloy")
        );
        GameRegistry.addRecipe(
                new ItemStack(PLItems.carbonTreetap),
                " XY",
                "XXX",
                "XY ",
                'X', IC2Items.getItem("carbonPlate"),
                'Y', IC2Items.getItem("carbonMesh")
        );
        GameRegistry.addRecipe(
                PLItems.getItems(PLItems.redstoneModule, 2),
                "ABA",
                "BCB",
                "ABA",
                'A', Items.REDSTONE,
                'B', IC2Items.getItem("tinCableItem"),
                'C', IC2Items.getItem("plateiron")
        );

        GameRegistry.addRecipe(
                PLItems.getItems(PLItems.electronicCircuitCore, 3),
                "AAA",
                "BBB",
                "BBB",
                'A', IC2Items.getItem("plateiron"),
                'B', IC2Items.getItem("tinCableItem")
        );
        GameRegistry.addRecipe(
                PLItems.getItems(PLItems.electronicCircuitControl, 2),
                " A ",
                "BCB",
                'A', Blocks.LEVER,
                'B', IC2Items.getItem("tinCableItem"),
                'C', Blocks.STONE_BUTTON
        );
        GameRegistry.addRecipe(
                PLItems.lazuliModule,
                "ABA",
                "CDC",
                "ABA",
                'A', IC2Items.getItem("goldCableItem"),
                'B', PLItems.heatInsulationPlate,
                'C', Blocks.LAPIS_BLOCK,
                'D', PLItems.redstoneModule
        );
        GameRegistry.addRecipe(
                PLItems.pulseElectronicCircuitCore,
                "AAA",
                "AAA",
                "BC ",
                'A', PLItems.redstoneModule,
                'B', PLItems.heatInsulationPlate,
                'C', PLItems.electronicCircuitCore
        );
        GameRegistry.addRecipe(
                PLItems.pulseElectronicCircuitControl,
                "AAA",
                "BAC",
                "BDC",
                'A', PLItems.redstoneModule,
                'B', PLItems.pulseElectronicCircuitCore,
                'C', PLItems.heatInsulationPlate,
                'D', PLItems.electronicCircuitControl
        );
        GameRegistry.addRecipe(
                PLItems.cyclotronParticleAccelerator,
                "A A",
                "BCB",
                "ADA",
                'A', IC2Items.getItem("glassFiberCableItem"),
                'B', PLItems.redstoneModule,
                'C', PLItems.lazuliModule,
                'D', IC2Items.getItem("teslaCoil")
        );
        GameRegistry.addShapelessRecipe(
                PLItems.calculateUnit,
                new ItemStack(Blocks.STONE_BUTTON),
                new ItemStack(Blocks.STONE_BUTTON),
                new ItemStack(Blocks.STONE_BUTTON),
                new ItemStack(Blocks.STONE_BUTTON),
                new ItemStack(Blocks.STONE_BUTTON),
                new ItemStack(Blocks.STONE_BUTTON),
                new ItemStack(Blocks.STONE_BUTTON),
                new ItemStack(Blocks.STONE_BUTTON),
                new ItemStack(Blocks.STONE_BUTTON)
        );
        GameRegistry.addShapelessRecipe(
                PLItems.calculateChunk,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit,
                PLItems.calculateUnit
        );
        GameRegistry.addShapelessRecipe(
                PLItems.calculateArray,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk,
                PLItems.calculateChunk
        );
        GameRegistry.addShapelessRecipe(
                PLItems.floatPointCalculationsRegion,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray,
                PLItems.calculateArray
        );
        GameRegistry.addRecipe(
                PLItems.parallelSpaceConverter,
                "A A",
                " B ",
                "A A",
                'A', PLItems.floatPointCalculationsRegion,
                'B', PLItems.enderCalculationCrystal
        );
        GameRegistry.addRecipe(
                PLItems.uuMatterCore,
                "ABA",
                "CDC",
                "ABA",
                'A', PLItems.cyclotronParticleAccelerator,
                'B', PLItems.lazuliModule,
                'C', PLItems.pulseElectronicCircuitControl,
                'D', PLItems.parallelSpaceConverter
        );/*
        GameRegistry.addRecipe(
                Ic2Items.massFabricator,
                "ACA",
                "BDB",
                "ACA",
                'A', PLItems.lazuliModule,
                'B', IC2Items.getItem("teleporter"),
                'C', PLItems.pulseElectronicCircuitControl,
                'D', PLItems.uuMatterCore
        );*/
        GameRegistry.addRecipe(
                PLItems.obsidianMechanicalFrame,
                "AAA",
                "A A",
                "AAA",
                'A', PLItems.obsidianPlateGravityField
        );
        GameRegistry.addRecipe(
                PLItems.obsidianMechanicalCasing,
                "ABA",
                "ACA",
                'A', PLItems.pulseElectronicCircuitCore,
                'B', IC2Items.getItem("advancedMachine"),
                'C', PLItems.obsidianMechanicalFrame
        );
        GameRegistry.addRecipe(
                PLItems.enderCalculationCrystal,
                "AAA",
                "BCB",
                "BCB",
                'A', IC2Items.getItem("lapotronCrystal"),
                'B', PLItems.calculateArray,
                'C', Items.ENDER_PEARL
        );
        GameRegistry.addRecipe(
                PLItems.millTeeth,
                "ABC",
                'A', Items.FLINT,
                'B', Blocks.STONE,
                'C', Blocks.BRICK_BLOCK
        );
        GameRegistry.addRecipe(
                PLItems.millWheel,
                "AAA",
                "ABA",
                "AAA",
                'A', PLItems.millTeeth,
                'B', IC2Items.getItem("plateiron")
        );
        GameRegistry.addRecipe(
                IC2Items.getItem("iridiumPlate"),
                "ABA",
                "BCB",
                "ABA",
                'A', PLItems.ingotIridium,
                'B', IC2Items.getItem("advancedAlloy"),
                'C', PLItems.denseDiamondPlate
        );
        GameRegistry.addRecipe(
                PLItems.carbonTube,
                "AB",
                "B",
                'A', Items.REDSTONE,
                'B', IC2Items.getItem("coalDust")
        );
        GameRegistry.addRecipe(
                PLItems.rigidPaper,
                "A",
                "B",
                "A",
                'A', Items.PAPER,
                'B', Items.SUGAR
        );
        GameRegistry.addShapelessRecipe(
                new ItemStack(Items.BOOK),
                PLItems.rigidPaper,
                PLItems.rigidPaper,
                Items.LEATHER
        );
        GameRegistry.addRecipe(
                PLItems.getItems(PLItems.rigidPaperPack, 2),
                " A ",
                "A A",
                " A ",
                'A', PLItems.rigidPaper
        );
        GameRegistry.addRecipe(
                PLBlocks.heatDryer,
                "ABA",
                "CDC",
                "EFE",
                'A', IC2Items.getItem("plateiron"),
                'B', PLItems.electronicCircuitControl,
                'C', PLItems.roller,
                'D', Blocks.FURNACE,
                'E', PLItems.redstoneModule,
                'F', IC2Items.getItem("machine")
        );
        GameRegistry.addRecipe(
                PLBlocks.evsu,
                "ABA",
                "ACD",
                "ABA",
                'A', IC2Items.getItem("evTransformer"),
                'B', IC2Items.getItem("lapotronCrystal"),
                'C', IC2Items.getItem("mfsUnit"),
                'D', PLItems.pulseElectronicCircuitControl
        );/*
        AdvRecipe.addAndRegister(
                new ItemStack(PLItems.ceu),
                "ABA",
                "CDC",
                "CDC",
                'A', IC2Items.getItem("copperCableItem"),
                'B', IC2Items.getItem("lapiDust"),
                'C', IC2Items.getItem("casingtin"),
                'D', PLItems.carbonTube
        );*/

        //smelting registry
        GameRegistry.addSmelting(PLBlocks.oreIridium, PLItems.ingotIridium, XP);
        GameRegistry.addSmelting(PLItems.cleanedCrushedIridium, PLItems.ingotIridium, XP);
        GameRegistry.addSmelting(PLItems.dustIridium, PLItems.ingotIridium, XP);
        GameRegistry.addSmelting(PLItems.crushedIridium, PLItems.ingotIridium, XP);
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
        Map<ItemStack, ItemStack> smelting = FurnaceRecipes.instance().getSmeltingList();
        smelting.remove(itemStack);
    }
}
