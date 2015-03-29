package com.mcgoodtime.gti.common.core.recipes;

import com.mcgoodtime.gti.common.items.Food;
import com.mcgoodtime.gti.common.items.Plate;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;

public class Recipes {

	public static void initRecipes() {
		//----Food
		GameRegistry.addRecipe(new ItemStack(Food.DimApple , 1) , new Object[] {"XXX" , "XAX" , "XXX" , 'X' , Plate.DenseDimPlate , 'A' , Item.getItemById(260)});
		GameRegistry.addShapelessRecipe(new ItemStack(Block.getBlockById(57) , 8) , Food.DimApple );
	
	}
}
