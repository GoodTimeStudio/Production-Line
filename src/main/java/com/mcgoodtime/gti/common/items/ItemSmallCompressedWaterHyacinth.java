package com.mcgoodtime.gti.common.items;

import net.minecraft.item.Item;
import static com.mcgoodtime.gti.common.core.Gti.creativeTabGti;


public class ItemSmallCompressedWaterHyacinth extends Item{
	public ItemSmallCompressedWaterHyacinth(){
		this.setUnlocalizedName("gti.item.SmallCompressedWaterHyacinth");
		this.setTextureName("gti:itemSmallCompressedWaterHyacinth");
		this.setMaxStackSize(64);
		this.setCreativeTab(creativeTabGti);
	}
}
