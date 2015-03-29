package com.mcgoodtime.gti.common.core.recipes;

import com.mcgoodtime.gti.common.items.ItemIridium;
import cpw.mods.fml.common.registry.GameRegistry;

public class Smelting {
	public static float xp;
	public static void initSmelting() {
		//----ItemIridium
		GameRegistry.addSmelting(ItemIridium.CrushedIR, ItemIridium.IngotIRs, xp);
		GameRegistry.addSmelting(ItemIridium.DustIR, ItemIridium.IngotIRs, xp);
	}
}
