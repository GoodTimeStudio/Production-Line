package com.mcgoodtime.gti.common.items.crashed;

import com.mcgoodtime.gti.common.core.CreativeTabGti;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by suhao on 2015-04-24-0024.
 */
public class CrushedIridium extends Item {
    public CrushedIridium() {
        setUnlocalizedName("gti.CrushedIridium");
        setCreativeTab(CreativeTabGti.creativeTabGti);
        setTextureName("gti:itemCrushedIriridium");
        GameRegistry.registerItem(this, "CrushedIridium");
    }
}
