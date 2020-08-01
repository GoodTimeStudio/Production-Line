package com.mcgoodtime.productionline.mixin;

import org.spongepowered.asm.mixin.Mixin;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileGenerating;

@Mixin(SubTileGenerating.class)
public abstract class MixinSubTileGenerating extends SubTileEntity {

}
