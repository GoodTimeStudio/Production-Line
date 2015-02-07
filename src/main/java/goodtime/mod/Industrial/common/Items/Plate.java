package goodtime.mod.Industrial.common.Items;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import goodtime.mod.Industrial.common.core.CreativeTabGTI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Plate extends Item {

	public static Item DimPlate = new Item()
	.setUnlocalizedName("DiamondPlate")
	.setCreativeTab(CreativeTabGTI.tab)
	.setTextureName("gti:itemDiamondPlate");
	public static Item DenseDimPlate = new Item()
	.setUnlocalizedName("DenseDiamondPlate")
	.setCreativeTab(CreativeTabGTI.tab)
	.setTextureName("gti:itemDenseDiamondPlate");
	
	public static void preInit() {
		GameRegistry.registerItem(DimPlate, "DiamondPlate");
		GameRegistry.registerItem(DenseDimPlate, "DenseDiamondPlate");
	}	
	
}
