package com.mcgoodtime.productionline.common.tiles;

import com.mcgoodtime.productionline.common.init.PLItems;
import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlot;
import com.mcgoodtime.productionline.common.tiles.tileslots.TileSlotCharge;
import ic2.api.energy.tile.IEnergyAcceptor;
import ic2.api.tile.IWrenchable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDesert;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collections;
import java.util.List;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 */
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
    public void update() {
        super.update();
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
        int skylight = this.worldObj.getBlockLightOpacity(new BlockPos(pos.getX(), 255, pos.getZ()));
        boolean hasSky = !this.worldObj.provider.getHasNoSky();
        boolean canSeeSky = this.worldObj.canSeeSky(pos.up());
        boolean isDesert = this.worldObj.getBiomeForCoordsBody(pos) instanceof BiomeDesert;
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
     */
    @Override
    public boolean emitsEnergyTo(IEnergyAcceptor receiver, EnumFacing direction) {
        return direction != EnumFacing.UP;
    }

    @Override
    public String getName() {
        return "AdvSolar";
    }

    @Override
    public EnumFacing getFacing(World world, BlockPos blockPos) {
        return this.facing;
    }

    @Override
    public boolean setFacing(World world, BlockPos blockPos, EnumFacing enumFacing, EntityPlayer entityPlayer) {
        setFacing(enumFacing);
        return true;
    }

    @Override
    public boolean wrenchCanRemove(World world, BlockPos blockPos, EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public List<ItemStack> getWrenchDrops(World world, BlockPos blockPos, IBlockState iBlockState, TileEntity tileEntity, EntityPlayer entityPlayer, int i) {
        return Collections.singletonList(new ItemStack(iBlockState.getBlock(), 1, iBlockState.getBlock().getMetaFromState(iBlockState)));
    }
}
