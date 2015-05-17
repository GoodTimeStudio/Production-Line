package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.common.init.GtiBlocks;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

/**
 * Created by suhao on 2015/5/17.
 */
public class GtiEvent {

    @SubscribeEvent
    public void onPlayerCrafting(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.getItem().equals(GtiBlocks.carbonizeFurnace)) {
            event.player.addStat(GtiAchievement.carbonizeFurnace, 1);
        }
    }
}
