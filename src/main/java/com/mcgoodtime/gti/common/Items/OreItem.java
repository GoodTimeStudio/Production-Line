package com.mcgoodtime.gti.common.Items;

import com.mcgoodtime.gti.common.core.CreativeTabGTI;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;


public class OreItem extends Item {
	
	public static Item CrushedIR = new Item()
	.setUnlocalizedName("CrushedIriridium")
	.setCreativeTab(CreativeTabGTI.tab)
	.setTextureName("gti:itemCrushedIR");
	public static Item DustIR = new Item()
	.setUnlocalizedName("DustIriridium")
	.setCreativeTab(CreativeTabGTI.tab)
	.setTextureName("gti:itemDustIR");
	public static Item IngotIR = new Item()
	.setUnlocalizedName("IngotIriridium")
	.setCreativeTab(CreativeTabGTI.tab)
	.setTextureName("gti:itemIngotIR");
	
	public static ItemStack CrushedIRs = new ItemStack(CrushedIR); 
	public static ItemStack DustIRs = new ItemStack(DustIR);
	public static ItemStack IngotIRs = new ItemStack(IngotIR);
	
    public static void preInit() {
    	GameRegistry.registerItem(CrushedIR, "CrushedIriridium" );	 
    	GameRegistry.registerItem(DustIR, "PowderIriridium");
    	GameRegistry.registerItem(IngotIR, "IngotIriridium");
	}
	
}
