package com.mcgoodtime.gti.common.mixin;

import net.minecraftforge.fluids.FluidTank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

/**
 * Created by BestOwl on 2015.11.22.0022.
 *
 * @author BestOwl
 */
@Mixin(FluidTank.class)
public abstract class MixinFluidTank extends FluidTank {
    public MixinFluidTank(int capacity) {
        super(capacity);
    }

}
