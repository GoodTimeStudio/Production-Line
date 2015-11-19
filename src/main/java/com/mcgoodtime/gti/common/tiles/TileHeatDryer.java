package com.mcgoodtime.gti.common.tiles;

import com.mcgoodtime.gti.common.recipes.CarbonizeFurnaceRecipes;
import com.mcgoodtime.gti.common.tiles.tileslot.*;
import ic2.api.tile.IWrenchable;
import ic2.core.Ic2Items;
import ic2.core.block.IUpgradableBlock;
import ic2.core.item.IUpgradeItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Java0 on 2015/11/18.
 */
public class TileHeatDryer extends TileElectricContainer implements IUpgradableBlock, IWrenchable {

    public double requireEnergy;
    public int progress;
    public TileHeatDryer() {
        super(3, 300, 1, 1);
        this.tileSlots.add(new TileSlotInput(this, CarbonizeFurnaceRecipes.instance));
        this.tileSlots.add(new TileSlotDischarge(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotOutput(this, TileSlot.SlotMode.OUTPUT));
        this.tileSlots.add(new TileSlotOutput(this, TileSlot.SlotMode.OUTPUT));
        this.tileSlots.add(new TileSlotUpgrade(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotUpgrade(this, TileSlot.SlotMode.NULL));
    }

    @Override
    public int getSizeInventory() {
        return 6;
    }


    @Override
    public String getInventoryName() {
        return this.hasCustomInventoryName() ? this.name : "Heat Dryer";
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.requireEnergy = nbt.getShort("requireEnergy");
        this.progress = nbt.getShort("Progress");
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("requireEnergy", (short) requireEnergy);
        nbt.setShort("Progress", (short) progress);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.worldObj.isRemote) {
            boolean needUpdate = false;

            if (canProcess() && this.energy >= this.energyPerTick) {
                this.requireEnergy = CarbonizeFurnaceRecipes.instance.getRecipe(this.getStackInSlot(0)).requiresEnergy;
                this.setActive(true);
                this.energy -= this.energyPerTick;
                this.progress += this.energyPerTick;

                if (this.progress >= this.requireEnergy) {
                    this.requireEnergy = 0;
                    this.progress = 0;
                    this.processItem();
                    needUpdate = true;
                }
            } else {
                this.setActive(false);
                this.requireEnergy = 0;
                this.progress = 0;
            }

            for(int i = 4; i < 6; i++) {
                ItemStack stack = this.getStackInSlot(i);
                if(stack != null && stack.getItem() instanceof IUpgradeItem && ((IUpgradeItem)stack.getItem()).onTick(stack, this)) {
                    needUpdate = true;
                }
            }

            if (needUpdate) {
                this.markDirty();
            }
        }
    }

    private boolean canProcess() {
        if (this.getStackInSlot(0) == null) {
            return false;
        } else {
            ItemStack itemStack = CarbonizeFurnaceRecipes.instance.getProcessResult(this.getStackInSlot(0));
            if (itemStack != null) {
                if (this.getStackInSlot(2) == null || this.getStackInSlot(3) == null) {
                    return true;
                } else {

                    if (this.getStackInSlot(2).isItemEqual(itemStack)) {
                        int result = this.getStackInSlot(2).stackSize + itemStack.stackSize;
                        if (result <= getInventoryStackLimit() && result <= this.getStackInSlot(2).getMaxStackSize()) {
                            return true;
                        }
                    }

                    if (this.getStackInSlot(3).isItemEqual(itemStack)) {
                        int result = this.getStackInSlot(3).stackSize + itemStack.stackSize;
                        if (result <= getInventoryStackLimit() && result <= this.getStackInSlot(3).getMaxStackSize()) {
                            return true;
                        }
                    }

                }

            }
            return false;
        }
    }

    public void processItem() {
        if (this.canProcess()) {
            ItemStack outputItem = CarbonizeFurnaceRecipes.instance.getProcessResult(this.getStackInSlot(0));

            if (this.getStackInSlot(2) == null) {
                this.setInventorySlotContents(2, outputItem.copy());
            }
            else if (this.getStackInSlot(2).isItemEqual(outputItem)) {
                this.getStackInSlot(2).stackSize += outputItem.stackSize;
            }
            else if (this.getStackInSlot(3) == null) {
                this.setInventorySlotContents(3, outputItem.copy());
            }
            else if (this.getStackInSlot(3).isItemEqual(outputItem)) {
                this.getStackInSlot(3).stackSize += outputItem.stackSize;
            }

            this.getStackInSlot(0).stackSize -= CarbonizeFurnaceRecipes.instance.getRequiredProcessAmount(this.getStackInSlot(0));

            if (this.getStackInSlot(0).stackSize <= 0) {
                this.setInventorySlotContents(0, null);
            }
        }
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return direction != ForgeDirection.UP && super.acceptsEnergyFrom(emitter, direction);
    }

    @Override
    public double getEnergy() {
        return this.energy;
    }

    @Override
    public boolean useEnergy(double amount) {
        if(this.energy >= amount) {
            this.energy -= amount;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setRedstonePowered(boolean redstonePowered) {
    }

    @Override
    public List<ItemStack> getCompatibleUpgradeList() {
        List<ItemStack> list = new ArrayList<ItemStack>();
        list.add(Ic2Items.ejectorUpgrade);
        list.add(Ic2Items.pullingUpgrade);
        return list;
    }

    @Override
    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int i) {
        return i != this.facing && i != 0 && i != 1;
    }

    @Override
    public short getFacing() {
        return this.facing;
    }

    @Override
    public void setFacing(short i) {
        super.setFacing(i);
    }

    @Override
    public boolean wrenchCanRemove(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public float getWrenchDropRate() {
        return 1.0F;
    }

    @Override
    public ItemStack getWrenchDrop(EntityPlayer entityPlayer) {
        return new ItemStack(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 1, this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
    }
}