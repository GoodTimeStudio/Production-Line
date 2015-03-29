package com.mcgoodtime.gti.common.items;

//import com.mcgoodtime.gti.common.core.CreativeTabGti;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Plate extends Item {

	public static Item DimPlate = new Item()
	.setUnlocalizedName("DiamondPlate")
	.setCreativeTab(CreativeTabs.tabFood)
	.setTextureName("gti:itemDiamondPlate");
	public static Item DenseDimPlate = new Item()
	.setUnlocalizedName("DenseDiamondPlate")
	.setCreativeTab(CreativeTabs.tabFood)
	.setTextureName("gti:itemDenseDiamondPlate");
	
	public static void preInit() {
		GameRegistry.registerItem(DimPlate, "DiamondPlate");
		GameRegistry.registerItem(DenseDimPlate, "DenseDiamondPlate");
	}	
	
}
