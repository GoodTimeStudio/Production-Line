package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.GtiPotion;
import com.mcgoodtime.gti.common.init.GtiItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class ItemGtiFood extends ItemGti implements ITextureFolder {

	private int timer;
	private Map<EntityPlayer, EatAmount> amountMap = new HashMap<EntityPlayer, EatAmount>();

	/** The amount this food item heals the player. */
	private int healAmount;
	/** 饱食度 */
	private final float saturationModifier;
	/** If this field is true, the food can be consumed even if the player don't need to eat. */
	private boolean alwaysEdible;

	public ItemGtiFood(String name, int healAmount, float saturationModifier, boolean alwaysEdible) {
		super(name);
		this.healAmount = healAmount;
		this.saturationModifier = saturationModifier;
		this.alwaysEdible = alwaysEdible;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
		if (entityPlayer.canEat(this.alwaysEdible)) {
			entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		}

		return itemStack;
	}

	/**
	 * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
	 * update it's contents.
	 *
	 */
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slotIndex, boolean isCurrentItem) {
		if (!world.isRemote) {
			if (entity instanceof EntityPlayer)
			//1200 ticks = 1min
			if (this.timer < 1200) {
				++this.timer;
			}
			else if (this.timer == 1200) {
				this.timer = 0;
				amountMap.remove(entity);
			}
		}
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		--stack.stackSize;
		player.getFoodStats().addStats(healAmount, saturationModifier);
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

		if (!world.isRemote) {

			if (this.equals(GtiItems.salt)) {

				if (!amountMap.containsKey(player)) {
					EatAmount amount = amountMap.get(player);
					if (!stack.isItemEqual(amount.itemStack)) {
						amountMap.remove(player);
					}
					//3 - 1
					else if (amount.amount == 2) {
						amountMap.remove(player);
						GtiPotion.salty.applyPotion(player, 0, 6);
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

	@Override
	public String getTextureFolder() {
		return "food/";
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
	public EnumAction getItemUseAction(ItemStack itemStack) {
		return EnumAction.eat;
	}

	public static class EatAmount {
		private ItemStack itemStack;
		private int amount;

		private EatAmount(ItemStack itemStack, int amount) {
			this.itemStack = itemStack;
			this.amount = amount;
		}
	}
}
