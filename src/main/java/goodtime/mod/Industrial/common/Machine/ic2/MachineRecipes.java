package goodtime.mod.Industrial.common.Machine.ic2;

import goodtime.mod.Industrial.common.Items.OreItem;
import goodtime.mod.Industrial.common.block.Ore;
import ic2.api.recipe.IMachineRecipeManager;
import ic2.api.recipe.IRecipeInput;
import ic2.api.recipe.RecipeInputItemStack;
import ic2.api.recipe.RecipeInputOreDict;
import ic2.api.recipe.RecipeOutput;
import ic2.api.recipe.Recipes;
import net.minecraft.item.ItemStack;

public class MachineRecipes {
	
	public static void initRecipes()
	{
		Ore.IRs = new ItemStack(Ore.IR);
		
		Recipes.macerator.addRecipe(new RecipeInputItemStack(Ore.IRs) ,null , new ItemStack(OreItem.CrushedIR , 2));
		Recipes.macerator.addRecipe(new RecipeInputItemStack(OreItem.CrushedIRs), null, OreItem.DustIRs);
		Recipes.macerator.addRecipe(new RecipeInputItemStack(OreItem.IngotIRs, 1), null, OreItem.DustIRs);
	}
}
