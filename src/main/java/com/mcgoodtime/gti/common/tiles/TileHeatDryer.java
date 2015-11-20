package com.mcgoodtime.gti.common.tiles;

import com.mcgoodtime.gti.common.recipes.HeatDryerRecipes;
import com.mcgoodtime.gti.common.tiles.tileslot.*;
import ic2.api.tile.IWrenchable;
import ic2.core.Ic2Items;
import ic2.core.block.IUpgradableBlock;
import ic2.core.item.IUpgradeItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Java0 on 2015/11/18.
 *
 */
public class TileHeatDryer extends TileElectricContainer implements IUpgradableBlock, IWrenchable {

    public final int requireEnergy = 500;
    public int progress;

    public TileHeatDryer() {
        super(3, 300, 1, 1);
        this.tileSlots.add(new TileSlotInput(this, HeatDryerRecipes.instance));
        this.tileSlots.add(new TileSlotDischarge(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotOutput(this));
        this.tileSlots.add(new TileSlotUpgrade(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotUpgrade(this, TileSlot.SlotMode.NULL));
    }

    @Override
    public String getInventoryName() {
        return "HeatDryer";
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.worldObj.isRemote) {
            boolean needUpdate = false;

            if (this.canProcess() && this.energy >= this.energyPerTick) {
                this.setActive(true);
                this.energy -= this.energyPerTick;
                this.progress += this.energyPerTick;

                if (this.progress >= this.requireEnergy) {
                    this.progress = 0;
                    this.processItem();
                    needUpdate = true;
                }
            } else {
                this.setActive(false);
                this.progress = 0;
            }

            for (TileSlot tileSlot : this.tileSlots) {
                if (tileSlot instanceof TileSlotUpgrade) {
                    ItemStack stack = tileSlot.getStack();
                    if(stack != null && stack.getItem() instanceof IUpgradeItem && ((IUpgradeItem)stack.getItem()).onTick(stack, this)) {
                        needUpdate = true;
                    }
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
            ItemStack itemStack = HeatDryerRecipes.instance.getProcessResult(this.getStackInSlot(0));
            if (itemStack != null) {
                if (this.getStackInSlot(2) == null) {
                    return true;
                } else {
                    if (this.getStackInSlot(2).isItemEqual(itemStack)) {
                        int result = this.getStackInSlot(2).stackSize + itemStack.stackSize;
                        if (result <= getInventoryStackLimit() && result <= this.getStackInSlot(2).getMaxStackSize()) {
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
            ItemStack outputItem = HeatDryerRecipes.instance.getProcessResult(this.getStackInSlot(0));

            if (this.getStackInSlot(2) == null) {
                this.setInventorySlotContents(2, outputItem.copy());
            }
            else if (this.getStackInSlot(2).isItemEqual(outputItem)) {
                this.getStackInSlot(2).stackSize += outputItem.stackSize;
            }

            this.getStackInSlot(0).stackSize -= HeatDryerRecipes.instance.getRequiredProcessAmount(this.getStackInSlot(0));

            if (this.getStackInSlot(0).stackSize <= 0) {
                this.setInventorySlotContents(0, null);
            }
        }
    }

    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return super.acceptsEnergyFrom(emitter, direction);
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