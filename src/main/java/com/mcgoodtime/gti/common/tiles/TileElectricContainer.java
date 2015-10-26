package com.mcgoodtime.gti.common.tiles;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySink;
import ic2.api.network.INetworkTileEntityEventListener;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by BestOwl on 2015.10.26.0026.
 *
 * @author BestOwl
 */
public abstract class TileElectricContainer extends TileContainer implements IEnergySink, INetworkTileEntityEventListener {

    @Override
    public boolean acceptsEnergyFrom(TileEntity tileEntity, ForgeDirection forgeDirection) {
        return false;
    }

    @Override
    public double getDemandedEnergy() {
        return 0;
    }

    @Override
    public int getSinkTier() {
        return 0;
    }

    @Override
    public double injectEnergy(ForgeDirection forgeDirection, double v, double v1) {
        return 0;
    }

    @Override
    public void onNetworkEvent(int i) {

    }



    /**
     * Called when the chunk this TileEntity is on is Unloaded.
     */
    @Override
    public void onChunkUnload() {
        super.onChunkUnload();
        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
    }

    /**
     * validates a tile entity
     */
    @Override
    public void validate() {
        super.validate();
        MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
    }
}
