package com.mcgoodtime.gti.common.blocks.machines;

import cpw.mods.fml.common.registry.GameRegistry;

public class MachineLoader {
	public static void preInit() {
        GameRegistry.registerBlock(GenGasKU.GenGasKU, "GenGasKU");
	}
}
