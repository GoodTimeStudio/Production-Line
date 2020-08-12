/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mcgoodtime.productionline.event;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;

/**
 * Production Line event listener.
 *
 * @author BestOwl, liach
 */
@Mod.EventBusSubscriber
public class PLEvent {/*
    @SubscribeEvent
    public void onPlayerCrafting(PlayerEvent.ItemCraftedEvent event) {
        if (event.crafting.getItem().equals(PLBlocks.carbonizeFurnace.getItem())) {
            event.player.addStat(PLAchievement.getCarbonizeFurnace, 1);
        }
    }

    @SubscribeEvent
    public void onPlayerPickup(PlayerEvent.ItemPickupEvent event) {
        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(PLBlocks.oreIridium))) {
            event.player.addStat(PLAchievement.getIrOre, 1);
        }
    }

    @SubscribeEvent
    public void onBucketFill(FillBucketEvent event) {
        if (event.getEntityPlayer() != null) {
            Biome biome = event.getWorld().getBiomeForCoordsBody(event.getTarget().getBlockPos());
            if (biome == Biomes.OCEAN || biome == Biomes.DEEP_OCEAN || biome == Biomes.FROZEN_OCEAN) {
                event.setResult(Event.Result.ALLOW);
                event.setFilledBucket(new ItemStack(PLItems.saltWaterBucket));
            }
        }
    }

    @SubscribeEvent
    public void onEntityThrowableImpact(EntityThrowableImpactEvent event) {
        Optional<ItemStack> optItemStack = event.entityThrownItem.getThrowItem();
        if (!optItemStack.isPresent()) {
            return;
        }
        ItemStack stack = optItemStack.get();
        if (stack.getItem().equals(PLItems.packagedSalt)) {
            for (int i = 0; i < 8; ++i) {
                float fmod = (float) (1.2 - (Math.random() * 2.4));
                float f1mod = (float) (0.5 - (Math.random() * 1.0));
                float f2mod = (float) (1.2 - (Math.random() * 2.4));

                event.entityThrownItem.world.spawnParticle(EnumParticleTypes.ITEM_CRACK,
                        event.entityThrownItem.posX + fmod, event.entityThrownItem.posY + f1mod,
                        event.entityThrownItem.posZ + f2mod, 0.1D, 0.1D, 0.1D, Item.getIdFromItem(PLItems.salt));

                if (!event.entityThrownItem.world.isRemote) {
                    EntityItem entityItem = new EntityItem(event.entityThrownItem.world,
                            event.entityThrownItem.posX + fmod, event.entityThrownItem.posY + f1mod,
                            event.entityThrownItem.posZ + f2mod, new ItemStack(PLItems.salt));
                    event.entityThrownItem.world.spawnEntity(entityItem);
                }
            }
            this.onImpact(event.entityThrownItem, event.movingObjectPosition, new PotionEffect(PLPotion.salty, 0, 3));
        } else if (stack.isItemEqual(IC2Items.getItem("Uran238"))) {
            this.onImpact(event.entityThrownItem, event.movingObjectPosition, new PotionEffect(IC2Potion.radiation, 200, 0));
        } else {
            this.onImpact(event.entityThrownItem, event.movingObjectPosition, null);
        }
    }

    private void onImpact(EntityThrownItem entity, RayTraceResult movingObjectPosition, PotionEffect potionEffect) {
        if (movingObjectPosition.entityHit != null) {
            movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(entity, entity.getThrower()), 3F);
            if (movingObjectPosition.entityHit instanceof EntityLivingBase && potionEffect != null) {
                ((EntityLivingBase) movingObjectPosition.entityHit).addPotionEffect(potionEffect);
            }
        }
        entity.world.spawnParticle(EnumParticleTypes.ITEM_CRACK, entity.posX, entity.posY, entity.posZ, 0.1D, 0.1D,
                0.1D, Item.getIdFromItem(entity.getThrowItem().get().getItem()));
        if (!entity.world.isRemote) {
            entity.setDead();
            entity.world.spawnEntity(new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ,
                    entity.getThrowItem().get()));
        }
    }*/

    public Boolean canLogistics;
    public BlockPos pos;

    @SubscribeEvent
    public void onBlockPlayerPlaced(BlockEvent.EntityPlaceEvent event) {
        TileEntity te = event.getWorld().getTileEntity(event.getPos());
        if (te.hasCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null)) {
            canLogistics = true;
        }else if(te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY,null) && te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)){
            canLogistics = true;
        }else if(te.hasCapability(CapabilityEnergy.ENERGY,null)){
            canLogistics = true;
        }else{
            canLogistics = false;
        }
        if(canLogistics){
            this.pos=te.getPos();
        }

    }


}
