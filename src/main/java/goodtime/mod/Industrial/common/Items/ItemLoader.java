package goodtime.mod.Industrial.common.Items;

import goodtime.mod.Industrial.common.Items.Food;

public class ItemLoader {
	public static void Loader() {
		Food.preInit();
		OreItem.preInit();
		Plate.preInit();
	}
}
