package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.init.GtiBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemWhiteSesame extends ItemColored {

	public ItemWhiteSesame(Block block) {
		super(block, false);
	}

	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int damage) {
		return GtiBlocks.whiteSesame.getRenderColor(itemStack.getItemDamage());
	}

	public ItemStack onItemRightClick(ItemStack itemStack, World world,
			EntityPlayer player) {
		MovingObjectPosition movingobjectposition = this
				.getMovingObjectPositionFromPlayer(world, player, true);
		if (movingobjectposition == null) {
			return itemStack;
		} else {
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				int i = movingobjectposition.blockX;
				int j = movingobjectposition.blockY;
				int k = movingobjectposition.blockZ;

				if (!world.canMineBlock(player, i, j, k)) {
					return itemStack;
				}

				if (!player.canPlayerEdit(i, j, k,
						movingobjectposition.sideHit, itemStack)) {
					return itemStack;
				}

				if (world.getBlock(i, j, k).getMaterial() == Material.clay
						&& world.getBlockMetadata(i, j, k) == 0
						&& world.isAirBlock(i, j + 1, k)) {
					// special case for handling block placement with water
					// lilies, moved to water hyacinth
					net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot
							.getBlockSnapshot(world, i, j + 1, k);
					world.setBlock(i, j + 1, k, GtiBlocks.whiteSesame);
					if (net.minecraftforge.event.ForgeEventFactory
							.onPlayerBlockPlace(
									player,
									blocksnapshot,
									net.minecraftforge.common.util.ForgeDirection.UP)
							.isCanceled()) {
						blocksnapshot.restore(true, false);
						return itemStack;
					}

					if (!player.capabilities.isCreativeMode) {
						itemStack.stackSize--;
					}
				}
			}
			return itemStack;
		}
	}
}