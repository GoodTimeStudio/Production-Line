package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import ic2.core.ContainerFullInv;
import ic2.core.block.machine.tileentity.TileEntityIronFurnace;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

import java.util.List;

/*
 * Created by suhao on 2015.7.4.
 */
public class ContainerCarbonizeFurnace extends ContainerFullInv<TileCarbonizeFurnace> {

    public ContainerCarbonizeFurnace(EntityPlayer entityPlayer, TileCarbonizeFurnace tileEntity) {
        super(entityPlayer, tileEntity, 166);
        this.addSlotToContainer(new SlotInvSlot(tileEntity.inputSlot, 0, 56, 17));
        this.addSlotToContainer(new SlotInvSlot(tileEntity.fuelSlot, 0, 56, 53));
        this.addSlotToContainer(new SlotInvSlot(tileEntity.outputSlot, 0, 116, 35));
    }

    public List<String> getNetworkedFields() {
        List ret = super.getNetworkedFields();
        ret.add("progress");
        ret.add("fuel");
        ret.add("maxFuel");
        return ret;
    }
}
