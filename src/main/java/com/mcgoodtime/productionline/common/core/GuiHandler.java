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

package com.mcgoodtime.productionline.common.core;

//import com.mcgoodtime.productionline.client.gui.*;
//import com.mcgoodtime.productionline.common.inventory.*;
//import com.mcgoodtime.productionline.common.tiles.*;
//import com.mcgoodtime.productionline.common.tiles.eustorage.TileCSEU;
//import com.mcgoodtime.productionline.common.tiles.eustorage.TileEVSU;
//import com.mcgoodtime.productionline.common.tiles.eustorage.TileParallelSpaceSU;
import com.mcgoodtime.productionline.client.gui.GuiAdvSolar;
import com.mcgoodtime.productionline.client.gui.GuiCarbonizeFurnace;
import com.mcgoodtime.productionline.client.gui.GuiEUStorage;
import com.mcgoodtime.productionline.client.gui.GuiFluidKineticGenerator;
import com.mcgoodtime.productionline.client.gui.GuiHeatDryer;
import com.mcgoodtime.productionline.client.gui.GuiParallelSpaceSU;
import com.mcgoodtime.productionline.common.inventory.ContainerAdvSolar;
import com.mcgoodtime.productionline.common.inventory.ContainerCarbonizeFurnace;
import com.mcgoodtime.productionline.common.inventory.ContainerEUStorage;
import com.mcgoodtime.productionline.common.inventory.ContainerFluidKineticGenerator;
import com.mcgoodtime.productionline.common.inventory.ContainerHeatDryer;
import com.mcgoodtime.productionline.common.inventory.ContainerParallelSpaceSU;
import com.mcgoodtime.productionline.common.tiles.TileAdvSolar;
import com.mcgoodtime.productionline.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.productionline.common.tiles.TileFluidKineticGenerator;
import com.mcgoodtime.productionline.common.tiles.TileHeatDryer;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileCSEU;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileEVSU;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileParallelSpaceSU;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

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
        BlockPos pos = new BlockPos(x, y, z);
        switch (gui) {
            case FluidKineticGenerator: return new ContainerFluidKineticGenerator(player, (TileFluidKineticGenerator)world.getTileEntity(pos));
            case CarbonizeFurnace: return new ContainerCarbonizeFurnace(player, (TileCarbonizeFurnace)world.getTileEntity(pos));
            case EVSU: return new ContainerEUStorage<>(player, (TileEVSU)world.getTileEntity(pos));
            case HeatDryer: return new ContainerHeatDryer(player, (TileHeatDryer) world.getTileEntity(pos));
            case CSEU: return new ContainerEUStorage<>(player, (TileCSEU) world.getTileEntity(pos));
            case ParallelSpaceSU: return new ContainerParallelSpaceSU(player, (TileParallelSpaceSU) world.getTileEntity(pos));
            case AdvSolar: return new ContainerAdvSolar(player, (TileAdvSolar) world.getTileEntity(pos));
            default: return null;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        EnumGui gui = EnumGui.values()[id];
        BlockPos pos = new BlockPos(x, y, z);
        switch (gui) {
            case FluidKineticGenerator:
                return new GuiFluidKineticGenerator(new ContainerFluidKineticGenerator(player, (TileFluidKineticGenerator)world.getTileEntity(pos)));
            case CarbonizeFurnace:
                return new GuiCarbonizeFurnace(new ContainerCarbonizeFurnace(player, (TileCarbonizeFurnace)world.getTileEntity(pos)));
            case EVSU:
                return new GuiEUStorage(new ContainerEUStorage<>(player, (TileEVSU) world.getTileEntity(pos)));
            case HeatDryer: return new GuiHeatDryer(new ContainerHeatDryer(player, (TileHeatDryer) world.getTileEntity(pos)));
            case CSEU: return new GuiEUStorage(new ContainerEUStorage<>(player, (TileCSEU) world.getTileEntity(pos)));
            case ParallelSpaceSU:
                return new GuiParallelSpaceSU(new ContainerParallelSpaceSU(player, (TileParallelSpaceSU) world.getTileEntity(pos)));
            case AdvSolar:
                return new GuiAdvSolar(new ContainerAdvSolar(player, (TileAdvSolar) world.getTileEntity(pos)));
            default: return null;
        }
    }

    public enum EnumGui {
        FluidKineticGenerator,
        CarbonizeFurnace,
        EVSU,
        HeatDryer,
        CSEU,
        ParallelSpaceSU,
        AdvSolar
    }
}
