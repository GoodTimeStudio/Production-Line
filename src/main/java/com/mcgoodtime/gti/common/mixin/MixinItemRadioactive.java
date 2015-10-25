package com.mcgoodtime.gti.common.mixin;

import com.mcgoodtime.gti.common.core.GtiConfig;
import com.mcgoodtime.gti.common.entity.EntityUran238;
import ic2.core.Ic2Items;
import ic2.core.init.InternalName;
import ic2.core.item.ItemIC2;
import ic2.core.item.ItemRadioactive;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Created by suhao on 2015.10.24.0024.
 *
 * @author BestOwl
 */
@Mixin(ItemRadioactive.class)
public abstract class MixinItemRadioactive extends ItemIC2 {

    public MixinItemRadioactive(InternalName internalName) {
        super(internalName);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (itemStack.isItemEqual(Ic2Items.Uran238)) {
            if (GtiConfig.instance.isThrowableUran238()) {
                if (!entityPlayer.capabilities.isCreativeMode) {
                    --itemStack.stackSize;
                }
                world.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                if (!world.isRemote) {
                    world.spawnEntityInWorld(new EntityUran238(world, entityPlayer));
                }
            }
        }
        return itemStack;
    }
}
