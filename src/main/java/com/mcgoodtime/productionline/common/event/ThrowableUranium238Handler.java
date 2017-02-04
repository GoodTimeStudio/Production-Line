package com.mcgoodtime.productionline.common.event;

import com.mcgoodtime.productionline.common.core.PLConfig;
import com.mcgoodtime.productionline.common.entity.EntityThrownItem;
import ic2.api.item.IC2Items;
import ic2.core.item.ItemMulti;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;

import java.util.Random;

/**
 * Throwable uranium 238 right click handler
 * Created by BestOwl on 2016-05-31.
 *
 * @author BestOwl
 */
public class ThrowableUranium238Handler implements ItemMulti.IItemRightClickHandler {

    public static void registerHandler() {

    }

    @Override
    public ActionResult<ItemStack> onRightClick(ItemStack itemStack, EntityPlayer entityPlayer, EnumHand hand) {
        ItemStack target = IC2Items.getItem("nuclear", "uranium_238");
        if (itemStack.isItemEqual(target)) {
            if (PLConfig.instance.throwableUran238) {
                if (!entityPlayer.capabilities.isCreativeMode) {
                    --itemStack.stackSize;
                }

                entityPlayer.world.playSound(entityPlayer, entityPlayer.getPosition(),
                        SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS,
                        0.5F, 0.4F / (new Random().nextFloat() * 0.4F + 0.8F));
                if (!entityPlayer.world.isRemote) {
                    entityPlayer.world.spawnEntity(new EntityThrownItem(entityPlayer.world, entityPlayer, itemStack));
                }
            }
            return ActionResult.newResult(EnumActionResult.SUCCESS, itemStack);
        }
        return ActionResult.newResult(EnumActionResult.PASS, itemStack);
    }

}
