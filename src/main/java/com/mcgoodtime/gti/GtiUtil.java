package com.mcgoodtime.gti;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
}
