package com.mcgoodtime.gti.common.tiles;

import com.mcgoodtime.gti.client.gui.GuiCarbonizeFurnace;
import com.mcgoodtime.gti.common.blocks.BlockCarbonizeFurnace;
import com.mcgoodtime.gti.common.init.GtiBlocks;
import com.mcgoodtime.gti.common.inventory.ContainerCarbonizeFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.recipe.RecipeOutput;
import ic2.core.ContainerBase;
import ic2.core.IC2;
import ic2.core.IHasGui;
import ic2.core.block.TileEntityInventory;
import ic2.core.block.invslot.InvSlotConsumableFuel;
import ic2.core.block.invslot.InvSlotOutput;
import ic2.core.block.invslot.InvSlotProcessable;
import ic2.core.block.invslot.InvSlotProcessableSmelting;
import ic2.core.block.machine.container.ContainerIronFurnace;
import ic2.core.block.machine.gui.GuiIronFurnace;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import org.lwjgl.Sys;

public class TileCarbonizeFurnace extends TileEntityInventory {
    public int fuel = 0;
    public int maxFuel = 0;
    public short progress = 0;
    public final short operationLength = 250;
    public final InvSlotProcessable inputSlot = new InvSlotProcessableSmelting(this, "input", 0, 1);
    public final InvSlotOutput outputSlot = new InvSlotOutput(this, "output", 2, 1);
    public final InvSlotConsumableFuel fuelSlot = new InvSlotConsumableFuel(this, "fuel", 1, 1, true);

    public TileCarbonizeFurnace() {
    }

    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);

        try {
            this.fuel = nbttagcompound.getInteger("fuel");
        } catch (Throwable var4) {
            this.fuel = nbttagcompound.getShort("fuel");
        }

        try {
            this.maxFuel = nbttagcompound.getInteger("maxFuel");
        } catch (Throwable var3) {
            this.maxFuel = nbttagcompound.getShort("maxFuel");
        }

        this.progress = nbttagcompound.getShort("progress");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setInteger("fuel", this.fuel);
        nbttagcompound.setInteger("maxFuel", this.maxFuel);
        nbttagcompound.setShort("progress", this.progress);
    }

    public int gaugeProgressScaled(int i) {
        return this.progress * i / 250;
    }

    public int gaugeFuelScaled(int i) {
        if(this.maxFuel == 0) {
            this.maxFuel = this.fuel;
            if(this.maxFuel == 0) {
                this.maxFuel = 250;
            }
        }

        return this.fuel * i / this.maxFuel;
    }

    public boolean enableUpdateEntity() {
        return IC2.platform.isSimulating();
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        boolean needsInvUpdate = false;
        if(this.fuel <= 0 && this.canOperate()) {
            this.fuel = this.maxFuel = this.fuelSlot.consumeFuel();
            if(this.fuel > 0) {
                needsInvUpdate = true;
            }
        }

        if(this.isBurning() && this.canOperate()) {
            ++this.progress;
            if(this.progress >= 250) {
                this.progress = 0;
                this.operate();
                needsInvUpdate = true;
            }
        } else {
            this.progress = 0;
        }

        if(this.fuel > 0) {
            --this.fuel;
        }

        if(this.getActive() != this.isBurning()) {
            this.setActive(this.isBurning());
            BlockCarbonizeFurnace.updateFurnaceBlockState(this.isBurning(), this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            needsInvUpdate = true;
        }

        if(needsInvUpdate) {
            this.markDirty();
        }
    }

    public void operate() {
        this.outputSlot.add(this.inputSlot.process().items);
        this.inputSlot.consume();


    }

    public boolean isBurning() {
        return this.fuel > 0;
    }

    public boolean canOperate() {
        RecipeOutput output = this.inputSlot.process();
        return (output != null) && this.outputSlot.canAdd(output.items);
    }

    public ItemStack getResultFor(ItemStack itemstack) {
        return FurnaceRecipes.smelting().getSmeltingResult(itemstack);
    }

    public String getInventoryName() {
        return "Carbonize Furnace";
    }

}
