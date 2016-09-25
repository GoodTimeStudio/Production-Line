///*
// * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
// *
// * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
// * Copyright (c) contributors
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in
// * all copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// * THE SOFTWARE.
// */
//package com.mcgoodtime.productionline.common.event;
//
//import com.mcgoodtime.productionline.common.PLPotion;
//import com.mcgoodtime.productionline.common.entity.EntityThrowable;
//import com.mcgoodtime.productionline.common.init.PLAchievement;
//import com.mcgoodtime.productionline.common.init.PLBlocks;
//import com.mcgoodtime.productionline.common.init.PLItems;
//import net.minecraftforge.fml.common.eventhandler.Event;
//import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
//import net.minecraftforge.fml.common.gameevent.PlayerEvent;
//import ic2.core.IC2Potion;
//import ic2.core.Ic2Items;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.item.EntityItem;
//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.DamageSource;
//import net.minecraft.util.MovingObjectPosition;
//import net.minecraft.world.biome.BiomeGenBase;
//import net.minecraftforge.event.entity.player.FillBucketEvent;
//
///*
// * Created by suhao on 2015/5/17.
// */
//
//public class PLEvent {
//    @SubscribeEvent
//    public void onPlayerCrafting(PlayerEvent.ItemCraftedEvent event) {
//        if (event.crafting.getItem().equals(PLBlocks.carbonizeFurnace.getItem())) {
//            event.player.addStat(PLAchievement.getCarbonizeFurnace, 1);
//        }
//    }
//
//    @SubscribeEvent
//    public void onPlayerPickup(PlayerEvent.ItemPickupEvent event) {
//        if (event.pickedUp.getEntityItem().isItemEqual(new ItemStack(PLBlocks.oreIridium))) {
//            event.player.addStat(PLAchievement.getIrOre, 1);
//        }
//    }
//
//    @SubscribeEvent
//    public void onBucketFill(FillBucketEvent event) {
//        if (event.entityPlayer != null) {
//            BiomeGenBase biome = event.world.getBiomeGenForCoords(event.target.blockX, event.target.blockZ);
//            if (biome == BiomeGenBase.ocean || biome == BiomeGenBase.deepOcean || biome == BiomeGenBase.frozenOcean) {
//                event.setResult(Event.Result.ALLOW);
//                event.result = new ItemStack(PLItems.saltWaterBucket);
//            }
//        }
//    }
//
//    @SubscribeEvent
//    public void onEntityThrowableImpact(EntityThrowableImpactEvent event) {
//        if (event.entityThrowable.getThrowItem().getItem().equals(PLItems.packagedSalt)) {
//            for (int i = 0; i < 8; ++i) {
//                float fmod = (float) (1.2 - (Math.random() * 2.4));
//                float f1mod = (float) (0.5 - (Math.random() * 1.0));
//                float f2mod = (float) (1.2 - (Math.random() * 2.4));
//
//                event.entityThrowable.worldObj.spawnParticle("iconcrack_" + Item.getIdFromItem(PLItems.salt), event.entityThrowable.posX + fmod,
//                        event.entityThrowable.posY + f1mod, event.entityThrowable.posZ + f2mod, 0.1D, 0.1D, 0.1D);
//
//                if (!event.entityThrowable.worldObj.isRemote) {
//                    EntityItem entityItem = new EntityItem(event.entityThrowable.worldObj,
//                            event.entityThrowable.posX + fmod, event.entityThrowable.posY + f1mod,
//                            event.entityThrowable.posZ + f2mod, new ItemStack(PLItems.salt));
//                    event.entityThrowable.worldObj.spawnEntityInWorld(entityItem);
//                }
//            }
//            this.onImpact(event.entityThrowable, event.movingObjectPosition, new PotionEffect(PLPotion.salty.id, 0, 3));
//        }
//        else if (event.entityThrowable.getThrowItem().isItemEqual(Ic2Items.Uran238)) {
//            this.onImpact(event.entityThrowable, event.movingObjectPosition, new PotionEffect(IC2Potion.radiation.id, 200, 0));
//        }
//        else {
//            this.onImpact(event.entityThrowable, event.movingObjectPosition, null);
//        }
//    }
//
//    private void onImpact(EntityThrowable entity, MovingObjectPosition movingObjectPosition, PotionEffect potionEffect) {
//        if (movingObjectPosition.entityHit != null) {
//            movingObjectPosition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(entity, entity.getThrower()), 3F);
//            if (movingObjectPosition.entityHit instanceof EntityLivingBase && potionEffect != null) {
//                ((EntityLivingBase) movingObjectPosition.entityHit).addPotionEffect(potionEffect);
//            }
//        }
//        entity.worldObj.spawnParticle("iconcrack_" + Item.getIdFromItem(entity.getThrowItem().getItem()), entity.posX, entity.posY, entity.posZ, 0.1D, 0.1D, 0.1D);
//        if (!entity.worldObj.isRemote) {
//            entity.setDead();
//        }
//    }
//}
