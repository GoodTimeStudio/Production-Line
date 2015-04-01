package com.mcgoodtime.gti.common.items.tools;

import static com.mcgoodtime.gti.common.core.CreativeTabGti.creativeTabGti;
import com.mcgoodtime.gti.common.init.GtiItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;

public class IridiumPickaxe extends ItemPickaxe {

    protected IridiumPickaxe(ToolMaterial meta) {
        super(meta);
        //GtiItems.iridiumPickaxe = new ItemPickaxe(ToolMaterial.IRON);
        setCreativeTab(creativeTabGti);
        setUnlocalizedName("iridiumPickaxe");
        setTextureName("gti:iridiumPickaxe");
    }

    public static void preInit() {
        //GameRegistry.registerItem(GtiItems.iridiumPickaxe, "irPix");
    }
}
