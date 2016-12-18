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
package com.mcgoodtime.productionline.common.items;

import com.mcgoodtime.productionline.client.IItemModelProvider;
import com.mcgoodtime.productionline.common.init.PLItems;
import com.mcgoodtime.productionline.common.potion.PLPotion;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;
import java.util.WeakHashMap;

public class ItemPLFood extends ItemPL implements IItemModelProvider {

    private int timer;
    private Map<EntityPlayer, EatAmount> amountMap = new WeakHashMap<>();

    /**
     * The amount this food item heals the player.
     */
    private int healAmount;
    /**
     * 饱食度
     */
    private final float saturationModifier;
    /**
     * If this field is true, the food can be consumed even if the player don't need to eat.
     */
    private boolean alwaysEdible;

    public ItemPLFood(String name, int healAmount, float saturationModifier, boolean alwaysEdible) {
        super(name);
        this.healAmount = healAmount;
        this.saturationModifier = saturationModifier;
        this.alwaysEdible = alwaysEdible;
    }

    @Nullable
    @Override
    public ItemStack onItemUseFinish(@Nonnull ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        --stack.stackSize;

        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer entityplayer = (EntityPlayer) entityLiving;
            entityplayer.getFoodStats().addStats(healAmount, saturationModifier);
            worldIn.playSound(null, entityplayer.posX, entityplayer.posY, entityplayer.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
            this.onEaten(stack, worldIn, entityplayer);
            entityplayer.addStat(StatList.getObjectUseStats(this));
        }

        return stack;
    }

    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        if (playerIn.canEat(this.alwaysEdible)) {
            playerIn.setActiveHand(hand);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        } else {
            return new ActionResult<>(EnumActionResult.FAIL, stack);
        }
    }

    /**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slotIndex, boolean isCurrentItem) {
        if (!world.isRemote) {
            if (entity instanceof EntityPlayer)
                //1200 ticks = 1min
                if (this.timer < 1200) {
                    ++this.timer;
                } else if (this.timer == 1200) {
                    this.timer = 0;
                    amountMap.remove(entity);
                }
        }
    }

    protected ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            if (this.equals(PLItems.salt)) {
                if (amountMap.containsKey(player)) {
                    EatAmount amount = amountMap.get(player);
                    if (!stack.isItemEqual(amount.itemStack)) {
                        amountMap.remove(player);
                    }
                    //3 - 1
                    else if (amount.amount == 2) {
                        amountMap.remove(player);
                        PLPotion.salty.applyPotion(player, 0, 1);
                        return stack;
                    }

                } else {
                    amountMap.put(player, new EatAmount(stack, 0));
                }
                amountMap.get(player).amount++;
            }

        }
        return stack;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return 32;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    @Nonnull
    public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.EAT;
    }

    @Override
    public String getModelResourcePath() {
        return "food";
    }

    /**
     * Get custom resource name.
     * To use default resource name, return null.
     *
     */
    @Override
    public String getModelResourceName(int meta) {
        return null;
    }

    static class EatAmount {
        private ItemStack itemStack;
        private int amount;

        private EatAmount(ItemStack itemStack, int amount) {
            this.itemStack = itemStack;
            this.amount = amount;
        }
    }
}
