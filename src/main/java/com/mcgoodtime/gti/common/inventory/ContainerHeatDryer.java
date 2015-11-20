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
package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.inventory.slot.SlotInput;
import com.mcgoodtime.gti.common.inventory.slot.SlotOutput;
import com.mcgoodtime.gti.common.inventory.slot.SlotUpgrade;
import com.mcgoodtime.gti.common.tiles.TileHeatDryer;
import com.mcgoodtime.gti.common.tiles.tileslot.TileSlotInput;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.slot.SlotDischarge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ICrafting;

/*
 * Created by suhao on 2015.7.10.
 */
public class ContainerHeatDryer extends ContainerGti <TileHeatDryer>{

    public ContainerHeatDryer(EntityPlayer player, TileHeatDryer tile) {
        super(player, tile);
        this.addSlotToContainer(new SlotInput((TileSlotInput) tile.tileSlots.get(0), tile, 0, 56, 16));
        this.addSlotToContainer(new SlotDischarge(tile, 1, 1, 56, 53));
        this.addSlotToContainer(new SlotOutput(player, tile, 2, 116, 35));
    }

    @Override
    public void addCraftingToCrafters(ICrafting crafting) {
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
    }

    /**
     * Sends two ints to the client-side Container. Used for furnace burning time, smelting progress, brewing progress,
     * and enchanting level. Normally the first int identifies which variable to update, and the second contains the new
     * value. Both are truncated to shorts in non-local SMP.
     * @param i Identifies which variable to update
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int i, int var) {
    }
}
