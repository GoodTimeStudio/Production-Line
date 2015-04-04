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

import com.mcgoodtime.gti.common.blocks.OreIridium;
import com.mcgoodtime.gti.common.items.ItemIridium;
import com.mcgoodtime.gti.common.items.Plate;

import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MachineRecipes {
	
	public static void InitMachineRecipes() {
		maceratorRecipes();
		compressorRecipes();
	}
	
	public static void maceratorRecipes() {
		OreIridium.IRs = new ItemStack(OreIridium.IR);
		//-------------------------------------------
		
		//----OreIridium
		Recipes.macerator.addRecipe(new RecipeInputItemStack(OreIridium.IRs) ,null , new ItemStack(ItemIridium.CrushedIR , 2));
		
		//----OreItem
		Recipes.macerator.addRecipe(new RecipeInputItemStack(ItemIridium.CrushedIRs), null, ItemIridium.DustIRs);
		Recipes.macerator.addRecipe(new RecipeInputItemStack(ItemIridium.IngotIRs, 1), null, ItemIridium.DustIRs);
	}
	
	public static void compressorRecipes() {
		//----Plate
		Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(Items.diamond)), null, new ItemStack(Plate.DimPlate));
		Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(Plate.DimPlate , 9)), null, new ItemStack(Plate.DenseDimPlate));
	}

	/**
	 *
	 */
	public static void oreWashingRecipes() {
		Recipes.oreWashing.addRecipe(new RecipeInputItemStack(new ItemStack(ItemIridium.CrushedIR)), null, null);
	}
}
