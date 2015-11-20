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
 *
 */
public class TileHeatDryer extends TileElectricContainer implements IUpgradableBlock, IWrenchable {

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
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt) {
        super.writeToNBT(nbt);
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if (!this.worldObj.isRemote) {

        }
    }

    private boolean canProcess() {

        return false;
    }

    public void processItem() {

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