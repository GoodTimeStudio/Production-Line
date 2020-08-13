package com.mcgoodtime.productionline.tiles.tilewireless;

import ibxm.Player;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class TileWaterSource extends TileEntity implements IWireless,ITickable {

    @Override
    public void update() {

    }

    @Override
    public String getTerminalName() {
        return null;
    }

    @Override
    public Player getOwner() {
        return null;
    }

    @Override
    public int getRange() {
        return 0;
    }

    @Override
    public BlockPos getPos() {
        return null;
    }

}
