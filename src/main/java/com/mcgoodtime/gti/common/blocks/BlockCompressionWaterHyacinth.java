package com.mcgoodtime.gti.common.blocks;

import static com.mcgoodtime.gti.common.core.Gti.creativeTabGti;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockCompressionWaterHyacinth extends Block{
	
	public BlockCompressionWaterHyacinth(){
		super(Material.rock);
		  this.setCreativeTab(creativeTabGti);
		  this.setHardness(0.3f);
		  this.setResistance(5.0f);
		  this.setBlockTextureName("gti:CompressionWaterHyacinth");
		  this.setBlockName("gti.block.CompressionWaterHyacinth");
	}
}
