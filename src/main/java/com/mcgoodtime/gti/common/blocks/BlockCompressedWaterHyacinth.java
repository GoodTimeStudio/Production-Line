package com.mcgoodtime.gti.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import static com.mcgoodtime.gti.common.core.Gti.creativeTabGti;

public class BlockCompressedWaterHyacinth extends Block{
	public BlockCompressedWaterHyacinth(){
		super(Material.rock);
		this.setBlockName("gti.block.CompressedWaterHyacinth");
		this.setCreativeTab(creativeTabGti);
		this.setHardness(0.5f);
		this.setResistance(0.3f);
		this.setBlockTextureName("gti:blockCompressedWaterHyacinth");
	}
}
