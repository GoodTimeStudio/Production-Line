package com.mcgoodtime.productionline.tiles.tilewireless;

import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

import java.util.Map;
import java.util.UUID;

public class TileWireless extends TileEntity implements IWireless, ITickable {

    Map<UUID,TileEntity> linkedWirelessDecives;

    private final int X = this.pos.getX();
    private final int Y = this.pos.getY();
    private final int Z = this.pos.getZ();

    Entity owner;

    @Override
    public void update() {

    }

    @Override
    public String getTerminalName() {
        return null;
    }

    @Override
    public void setOwner(Entity player) {
        this.owner = player;
    }

    public boolean sameOwner(Entity player){
        return player.equals(this.owner)?true:false;
    }

    public boolean inRange(BlockPos pos) {
        boolean x_InRange = pos.getX() <= X+getRange(0) && pos.getX()>=X-getRange(0);
        boolean y_InRange = pos.getY() <= Y+getRange(1) && pos.getY()>=Y-getRange(1);
        boolean z_InRange = pos.getZ() <= Z+getRange(2) && pos.getZ()>=Z-getRange(2);
        boolean notSelf = pos.getX() != X && pos.getY() != Y && pos.getZ() != Z;

        if(x_InRange && y_InRange && z_InRange && notSelf){
            return true;
        }
        return false;
    }

    private int getRange(int index){
        int[] range = {(17-1)/2,(9-1)/2,(17-1)/2};
        return range[index];
    }

    @Override
    public void link(TileEntity tile) {
        this.linkedWirelessDecives.put(UUID.randomUUID(), tile);
    }



}
