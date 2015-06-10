/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
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
import com.mcgoodtime.gti.client.gui.GuiEVSU;
import com.mcgoodtime.gti.client.gui.GuiGenGasKu;
import com.mcgoodtime.gti.common.inventory.ContainerCarbonizeFurnace;
import com.mcgoodtime.gti.common.inventory.ContainerEVSU;
import com.mcgoodtime.gti.common.inventory.ContainerGenGasKu;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.gti.common.tiles.TileEVSU;
import com.mcgoodtime.gti.common.tiles.TileGenGasKu;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.gui.Gui;
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
        if (id == Guis.GenGasKu.ordinal()) {
            return new ContainerGenGasKu(player.inventory, (TileGenGasKu)world.getTileEntity(x, y, z));
        }
        if (id == Guis.CarbonizeFurnace.ordinal()) {
            return new ContainerCarbonizeFurnace(player.inventory, (TileCarbonizeFurnace)world.getTileEntity(x, y, z));
        }
        if (id == Guis.EVSU.ordinal()) {
            return new ContainerEVSU(player.inventory, (TileEVSU)world.getTileEntity(x, y, z));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id == Guis.GenGasKu.ordinal()) {
            return new GuiGenGasKu(player.inventory, (TileGenGasKu)world.getTileEntity(x, y, z));
        }
        if (id == Guis.CarbonizeFurnace.ordinal()) {
            return new GuiCarbonizeFurnace(player.inventory, (TileCarbonizeFurnace)world.getTileEntity(x, y, z));
        }
        if (id == Guis.EVSU.ordinal()) {
            return new GuiEVSU(player.inventory, (TileEVSU)world.getTileEntity(x, y, z));
        }
        return null;
    }

    public enum Guis {
        GenGasKu,
        CarbonizeFurnace,
        EVSU
    }
}
