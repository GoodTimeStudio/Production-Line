package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.core.Gti;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OreIridium extends Block {

	public OreIridium() {
		super(Material.rock);
		this.setCreativeTab(Gti.creativeTabGTI);
		this.setHardness(10);
		this.setBlockName("iridiumore");
		this.setResistance(20);
		this.setBlockTextureName("gti:blockOreIridium");
		this.setHarvestLevel("pickaxe", 3);
	}

	public static Block IR = new OreIridium();
	
	public static ItemStack IRs = new ItemStack(Item.getItemFromBlock(IR));

    public static void preInit() {
    	GameRegistry.registerBlock(IR, "iridiumore");
	}
}
