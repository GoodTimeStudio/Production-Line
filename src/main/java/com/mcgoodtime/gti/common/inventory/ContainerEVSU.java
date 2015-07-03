/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
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
package com.mcgoodtime.gti.common.inventory;

import com.mcgoodtime.gti.common.tiles.TileEVSU;
import ic2.core.ContainerFullInv;
import ic2.core.slot.SlotArmor;
import ic2.core.slot.SlotInvSlot;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

/*
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
