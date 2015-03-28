package com.mcgoodtime.gti.common.Items.tools;

import com.mcgoodtime.gti.common.core.GTI;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class IRPix extends Item {

    public static Item irPix = new Item()
    .setCreativeTab(GTI.creativeTabGTI)
    .setUnlocalizedName("irPix")
    .setTextureName("gti:irPix");

    public static void preInit() {
        GameRegistry.registerItem(irPix, "irPix");
    }
}
