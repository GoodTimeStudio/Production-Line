package com.mcgoodtime.gti.common.items.plate;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

import static com.mcgoodtime.gti.common.core.CreativeTabGti.creativeTabGti;

/**
 * Created by suhao on 2015-04-24-0024.
 */
public class DenseDiamondPlate extends Item {

    public DenseDiamondPlate() {
        setUnlocalizedName("DenseDiamondPlate");
        setCreativeTab(creativeTabGti);
        setTextureName("gti:itemDenseDiamondPlate");
        GameRegistry.registerItem(this, "DenseDiamondPlate");
    }
}
