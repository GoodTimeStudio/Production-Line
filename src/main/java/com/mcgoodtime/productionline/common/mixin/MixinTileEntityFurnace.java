package com.mcgoodtime.productionline.common.mixin;

import net.minecraft.block.BlockFurnace;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.ItemFireworkCharge;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.tileentity.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 */
@Mixin(TileEntityFurnace.class)
public abstract class MixinTileEntityFurnace extends TileEntityFurnace {

    @Shadow
    public ItemStack[] furnaceItemStacks;

    @Overwrite
    public void updateEntity() {
        boolean flag = this.furnaceBurnTime > 0;
        boolean flag1 = false;

        if (this.furnaceBurnTime > 0) {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote) {
            if (this.furnaceBurnTime != 0 || this.furnaceItemStacks[1] != null && this.furnaceItemStacks[0] != null) {
                if (this.furnaceBurnTime == 0 && this.canSmelt()) {
                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

                    if (this.furnaceBurnTime > 0) {
                        flag1 = true;

                        if (this.furnaceItemStacks[1] != null) {
                            --this.furnaceItemStacks[1].stackSize;

                            if (this.furnaceItemStacks[1].stackSize == 0) {
                                this.furnaceItemStacks[1] = furnaceItemStacks[1].getItem().
                                        getContainerItem(furnaceItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt()) {
                    ++this.furnaceCookTime;

                    if (this.furnaceCookTime == 200) {
                        this.furnaceCookTime = 0;
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else {
                    this.furnaceCookTime = 0;
                }
            }

            if (flag != this.furnaceBurnTime > 0) {
                flag1 = true;
                BlockFurnace.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord,
                        this.zCoord);
            }

            //-----------------------------------------------------------------------
            // productionline start

            ItemStack itemStack = this.furnaceItemStacks[1];
            if (itemStack != null) {
                if (itemStack.getItem() instanceof ItemBlock) {
                    if (((ItemBlock) itemStack.getItem()).field_150939_a.getMaterial() == Material.tnt) {
                        this.doExplosion();
                    }
                }
                else if (itemStack.getItem().getPotionEffect(itemStack).equals(PotionHelper.gunpowderEffect)) {
                    this.doExplosion();
                }
                else if (itemStack.getItem() instanceof ItemFirework || itemStack.getItem()
                        instanceof ItemFireworkCharge) {
                    this.doExplosion();
                }
            }

            // productionline end
            //-----------------------------------------------------------------------
        }

        if (flag1) {
            this.markDirty();
        }
    }

    private void doExplosion() {
        this.worldObj.createExplosion(null, this.xCoord, this.yCoord, this.zCoord, 4.0F, true);
    }

    @Shadow
    public abstract boolean canSmelt();
}
