package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.core.CreativeTabGti;
import com.mcgoodtime.gti.common.core.Gti;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

/**
 * @author Su Hao
 */
public class ItemAirBrake {

    public static class ItemAirBrakeUnit extends Item {
        public ItemAirBrakeUnit() {
            setUnlocalizedName("gti.item.AirBrakeUnit");
            setTextureName(Gti.MOD_ID + ":" + "itemAirBrakeUnit");
            setCreativeTab(CreativeTabGti.creativeTabGti);
            GameRegistry.registerItem(this, "AirBrakeUnit");
        }
    }

    public static class ItemAirBrakeCasing extends Item {
        public ItemAirBrakeCasing() {
            setUnlocalizedName("gti.item.AirBrakeCasing");
            setTextureName(Gti.MOD_ID + ":" + "itemAirBrakeCasing");
            setCreativeTab(CreativeTabGti.creativeTabGti);
            GameRegistry.registerItem(this, "AirBrakeCasing");
        }
    }
}
