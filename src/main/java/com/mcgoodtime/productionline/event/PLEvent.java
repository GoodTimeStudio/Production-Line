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

import com.mcgoodtime.productionline.tiles.tilewireless.TileTributary;
import com.mcgoodtime.productionline.tiles.tilewireless.TileWaterSource;
import com.mcgoodtime.productionline.tiles.tilewireless.TileWireless;
import gnu.trove.map.custom_hash.TObjectByteCustomHashMap;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

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

    List<TileWaterSource> playerWaterSources;

    @SubscribeEvent
    public void onBlockPlayerPlaced(BlockEvent.EntityPlaceEvent event) {
        TileEntity currentPlacedBlock = event.getWorld().getTileEntity(event.getPos());

        if (event.getPlacedBlock().getBlock() instanceof ITileEntityProvider && currentPlacedBlock instanceof TileWireless){
            TileWireless currentDevice = (TileWireless) currentPlacedBlock;
            if(!(event.getWorld().isRemote) && event.getEntity()instanceof EntityPlayer){
                currentDevice.setOwner(event.getEntity());
            }

            if(currentDevice instanceof TileWaterSource){
               playerWaterSources.add((TileWaterSource) currentDevice);
            }
            if(currentDevice instanceof TileTributary){
                if(!playerWaterSources.isEmpty()){
                    for (TileWaterSource waterSource : playerWaterSources){
                        if(waterSource.sameOwner(currentDevice.getOwner()) && waterSource.inRange(currentDevice.getPos())){
                            currentDevice.link(waterSource);
                            waterSource.link(currentDevice);
                        }
                    }
                }
            }
        }else {
            return;
        }

    }

    @SubscribeEvent
    public void onPlayerBreaked(BlockEvent.BreakEvent event){
        TileEntity curretBreakedBlock = event.getWorld().getTileEntity(event.getPos());
        if(curretBreakedBlock != null && curretBreakedBlock.isInvalid()){
            if(event.getState().getBlock() instanceof ITileEntityProvider && curretBreakedBlock instanceof TileWireless){
                TileWireless curretDevice = (TileWireless) curretBreakedBlock;
                if(curretBreakedBlock instanceof TileWaterSource){
                    playerWaterSources.remove(curretDevice);
                }
                for(TileWireless deviceInOtherTiles : curretDevice.getLinkedWirelessDecives()){
                    deviceInOtherTiles.unlink(curretDevice);
                }
            }
        }else {
            return;
        }
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
