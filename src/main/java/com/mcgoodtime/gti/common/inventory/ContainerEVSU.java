package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.gti.common.tiles.TileEVSU;
import ic2.core.ContainerFullInv;
import ic2.core.slot.SlotArmor;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;

import java.util.List;

/**
 * Created by suhao on 2015-6-10-0010.
 */
public class ContainerEVSU extends ContainerFullInv<TileEVSU> {

    public ContainerEVSU(EntityPlayer player, TileEVSU tileentity) {
        super(player, tileentity, 176 + 3, 166);
        for (int i = 0; i < 4; i++){
            //this.addSlotToContainer(new SlotArmor(entityPlayer.inventory, i, 8 + i * 18, 84)); //<-default
            addSlotToContainer(new SlotArmor(player.inventory, i, 152,
                    5 + i * 18));
        }

        addSlotToContainer(new SlotInvSlot(tileentity.chargeSlot, 0, 128,
                14));
        addSlotToContainer(new SlotInvSlot(tileentity.dischargeSlot, 0, 128,
                50));
    }

    @Override
    public List<String> getNetworkedFields(){
        List<String> list = super.getNetworkedFields();
        list.add("energy");
        list.add("redstoneMode");
        return list;
    }
}
