package com.mcgoodtime.productionline.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFirework;
import net.minecraft.item.ItemFireworkCharge;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
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
public abstract class MixinTileEntityFurnace extends TileEntity {

    @Shadow
    private ItemStack[] furnaceItemStacks;

    @Shadow
    public abstract boolean isBurning();

    @Inject(method = "update", at = @At("RETURN"))
    private void onUpdate(CallbackInfo callbackInfo) {
        if (!this.world.isRemote) {
            if (this.isBurning()) {
                ItemStack itemStack = this.furnaceItemStacks[0];
                if (itemStack != null) {
                    if (itemStack.getItem() instanceof ItemBlock) {
                        Block block = ((ItemBlock) itemStack.getItem()).block;
                        if (block.getMaterial(block.getStateFromMeta(itemStack.getMetadata())) == Material.TNT) {
                            this.doExplosion();
                        }
                    } else if (itemStack.getItem() == Items.GUNPOWDER) {
                        this.doExplosion();
                    } else if (itemStack.getItem() instanceof ItemFirework || itemStack.getItem()
                            instanceof ItemFireworkCharge) {
                        this.doExplosion();
                    }
                }
            }
        }
    }

    private void doExplosion() {
        this.world.createExplosion(null, this.getPos().getX(), this.getPos().getY(), this.getPos().getZ(), 4.0F, true);
        this.world.destroyBlock(this.getPos(), true);
        this.invalidate();
    }
}
