package com.mcgoodtime.productionline.common.tiles.eustorage;

/**
 * Created by BestOwl on 2015.11.29.0029.
 *
 * @author BestOwl
 */
public class TileCSEU extends TileEUStorage {

    public TileCSEU() {
        super(2, (int) 720E3);
    }

    @Override
    public String getInventoryName() {
        return "CSEU";
    }
}
