package com.mcgoodtime.gti.common.core.recipes;

import com.mcgoodtime.gti.common.blocks.OreIridium;
import com.mcgoodtime.gti.common.items.ItemIridium;
import com.mcgoodtime.gti.common.items.Plate;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.Recipes;
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
		Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(Item.getItemById(264))), null, new ItemStack(Plate.DimPlate));
		Recipes.compressor.addRecipe(new RecipeInputItemStack(new ItemStack(Plate.DimPlate , 9)), null, new ItemStack(Plate.DenseDimPlate));
	}
}
