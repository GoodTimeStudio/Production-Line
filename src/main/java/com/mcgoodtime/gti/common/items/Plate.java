package com.mcgoodtime.gti.common.items;

import static com.mcgoodtime.gti.common.core.CreativeTabGti.creativeTabGti;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class Plate extends Item {

	public static Item DimPlate = new Item()
	.setUnlocalizedName("DiamondPlate")
	.setCreativeTab(creativeTabGti)
	.setTextureName("gti:itemDiamondPlate");
	public static Item DenseDimPlate = new Item()
	.setUnlocalizedName("DenseDiamondPlate")
	.setCreativeTab(creativeTabGti)
	.setTextureName("gti:itemDenseDiamondPlate");
	
	public static void preInit() {
		GameRegistry.registerItem(DimPlate, "DiamondPlate");
		GameRegistry.registerItem(DenseDimPlate, "DenseDiamondPlate");
	}	
	
}
