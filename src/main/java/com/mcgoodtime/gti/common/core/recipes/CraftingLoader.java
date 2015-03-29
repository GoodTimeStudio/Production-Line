package com.mcgoodtime.gti.common.core.recipes;

public class CraftingLoader {
	public static void preInit() {
		Recipes.initRecipes();
		Smelting.initSmelting();
		MachineRecipes.InitMachineRecipes();
	}
}
