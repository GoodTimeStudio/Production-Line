package com.mcgoodtime.gti.common.items.dust.cleaned;

import com.mcgoodtime.gti.common.core.CreativeTabGti;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * Created by suhao on 2015-04-24-0024.
 */
public class CleanedDustIridium extends Item {
    public CleanedDustIridium() {
        setUnlocalizedName("gti.CleanedDustIridium");
        setCreativeTab(CreativeTabGti.creativeTabGti);
        setTextureName("gti:itemWashedDustIridium");
        GameRegistry.registerItem(this, "CleanedDustIridium");
    }
}
