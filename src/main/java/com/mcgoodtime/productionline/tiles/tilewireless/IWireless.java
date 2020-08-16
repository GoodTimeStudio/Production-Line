package com.mcgoodtime.productionline.tiles.tilewireless;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public interface IWireless {

    String getTerminalName();

    void setOwner(Entity player);

    Entity getOwner();

    void link(TileWireless tile);

    void unlink(TileWireless tile);

}
