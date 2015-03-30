package com.mcgoodtime.gti.common.fluid;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class Gas extends Fluid {

	public static Fluid gasNatural = new Gas("Natural Gas")
	.setGaseous(true);
	
	public Gas(String fluidName) {
		super(fluidName);
	}

	static {
		FluidRegistry.registerFluid(gasNatural);
	}
}
