package com.mcgoodtime.gti.common.tiles;

import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.machine.tileentity.TileEntityIronFurnace;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by suhao on 2015.7.3.
 */
public class TileCarbonizeFurnace extends TileEntityInventory {

    @Override
    public String getInventoryName() {
        return "Carbonize Furnace";
    }

    @Override
    public void setFacing(short facing) {
        super.setFacing(facing);
    }
}
