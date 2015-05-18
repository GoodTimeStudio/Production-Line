package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.common.init.GtiBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * Created by suhao on 2015/5/17.
 */
public class GtiEvent {

    @SubscribeEvent
    public void onPlayerCrafting(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.getItem().equals(Item.getItemFromBlock(GtiBlocks.carbonizeFurnace))) {
            event.player.addStat(GtiAchievement.carbonizeFurnace, 1);
        }
    }

    /*@SubscribeEvent
    public void onPlayerPickup(PlayerEvent.ItemPickupEvent event) {
        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(GtiBlocks.carbonizeFurnace))) {
            event.player.addStat(GtiAchievement.carbonizeFurnace, 1);
        }
    }*/
}
