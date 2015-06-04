package com.mcgoodtime.gti.common.tiles;

import ic2.core.block.wiring.TileEntityElectricBlock;

/**
 * Created by suhao on 2015-6-3-0003.
 */
public class TileEVSU extends TileEntityElectricBlock {

    public static final int MAX_OUT = 8192;
    public static final int MAX_STORAGE = 100000000;

    public TileEVSU() {
        super(5, MAX_OUT, MAX_STORAGE);
    }

    @Override
    public String getInventoryName() {
        return "EVSU";
    }
}
