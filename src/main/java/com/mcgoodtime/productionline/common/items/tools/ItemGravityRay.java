package com.mcgoodtime.productionline.common.items.tools;

import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.entity.EntityRay;
import com.mcgoodtime.productionline.common.items.ItemElectricPL;
import ic2.api.item.ElectricItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

/**
 * Created by BestOwl on 2015.12.7.0007.
 *
 * @author BestOwl
 */
public class ItemGravityRay extends ItemElectricPL {

    public ItemGravityRay() {
        super("GravityRay", 3, (int) 11E6);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityLivingBase player, int itemInUseCount) {
        if (ElectricItem.manager.getCharge(itemStack) >= 100) {
            int i = this.getMaxItemUseDuration(itemStack) - itemInUseCount;

            float damge = (float) i / 20.0F;
            damge = (damge * damge + damge * 2.0F) / 3.0F;
            if ((double)damge < 0.1D) {
                return;
            }
            if (damge > 1.0F) {
                damge = 1.0F;
            }

            world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_SHOOT,
                    SoundCategory.PLAYERS, 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + damge * 0.5F);
            if (!(player instanceof EntityPlayer
                    && ((EntityPlayer) player).capabilities.isCreativeMode)) {
                ElectricItem.manager.discharge(itemStack, 100, this.tier, false, true, false);
            }
            if (!world.isRemote) {
                world.spawnEntityInWorld(new EntityRay(world, player, damge * 2.0F));
            }
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (player.capabilities.isCreativeMode || ElectricItem.manager.getCharge(stack) >= 100) {
            player.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        }
        return new ActionResult<>(EnumActionResult.PASS, stack);
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    @Nonnull
    public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.BOW;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return 72000;
    }

}
