package com.mcgoodtime.gti.common.items.tools;

import static com.mcgoodtime.gti.common.core.CreativeTabGti.creativeTabGti;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class IridiumPickaxe extends Item {

    public static Item irPix = new Item()
    .setCreativeTab(creativeTabGti)
    .setUnlocalizedName("irPix")
    .setTextureName("gti:irPix");

    public static void preInit() {
        GameRegistry.registerItem(irPix, "irPix");
    }
}
