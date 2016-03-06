package com.mcgoodtime.productionline.common.tiles.eustorage;

import com.mcgoodtime.productionline.common.tiles.TileElectricContainer;
import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlot;
import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlotCharge;
import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlotDischarge;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.tile.IEnergyStorage;
import ic2.api.tile.IWrenchable;
import ic2.core.init.MainConfig;
import ic2.core.util.ConfigUtil;
import ic2.core.util.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * Created by BestOwl on 2015.11.27.0027.
 *
 * @author BestOwl
 */
public abstract class TileEUStorage extends TileElectricContainer implements IEnergySource, IEnergyStorage, IWrenchable {

    public RedstoneMode redstoneMode;
    public boolean isRedstonePowered = false;

    public TileEUStorage(int tier, int maxStorage) {
        super((int) EnergyNet.instance.getPowerFromTier(tier), maxStorage, tier);
        this.redstoneMode = RedstoneMode.NONE;
        if (!(this instanceof TileParallelSpaceSU)) {
            this.tileSlots.add(new TileSlotDischarge(this, TileSlot.SlotMode.NULL));
            this.tileSlots.add(new TileSlotCharge(this, TileSlot.SlotMode.INPUT));
        }
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (this.energy > 0) {
            for (TileSlot tileSlot : this.tileSlots) {
                if (tileSlot instanceof TileSlotCharge) {
                    double amount = ((TileSlotCharge) tileSlot).charge(this.energy);
                    this.energy -= amount;
                    if (amount > 0) {
                        this.markDirty();
                    }
                }
            }
        }

        if (this.redstoneMode == RedstoneMode.OUTPUT_WHEN_REDSTONEPOWER_AND_FULLENEGRY || this.redstoneMode == RedstoneMode.NO_OUTPUT_WHEN_REDSTONEPOWER) {
            this.isRedstonePowered = this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
        }

        if (this.shouldEmitRedstonePower()) {
            this.worldObj.notifyBlocksOfNeighborChange(this.xCoord, this.yCoord, this.zCoord, this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord));
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.redstoneMode = RedstoneMode.values()[nbt.getShort("redstoneMode")];
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
        nbt.setShort("redstoneMode", (short) this.redstoneMode.ordinal());
    }

    /**
     * Determine if this acceptor can accept current from an adjacent emitter in a direction.
     * <p/>
     * The TileEntity in the emitter parameter is what was originally added to the energy net,
     * which may be normal in-world TileEntity, a delegate or an IMetaDelegate.
     *
     * @param emitter   energy emitter, may also be null or an IMetaDelegate
     * @param direction direction the energy is being received from
     */
    @Override
    public boolean acceptsEnergyFrom(TileEntity emitter, ForgeDirection direction) {
        return direction.ordinal() != this.facing;
    }

    @Override
    public boolean emitsEnergyTo(TileEntity tileEntity, ForgeDirection forgeDirection) {
        return forgeDirection.ordinal() == this.facing;
    }

    @Override
    public double getOfferedEnergy() {
        return this.energy < (double)this.energyTick || this.redstoneMode == RedstoneMode.NO_OUTPUT_WHEN_REDSTONEPOWER
                && this.isRedstonePowered || this.redstoneMode == RedstoneMode.OUTPUT_WHEN_REDSTONEPOWER_AND_FULLENEGRY &&
                this.isRedstonePowered && this.energy < (double)this.maxEnergy ? 0.0D : Math.min(this.energy, this.energyTick);
    }

    @Override
    public void drawEnergy(double amount) {
        this.energy -= amount;
    }

    @Override
    public int getSourceTier() {
        return this.tier;
    }

    @Override
    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int side) {
        return side != this.facing;
    }

    @Override
    public short getFacing() {
        return this.facing;
    }

    @Override
    public void setFacing(short facing) {
        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        super.setFacing(facing);
        MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
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
        ItemStack ret = new ItemStack(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 1,
                this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
        float energyRetainedInStorageBlockDrops = ConfigUtil.getFloat(MainConfig.get(), "balance/energyRetainedInStorageBlockDrops");
        if (energyRetainedInStorageBlockDrops > 0) {
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(ret);
            nbt.setDouble("energy", this.energy * (double)energyRetainedInStorageBlockDrops);
        }
        return ret;
    }

    public boolean shouldEmitRedstonePower() {
        boolean shouldEmitRedstone = false;
        switch(this.redstoneMode) {
            case EMIT_WHEN_FULL_ENERGY:
                shouldEmitRedstone = this.energy >= (double) (this.maxEnergy - this.energyTick * 20);
                break;
            case EMIT_WHEN_HAS_ENERGY:
                shouldEmitRedstone = this.energy > (double) this.energyTick && this.energy < (double)this.maxEnergy;
                break;
            case EMIT_WHEN_NOFULL_ENERGY:
                shouldEmitRedstone = this.energy > (double) this.energyTick && this.energy < (double)this.maxEnergy || this.energy < (double) this.energyTick;
                break;
            case EMIT_WHEN_NULL_ENERGY:
                shouldEmitRedstone = this.energy < (double) this.energyTick;
        }

        return shouldEmitRedstone;
    }

    @Override
    public int getStored() {
        return (int) this.energy;
    }

    @Override
    public void setStored(int energy) {
        this.energy = energy;
    }

    @Override
    public int addEnergy(int amount) {
        this.energy += amount;
        return amount;
    }

    @Override
    public int getCapacity() {
        return this.maxEnergy;
    }

    @Override
    public int getOutput() {
        return this.energyTick;
    }

    @Override
    public double getOutputEnergyUnitsPerTick() {
        return this.energyTick;
    }

    @Override
    public boolean isTeleporterCompatible(ForgeDirection forgeDirection) {
        return true;
    }

    public enum RedstoneMode {
        NONE,
        EMIT_WHEN_FULL_ENERGY,
        EMIT_WHEN_HAS_ENERGY,
        EMIT_WHEN_NOFULL_ENERGY,
        EMIT_WHEN_NULL_ENERGY,
        NO_OUTPUT_WHEN_REDSTONEPOWER,
        OUTPUT_WHEN_REDSTONEPOWER_AND_FULLENEGRY
    }
}
