package com.mcgoodtime.gti.common.block.Machine;

import net.minecraft.block.material.Material;

public class MachineLoader {
	public static void preInit() {
		GenGasKU.preInit();
	}

    public static void init() {
        new GenGasKU(Material.iron).init();
    }
}
