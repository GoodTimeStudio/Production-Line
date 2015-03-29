package com.mcgoodtime.gti.common.block.Machine;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.material.Material;

public class MachineLoader {
	public static void preInit() {
        GameRegistry.registerBlock(GenGasKU.GenGasKU, "GenGasKU");
	}
}
