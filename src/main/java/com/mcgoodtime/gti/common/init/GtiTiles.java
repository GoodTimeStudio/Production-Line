package com.mcgoodtime.gti.common.init;

import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.tileentity.TileEntity;

/**
 * Created by suhao on 2015/5/22.
 */
public class GtiTiles {
    public static TileEntity carbonizeFurnace;

    public static void init() {
        carbonizeFurnace = new TileCarbonizeFurnace();
        GameRegistry.registerTileEntity(TileCarbonizeFurnace.class, "TileEntityCarbonizeFurnace");
    }
}
