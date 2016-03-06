package com.mcgoodtime.productionline.common.tiles.eustorage;

/**
 * Created by BestOwl on 2015.11.29.0029.
 *
 * @author BestOwl
 */
public class TileParallelSpaceSU extends TileEUStorage {

    public TileParallelSpaceSU() {
        super(6, (int) 2E8);
    }

    @Override
    public String getInventoryName() {
        return "ParallelSpaceSU";
    }
}
