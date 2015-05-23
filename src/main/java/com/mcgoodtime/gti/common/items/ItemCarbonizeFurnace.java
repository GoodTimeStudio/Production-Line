package com.mcgoodtime.gti.common.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by suhao on 2015/5/23.
 */
public class ItemCarbonizeFurnace extends ItemBlock {

    public ItemCarbonizeFurnace(Block block) {
        super(block);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltipList, boolean boo) {
        tooltipList.add(I18n.format("gti.tooltip.CarbonizeFurnace"));
    }
}
