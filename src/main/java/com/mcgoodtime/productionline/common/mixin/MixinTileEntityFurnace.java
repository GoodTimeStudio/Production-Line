package com.mcgoodtime.productionline.common.mixin;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.ItemFireworkCharge;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionHelper;
import net.minecraft.tileentity.TileEntityFurnace;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 */
@Mixin(TileEntityFurnace.class)
public abstract class MixinTileEntityFurnace extends TileEntityFurnace {

    @Shadow
    private ItemStack[] furnaceItemStacks;

    @Inject(method = "update", at = @At("RETURN"))
    private void onUpdate(CallbackInfo callbackInfo) {
//        if (!this.worldObj.isRemote) {
//            if (this.isBurning()) {
//                ItemStack itemStack = this.furnaceItemStacks[0];
//                if (itemStack != null) {
//                    if (itemStack.getItem() instanceof ItemBlock) {
//                        if (((ItemBlock) itemStack.getItem()).block.getMaterial() == Material.tnt) {
//                            this.doExplosion();
//                        }
//                    } else if (itemStack.getItem().getPotionEffect(itemStack).equals(PotionHelper.gunpowderEffect)) {
//                        this.doExplosion();
//                    } else if (itemStack.getItem() instanceof ItemFirework || itemStack.getItem()
//                            instanceof ItemFireworkCharge) {
//                        this.doExplosion();
//                    }
//                }
//            }
//        }
    }

    private void doExplosion() {
        this.worldObj.createExplosion(null, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 4.0F, true);
    }
}
