package com.mcgoodtime.gti.common.items.ingot;

import com.mcgoodtime.gti.common.core.CreativeTabGti;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by suhao on 2015-04-24-0024.
 */
public class IngotIridium extends Item {
    public IngotIridium() {
        setUnlocalizedName("gti.IngotIridium");
        setCreativeTab(CreativeTabGti.creativeTabGti);
        setTextureName("gti:itemIngotIR");
        GameRegistry.registerItem(this, "IngotIridium");
    }
}
