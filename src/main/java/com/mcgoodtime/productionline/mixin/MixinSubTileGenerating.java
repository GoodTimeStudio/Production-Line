package com.mcgoodtime.productionline.mixin;

import net.minecraft.tileentity.TileEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import vazkii.botania.api.mana.IManaCollector;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileGenerating;

@Mixin(value=SubTileGenerating.class,remap = false)
public abstract class MixinSubTileGenerating extends SubTileEntity implements ISubTileGenerating{

    @Shadow
    protected TileEntity linkedCollector;

    @Shadow
    protected int mana;

    private int growth;

    @Shadow
    public abstract boolean isValidBinding();

    @Inject(method = "update",at=@At("HEAD"))
    public void onUpdate() {
        canEnhance();
    }

    @Overwrite
    public void emptyManaIntoCollector() {
        if(linkedCollector != null && isValidBinding()) {
            IManaCollector collector = (IManaCollector) linkedCollector;
            if(!collector.isFull() && mana > 0) {
                int manaval = Math.min(mana, collector.getMaxMana() - collector.getCurrentMana());
                mana -= manaval;
                    collector.recieveMana(manaval+growth);
            }
        }
    }

    public boolean canEnhance(){
        if(this.mana>0){
            return true;
        }else{
            return false;
        }
    }

    public void setGrowth(int growth){
       this.growth = growth;
    }


}
