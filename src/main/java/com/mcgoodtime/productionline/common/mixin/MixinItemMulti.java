package com.mcgoodtime.productionline.common.mixin;

import com.mcgoodtime.productionline.common.event.ThrowableUranium238Handler;
import ic2.core.block.state.EnumProperty;
import ic2.core.block.state.IIdProvider;
import ic2.core.item.ItemIC2;
import ic2.core.item.ItemMulti;
import ic2.core.item.type.NuclearResourceType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * ThrowableUranium238Handler registry.
 * Created by BestOwl on 2016-06-01.
 */
@Mixin(ItemMulti.class)
public abstract class MixinItemMulti<T extends Enum<T> & IIdProvider> extends ItemIC2 {

    MixinItemMulti() {
        super(null);
    }

    @Shadow
    private EnumProperty<T> typeProperty;

    @Inject(method = "<init>", at = @At("RETURN"))
    @SuppressWarnings("unchecked")
    private void onInstantiation(CallbackInfo callbackInfo) {
        if (this.typeProperty.getValue("type") instanceof NuclearResourceType) {
            this.setRightClickHandler((T) NuclearResourceType.uranium_238, new ThrowableUranium238Handler());
        }
    }

    @Shadow
    public abstract void setRightClickHandler(T type, ItemMulti.IItemRightClickHandler handler);
}
