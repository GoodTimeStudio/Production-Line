package com.mcgoodtime.gti.common.items.dust;

import com.mcgoodtime.gti.common.core.CreativeTabGti;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by suhao on 2015-04-24-0024.
 */
public class DustIridium extends Item {
    public DustIridium() {
        setUnlocalizedName("DustIridium");
        setCreativeTab(CreativeTabGti.creativeTabGti);
        setTextureName("gti:itemDustIriridium");
        GameRegistry.registerItem(this, "DustIridium");
    }
}
