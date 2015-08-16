package com.mcgoodtime.gti.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGtiFood extends ItemGti implements ITextureFolder {

	public ItemGtiFood(String name) {
		super(name);
		// TODO �Զ����ɵĹ��캯�����
	}

	public ItemStack onEaten(ItemStack stack, World world, EntityPlayer player) {
		return stack;
	}
}
