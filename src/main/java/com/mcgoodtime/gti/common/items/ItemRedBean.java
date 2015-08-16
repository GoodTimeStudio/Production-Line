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

public class ItemRedBean extends ItemColored {

    public ItemRedBean(Block block) {
        super(block, true);
        this.setUnlocalizedName("gti.item.RedBean");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemStack(ItemStack itemStack, int damage) {
        return GtiBlocks.redBean.getRenderColor(itemStack.getItemDamage());
    }
}