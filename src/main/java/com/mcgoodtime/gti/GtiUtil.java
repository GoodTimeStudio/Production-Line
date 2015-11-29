package com.mcgoodtime.gti;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;

/**
 * Created by BestOwl on 2015.11.14.0014.
 *
 * @author BestOwl
 */
public class GtiUtil {

    @SideOnly(Side.CLIENT)
    public static int getGuiScaled(int scale, float min, float max) {
        return (int) (scale * Math.min(1.0F, min / max));
    }

    public static void messageToPlayer(EntityPlayer player, String message, Object... args) {
        ChatComponentTranslation msg = new ChatComponentTranslation(message, args);
        player.addChatComponentMessage(msg);
    }
}
