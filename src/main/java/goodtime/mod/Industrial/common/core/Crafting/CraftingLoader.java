package goodtime.mod.Industrial.common.core.Crafting;

public class CraftingLoader {
	public static void Loader() {
		Recipes.initRecipes();
		Smelting.initSmelting();
		MachineRecipes.InitMachineRecipes();
	}
}
