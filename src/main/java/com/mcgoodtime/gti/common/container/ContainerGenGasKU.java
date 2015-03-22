package com.mcgoodtime.gti.common.container;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerGenGasKU extends Container {
    @Override
    //返回是否打开GUI
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }
}
