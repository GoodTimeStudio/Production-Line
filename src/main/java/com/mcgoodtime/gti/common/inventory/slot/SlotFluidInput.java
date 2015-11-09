package com.mcgoodtime.gti.common.inventory.slot;

import ic2.core.util.StackUtil;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.IFluidContainerItem;
import org.apache.commons.lang3.mutable.MutableObject;

/**
 * Created by BestOwl on 2015.11.9.0009.
 *
 * @author BestOwl
 */
public class SlotFluidInput extends Slot {

    public SlotFluidInput(IInventory inventory, int slotIndex, int x, int y) {
        super(inventory, slotIndex, x, y);
    }


}
