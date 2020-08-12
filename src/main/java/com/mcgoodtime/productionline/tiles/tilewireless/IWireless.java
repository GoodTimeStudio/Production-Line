package com.mcgoodtime.productionline.tiles.tilewireless;

import ibxm.Player;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public interface IWireless {

    String getTerminalName();

    Player getOwner();

    int getRange();

    BlockPos getPos();

    List<TileEntity> getLinkedBlocks();

}
