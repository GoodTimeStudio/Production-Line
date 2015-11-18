package com.mcgoodtime.gti.common.tiles;

import com.mcgoodtime.gti.common.recipes.HeatDryerRecipes;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.tile.IWrenchable;
import ic2.core.Ic2Items;
import ic2.core.block.IUpgradableBlock;
import ic2.core.item.IUpgradeItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
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

        NBTTagList nbttaglist = nbt.getTagList("Items", 10);
        for (int i = 0; i < nbttaglist.tagCount(); ++i) {
            NBTTagCompound tag = nbttaglist.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < this.containerItemsList.size()) {
                this.containerItemsList.set(slot, ItemStack.loadItemStackFromNBT(tag));
            }
        }

        if (nbt.hasKey("CustomName", 8)) {
            this.name = nbt.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("requireEnergy", (short) requireEnergy);
        nbt.setShort("Progress", (short) progress);

        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.containerItemsList.size(); ++i) {
            if (this.containerItemsList.get(i) != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                this.containerItemsList.get(i).writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        nbt.setTag("Items", itemList);

        if (this.hasCustomInventoryName()) {
            nbt.setString("CustomName", this.name);
        }
    }

    @SideOnly(Side.CLIENT)
    public int getProgressScaled(int i) {
        return (int) (i * Math.min(1.0F, this.progress / this.requireEnergy));
    }

    @SideOnly(Side.CLIENT)
    public int getRemainingBatteryScaled(int i) {
        return (int) (i * Math.min(1.0F, (float) this.energy / (float) this.maxEnergy));
    }

    public boolean isProcessing() {
        return requireEnergy == 0;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.worldObj.isRemote) {
            boolean needUpdate = false;

            if (canProcess() && this.energy >= this.energyPerTick) {
                this.requireEnergy = HeatDryerRecipes.instance.getRecipes(this.containerItemsList.get(0)).requiresEnergy;
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
                ItemStack stack = this.containerItemsList.get(i);
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
        if (this.containerItemsList.get(0) == null) {
            return false;
        } else {
            ItemStack itemStack = HeatDryerRecipes.instance.getProcessResult(this.containerItemsList.get(0));
            if (itemStack != null) {
                if (this.containerItemsList.get(2) == null || this.containerItemsList.get(3) == null) {
                    return true;
                } else {

                    if (this.containerItemsList.get(2).isItemEqual(itemStack)) {
                        int result = containerItemsList.get(2).stackSize + itemStack.stackSize;
                        if (result <= getInventoryStackLimit() && result <= this.containerItemsList.get(2).getMaxStackSize()) {
                            return true;
                        }
                    }

                    if (this.containerItemsList.get(3).isItemEqual(itemStack)) {
                        int result = containerItemsList.get(3).stackSize + itemStack.stackSize;
                        if (result <= getInventoryStackLimit() && result <= this.containerItemsList.get(3).getMaxStackSize()) {
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
            ItemStack outputItem = HeatDryerRecipes.instance.getProcessResult(this.containerItemsList.get(0));

            if (this.containerItemsList.get(2) == null) {
                this.containerItemsList.set(2, outputItem.copy());
            }
            else if (this.containerItemsList.get(2).isItemEqual(outputItem)) {
                this.containerItemsList.get(2).stackSize += outputItem.stackSize;
            }
            else if (this.containerItemsList.get(3) == null) {
                this.containerItemsList.set(3, outputItem.copy());
            }
            else if (this.containerItemsList.get(3).isItemEqual(outputItem)) {
                this.containerItemsList.get(3).stackSize += outputItem.stackSize;
            }

            this.containerItemsList.get(0).stackSize -= HeatDryerRecipes.instance.getRequiredProcessAmount(this.containerItemsList.get(0));

            if (this.containerItemsList.get(0).stackSize <= 0) {
                this.containerItemsList.set(0, null);
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

    /**
     * Returns true if automation can extract the given item in the given
     * slot from the given side. Args: Slot, item, side
     */
    @Override
    public boolean canExtractItem(int slot, ItemStack itemStack, int side) {
        if (itemStack.getItem() == Items.bucket) {
            return true;
        }
        switch (slot) {
            case 2: return true;
            case 3: return true;
            default: return false;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given
     * slot from the given side. Args: Slot, item, side
     */
    @Override
    public boolean canInsertItem(int slot, ItemStack itemStack, int side) {
        switch (slot) {
            case 2: return false;
            case 3: return false;
            default: return true;
        }
    }
}