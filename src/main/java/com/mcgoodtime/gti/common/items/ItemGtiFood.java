package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.PotionGti;
import com.mcgoodtime.gti.common.init.GtiItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemGtiFood extends ItemGti implements ITextureFolder {

	/** The amount this food item heals the player. */
	private int healAmount;
	private final float saturationModifier;
	/** If this field is true, the food can be consumed even if the player don't need to eat. */
	private boolean alwaysEdible;

	public ItemGtiFood(String name, int healAmount, float saturationModifier) {
		super(name);
		this.healAmount = healAmount;
		this.saturationModifier = saturationModifier;
	}

	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		--stack.stackSize;
		player.getFoodStats().addStats(healAmount, saturationModifier);
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

		if (!world.isRemote) {
			if (this.equals(GtiItems.salt)) {
				player.addPotionEffect(new PotionEffect(PotionGti.bitSalty.id, 6000, 0));
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
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 32;
	}

	/**
	 * returns the action that specifies what animation to play when the items is being used
	 */
	public EnumAction getItemUseAction(ItemStack itemStack) {
		return EnumAction.eat;
	}


}
