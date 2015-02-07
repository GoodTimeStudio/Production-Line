package goodtime.mod.Industrial.common.Items;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import goodtime.mod.Industrial.common.core.CreativeTabGTI;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class Food extends Item {
	
	public static Item DimApple = new ItemFood(1005, 10 , false)
	.setUnlocalizedName("DiamondApple")
	.setCreativeTab(CreativeTabGTI.tab)
	.setTextureName("gti:itemDiamondApple");
	
    public static void preInit() {
		GameRegistry.registerItem(DimApple, "DiamondApple");
	}
		
}
