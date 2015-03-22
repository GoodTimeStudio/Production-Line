package com.mcgoodtime.gti.common.core;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabGTI extends CreativeTabs {
	
	private int icon;
	public static CreativeTabGTI tab ;
	
	public CreativeTabGTI(int icon, String label) { 
		 super(label); 
		 this.icon = icon; 
	} 

	@Override 
	public ItemStack getIconItemStack() {
		Item iconItem;
		iconItem = GameRegistry.findItem("GTI", "DiamondApple");
		return new ItemStack(iconItem);
	}

	@Override
	public String getTranslatedTabLabel() {
		return new String("GoodTime Industrial");
	}

	@Override
	public Item getTabIconItem() {
		return null;
	}
	
}

