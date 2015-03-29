package com.mcgoodtime.gti.common.blocks;


//import com.mcgoodtime.gti.common.core.CreativeTabGTI;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class OreIridium extends Block {

	public static Block IR = new OreIridium(Material.rock)
	.setBlockName("iridiumore")
	.setHardness(10)
	.setCreativeTab(CreativeTabs.tabFood)
	.setBlockTextureName("gti:blockOreIR")
	.setResistance(20);
	
	public static ItemStack IRs;
	
	protected OreIridium(Material str) {
		super(str);
	}

    public static void preInit() {
    	GameRegistry.registerBlock(IR, "iridiumore");	 
	}

    static {//其他
    	IR.setHarvestLevel("pickaxe" , 3);
    }

	
}
