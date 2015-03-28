package com.mcgoodtime.gti.common.Items;

import com.mcgoodtime.gti.common.Items.tools.IRPix;

public class ItemLoader {
	public static void preInit() {
		Food.preInit();
		OreItem.preInit();
		Plate.preInit();

        IRPix.preInit();
	}
}
