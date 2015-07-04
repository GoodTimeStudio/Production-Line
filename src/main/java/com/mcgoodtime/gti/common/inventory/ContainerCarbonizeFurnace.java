package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import ic2.core.ContainerFullInv;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

/*
 * Created by suhao on 2015.7.4.
 */
public class ContainerCarbonizeFurnace extends ContainerFullInv<TileCarbonizeFurnace> {

    public ContainerCarbonizeFurnace(EntityPlayer player, TileCarbonizeFurnace tileEntity) {
        super(player, tileEntity, 168);
        this.addSlotToContainer(new SlotInvSlot(tileEntity.inputSlot, 0, 56, 17));
        this.addSlotToContainer(new SlotInvSlot(tileEntity.fuelSlot, 0, 56, 53));
        this.addSlotToContainer(new SlotInvSlot(tileEntity.outputSlot, 0, 116, 35));
    }

    @Override
    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return false;
    }
}
