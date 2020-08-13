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
package com.mcgoodtime.productionline.event;

import com.mcgoodtime.productionline.tiles.TileTefnutTear;
import com.mcgoodtime.productionline.tiles.tilewireless.IWireless;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;

/**
 * Production Line event listener.
 *
 * @author BestOwl, liach
 */
@Mod.EventBusSubscriber
public class PLEvent {/*
    @SubscribeEvent
    public void onPlayerCrafting(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.getItem().equals(PLBlocks.carbonizeFurnace.getItem())) {
            event.player.addStat(PLAchievement.getCarbonizeFurnace, 1);
        }
    }
*/


    public BlockPos wireLessPos;

    @SubscribeEvent
    public void onBlockPlayerPlaced(BlockEvent.EntityPlaceEvent event) {
        Boolean canLogistics;
        TileEntity te = event.getWorld().getTileEntity(event.getPos());
        if(te instanceof IWireless){
            wireLessPos = te.getPos();
        }

        /*
        if (te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
            canLogistics = true;
        }else if(te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,null) && te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)){
            canLogistics = true;
        }else if(te.hasCapability(CapabilityEnergy.ENERGY,null)){
            canLogistics = true;
        }else if(te instanceof TileTefnutTear){
            canLogistics = true;
        }
        else{
            canLogistics = false;
        }
        if(canLogistics){
            this.pos=te.getPos();
        }
*/
    }


}
