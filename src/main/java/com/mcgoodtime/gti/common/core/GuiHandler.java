/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.client.gui.GuiCarbonizeFurnace;
import com.mcgoodtime.gti.client.gui.GuiEUStorage;
import com.mcgoodtime.gti.client.gui.GuiFluidKineticGenerator;
import com.mcgoodtime.gti.client.gui.GuiHeatDryer;
import com.mcgoodtime.gti.common.inventory.ContainerCarbonizeFurnace;
import com.mcgoodtime.gti.common.inventory.ContainerEUStorage;
import com.mcgoodtime.gti.common.inventory.ContainerFluidKineticGenerator;
import com.mcgoodtime.gti.common.inventory.ContainerHeatDryer;
import com.mcgoodtime.gti.common.tiles.*;
import com.mcgoodtime.gti.common.tiles.eustorage.TileCSEU;
import com.mcgoodtime.gti.common.tiles.eustorage.TileEVSU;
import com.mcgoodtime.gti.common.tiles.eustorage.TileParallelSpaceSU;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    private static GuiHandler instance;

    /**
     * Instantiate or get the default GuiHandler
     * @return The only instance of the GuiHandler
     */
    public static GuiHandler getInstance() {
        if (instance == null) {
            instance = new GuiHandler();
        }
        return instance;
    }

    private GuiHandler() {

    }

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        EnumGui gui = EnumGui.values()[id];
        switch (gui) {
            case FluidKineticGenerator: return new ContainerFluidKineticGenerator(player, (TileFluidKineticGenerator)world.getTileEntity(x, y, z));
            case CarbonizeFurnace: return new ContainerCarbonizeFurnace(player, (TileCarbonizeFurnace)world.getTileEntity(x, y, z));
            case EVSU: return new ContainerEUStorage<TileEVSU>(player, (TileEVSU)world.getTileEntity(x, y, z));
            case HeatDryer: return new ContainerHeatDryer(player, (TileHeatDryer) world.getTileEntity(x, y, z));
            case CSEU: return new ContainerEUStorage<TileCSEU>(player, (TileCSEU) world.getTileEntity(x, y, z));
            case ParallelSpaceSU: return new ContainerEUStorage<TileParallelSpaceSU>(player, (TileParallelSpaceSU) world.getTileEntity(x, y, z));
            default: return null;
        }
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        EnumGui gui = EnumGui.values()[id];
        switch (gui) {
            case FluidKineticGenerator:
                return new GuiFluidKineticGenerator(new ContainerFluidKineticGenerator(player, (TileFluidKineticGenerator)world.getTileEntity(x, y, z)));
            case CarbonizeFurnace:
                return new GuiCarbonizeFurnace(new ContainerCarbonizeFurnace(player, (TileCarbonizeFurnace)world.getTileEntity(x, y, z)));
            case EVSU:
                return new GuiEUStorage(new ContainerEUStorage<TileEVSU>(player, (TileEVSU) world.getTileEntity(x, y, z)));
            case HeatDryer: return new GuiHeatDryer(new ContainerHeatDryer(player, (TileHeatDryer) world.getTileEntity(x, y, z)));
            case CSEU: return new GuiEUStorage(new ContainerEUStorage<TileCSEU>(player, (TileCSEU) world.getTileEntity(x, y, z)));
            case ParallelSpaceSU:
                return new GuiEUStorage(new ContainerEUStorage<TileParallelSpaceSU>(player, (TileParallelSpaceSU) world.getTileEntity(x, y, z)));
            default: return null;
        }
    }

    public enum EnumGui {
        FluidKineticGenerator,
        CarbonizeFurnace,
        EVSU,
        HeatDryer,
        CSEU,
        ParallelSpaceSU
    }
}
