package com.mcgoodtime.productionline.common.tiles.eustorage;

import com.mcgoodtime.productionline.common.tiles.TileElectricContainer;
import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlot;
import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlotCharge;
import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlotDischarge;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.energy.tile.IEnergyEmitter;
import ic2.api.energy.tile.IEnergySource;
import ic2.api.tile.IEnergyStorage;
import ic2.api.tile.IWrenchable;
import ic2.core.init.MainConfig;
import ic2.core.util.ConfigUtil;
import ic2.core.util.StackUtil;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import java.util.Collections;
import java.util.List;

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
//        if (!(this instanceof TileParallelSpaceSU)) {
//            this.tileSlots.add(new TileSlotDischarge(this, TileSlot.SlotMode.NULL));
//            this.tileSlots.add(new TileSlotCharge(this, TileSlot.SlotMode.INPUT));
//        }
    }

    @Override
    public void update() {
        super.update();
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
            this.isRedstonePowered = this.worldObj.isBlockIndirectlyGettingPowered(this.pos) > 0;
        }

        if (this.shouldEmitRedstonePower()) {
            this.worldObj.notifyBlockOfStateChange(this.pos, this.worldObj.getBlockState(this.pos).getBlock());
        }
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        this.redstoneMode = RedstoneMode.values()[nbt.getShort("redstoneMode")];
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt).setShort("redstoneMode", (short) this.redstoneMode.ordinal());
        return nbt;
    }

    @Override
    public boolean acceptsEnergyFrom(IEnergyEmitter iEnergyEmitter, EnumFacing enumFacing) {
        return enumFacing != this.facing;
    }

    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor iEnergyAcceptor, EnumFacing enumFacing) {
        return enumFacing == this.facing;
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
    public boolean setFacing(World world, BlockPos blockPos, EnumFacing side, EntityPlayer entityPlayer) {
        if (side == this.facing) {
            return false;
        }
        setFacing(side);
        return true;
    }

    @Override
    public EnumFacing getFacing(World world, BlockPos blockPos) {
        return this.facing;
    }

    @Override
    public void setFacing(EnumFacing facing) {
        MinecraftForge.EVENT_BUS.post(new EnergyTileUnloadEvent(this));
        super.setFacing(facing);
        MinecraftForge.EVENT_BUS.post(new EnergyTileLoadEvent(this));
    }

    @Override
    public boolean wrenchCanRemove(World world, BlockPos blockPos, EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState iBlockState, TileEntity tileEntity, EntityPlayer entityPlayer, int i) {
        ItemStack ret = new ItemStack(iBlockState.getBlock(), 1,
                iBlockState.getBlock().getMetaFromState(iBlockState));
        float energyRetainedInStorageBlockDrops = ConfigUtil.getFloat(MainConfig.get(), "balance/energyRetainedInStorageBlockDrops");
        if (energyRetainedInStorageBlockDrops > 0) {
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(ret);
            nbt.setDouble("energy", this.energy * (double)energyRetainedInStorageBlockDrops);
        }
        return Collections.singletonList(ret);
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
    public boolean isTeleporterCompatible(EnumFacing enumFacing) {
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
