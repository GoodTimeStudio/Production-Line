package com.mcgoodtime.productionline.tiles.tilewireless;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public interface IWireless {

    String getTerminalName();

    void setOwner(Entity player);

    void link(TileWireless tile);

}
