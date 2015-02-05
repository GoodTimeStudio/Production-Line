package goodtime.mod.Industrial.common.Items;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import goodtime.mod.Industrial.common.core.CreativeTabGTI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;

public class Food extends Item {
	
	public static Item DimApple = new ItemFood(1005, 10 , false)
	.setUnlocalizedName("钻石苹果")
	.setCreativeTab(CreativeTabGTI.tab)
	.setTextureName("gti:itemDiamondApple");
	
	@Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
		GameRegistry.registerItem(DimApple, "DiamondApple");
	}
	
	static {
		LanguageRegistry.addName(DimApple, "钻石苹果");
	}
	
	static {
		
	}
}
