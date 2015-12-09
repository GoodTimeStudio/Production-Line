package com.mcgoodtime.gti.common.mixin;

import com.mcgoodtime.gti.common.GtiUtil;
import com.mcgoodtime.gti.common.core.GtiConfig;
import com.mcgoodtime.gti.common.entity.EntityThrowableGti;
import ic2.core.Ic2Items;
import ic2.core.init.InternalName;
import ic2.core.item.ItemIC2;
import ic2.core.item.ItemRadioactive;
import net.minecraft.entity.player.EntityPlayer;
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
                GtiUtil.throwItemByPlayer(new EntityThrowableGti(world, entityPlayer, itemStack));
            }
        }
        return itemStack;
    }
}
