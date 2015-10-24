package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.GtiPotion;
import com.mcgoodtime.gti.common.init.GtiItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGtiFood extends ItemGti implements ITextureFolder {

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
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (entityPlayer.canEat(this.alwaysEdible))
		{
			entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
		}

		return itemStack;
	}

	@Override
	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		--stack.stackSize;
		player.getFoodStats().addStats(healAmount, saturationModifier);
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

		if (!world.isRemote) {
			if (this.equals(GtiItems.salt)) {
				GtiPotion.Salty.applyPotion(player, 1000, 2);
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


}
