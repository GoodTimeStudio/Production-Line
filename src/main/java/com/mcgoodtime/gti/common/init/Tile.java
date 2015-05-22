package com.mcgoodtime.gti.common.init;

import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * Created by suhao on 2015/5/22.
 */
public class Tile {
    public static void init() {
        //register TileEntity.
        GameRegistry.registerTileEntity(TileCarbonizeFurnace.class, "TileEntityCarbonizeFurnace");
    }
}
