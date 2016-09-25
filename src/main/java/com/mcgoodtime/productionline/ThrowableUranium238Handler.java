//package com.mcgoodtime.productionline;
//
//import com.mcgoodtime.productionline.common.core.PLConfig;
//import com.mcgoodtime.productionline.common.entity.EntityThrowable;
//import ic2.api.item.IC2Items;
//import ic2.core.item.ItemMulti;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.item.ItemStack;
//
//import java.util.Random;
//
///**
// * Throwable uranium 238 right click handler
// * Created by BestOwl on 2016-05-31.
// *
// * @author BestOwl
// */
//public class ThrowableUranium238Handler implements ItemMulti.IItemRightClickHandler {
//
//    public static void registerHandler() {
//
//    }
//
//    @Override
//    public ItemStack onRightClick(ItemStack itemStack, EntityPlayer entityPlayer) {
//        ItemStack target = IC2Items.getItem("nuclear", "uranium_238");
//        if (itemStack.isItemEqual(target)) {
//            if (PLConfig.instance.throwableUran238) {
//                if (!entityPlayer.capabilities.isCreativeMode) {
//                    --itemStack.stackSize;
//                }
//
//                entityPlayer.worldObj.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F));
//                if (!entityPlayer.worldObj.isRemote) {
//                    entityPlayer.worldObj.spawnEntityInWorld(new EntityThrowable(entityPlayer.worldObj, entityPlayer, itemStack));
//                }
//            }
//        }
//        return itemStack;
//    }
//
//}
