/*
package com.mcgoodtime.productionline.common.tiles;

import com.mcgoodtime.productionline.common.init.PLItems;
import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlot;
import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlotCharge;
import ic2.api.tile.IWrenchable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.biome.BiomeGenDesert;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 *//*
public class TileAdvSolar extends TileElectricGenerator implements IWrenchable {

    private int timer;
    public boolean sunIsVisible = false;
    public boolean hasLens = false;

    public TileAdvSolar() {
        super(2, 200);
        this.powerTick = 0;
        this.tileSlots.add(new TileSlot(this, TileSlot.SlotMode.NULL));
        this.tileSlots.add(new TileSlotCharge(this, TileSlot.SlotMode.NULL));
    }

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (!this.worldObj.isRemote) {
            boolean needUpdate = false;

            this.updateLensStatus();
            ItemStack stack = this.getStackInSlot(0);
            if (stack != null) {
                if (stack.isItemEqual(PLItems.advSolarLensUnit)) {
                    this.powerTick = 3;
                }
                else if (stack.isItemEqual(PLItems.advSolarLensGroup)) {
                    this.powerTick = 9;
                }
                else if (stack.isItemEqual(PLItems.advSolarLensCluster)) {
                    this.powerTick = 81;
                }
                else {
                    this.powerTick = 0;
                }
            } else {
                this.powerTick = 0;
            }

            this.timer++;
            if ((this.timer % 120) == 0) {
                this.timer = 0;
                this.updateSunVisible();
            }

            if (this.hasLens && this.sunIsVisible) {
                this.energy += this.powerTick;
            }
            if (this.energy > this.maxEnergy) {
                this.energy = this.maxEnergy;
            }

            if (this.energy > 0) {
                double amount = ((TileSlotCharge) this.tileSlots.get(1)).charge(this.energy);
                this.energy -= amount;
                if (amount > 0) {
                    needUpdate = true;
                }
            }

            if (needUpdate) {
                this.markDirty();
            }
        }
    }

    public void updateSunVisible() {
        int skylight = this.worldObj.getBlockLightValue(this.xCoord, 255, this.zCoord);
        boolean hasSky = !this.worldObj.provider.hasNoSky;
        boolean canSeeSky = this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord);
        boolean isDesert = this.worldObj.getBiomeGenForCoords(this.xCoord, this.zCoord) instanceof BiomeGenDesert;
        this.sunIsVisible = skylight > 4 && hasSky && canSeeSky && (isDesert || !this.worldObj.isRaining() && !this.worldObj.isThundering());
    }

    public void updateLensStatus() {
        this.hasLens = false;
        if (this.getStackInSlot(0) != null) {
            for (ItemStack stack : OreDictionary.getOres("advSolarLens")) {
                if (stack.isItemEqual(this.getStackInSlot(0))) {
                    this.hasLens = true;
                    break;
                }
            }
        }
    }

    /**
     * Determine if this emitter can emit energy to an adjacent receiver.
     * <p/>
     * The TileEntity in the receiver parameter is what was originally added to the energy net,
     * which may be normal in-world TileEntity, a delegate or an IMetaDelegate.
     *
     * @param receiver  receiver, may also be null or an IMetaDelegate
     * @param direction direction the receiver is from the emitter
     * @return Whether energy should be emitted
     *//*
    @Override
    public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection direction) {
        return direction != ForgeDirection.UP;
    }

    @Override
    public String getInventoryName() {
        return "AdvSolar";
    }

    @Override
    public boolean wrenchCanSetFacing(EntityPlayer entityPlayer, int i) {
        return false;
    }

    @Override
    public short getFacing() {
        return this.facing;
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
        return new ItemStack(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord), 1,
                this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord));
    }
}
*/