package com.mcgoodtime.productionline.common.blocks.fluid;

import com.mcgoodtime.productionline.common.core.ProductionLine;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class BioFuel extends Fluid{
	public static final ResourceLocation still = new ResourceLocation(ProductionLine.MOD_NAME+":"+"textures/blocks/fluids/Bio Fuel_Still");
	
	
	public BioFuel() {
		super("bioFuel", still, still);
		
		this.setUnlocalizedName("BioFuel");
		this.setDensity(800);
		this.setViscosity(2000);
		this.setLuminosity(1);
	}

}
