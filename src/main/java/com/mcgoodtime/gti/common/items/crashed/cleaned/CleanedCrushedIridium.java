package com.mcgoodtime.gti.common.items.crashed.cleaned;

import com.mcgoodtime.gti.common.core.CreativeTabGti;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by suhao on 2015-04-24-0024.
 */
public class CleanedCrushedIridium extends Item {
    public CleanedCrushedIridium() {
        setUnlocalizedName("CleanedCrushedIridium");
        setCreativeTab(CreativeTabGti.creativeTabGti);
        setTextureName("gti:itemWashedCrushedIridium");
        GameRegistry.registerItem(this, "CleanedCrushedIridium");
    }
}
