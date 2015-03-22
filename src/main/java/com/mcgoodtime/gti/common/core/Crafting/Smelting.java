package com.mcgoodtime.gti.common.core.Crafting;

import com.mcgoodtime.gti.common.Items.OreItem;
import cpw.mods.fml.common.registry.GameRegistry;

public class Smelting {
	public static float xp;
	public static void initSmelting() {
		//----OreItem
		GameRegistry.addSmelting(OreItem.CrushedIR, OreItem.IngotIRs, xp);
		GameRegistry.addSmelting(OreItem.DustIR, OreItem.IngotIRs, xp);
	}
}
