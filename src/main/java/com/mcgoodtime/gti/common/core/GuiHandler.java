package com.mcgoodtime.gti.common.core;

import com.mcgoodtime.gti.client.gui.GUIGenGasKU;
import com.mcgoodtime.gti.common.tiles.TileGenGasKu;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUIs.GenGasKU.ordinal()) {
            return new GUIGenGasKU(player.inventory, (TileGenGasKu)world.getTileEntity(x, y, z));
        }
        return null;
    }

    public enum GUIs {
        GenGasKU
    }
}
