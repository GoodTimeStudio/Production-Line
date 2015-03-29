package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.items.tools.IridiumPickaxe;

public class ItemLoader {
	public static void preInit() {
		Food.preInit();
		ItemIridium.preInit();
		Plate.preInit();

        IridiumPickaxe.preInit();
	}
}
