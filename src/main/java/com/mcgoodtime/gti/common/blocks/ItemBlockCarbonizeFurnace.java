package com.mcgoodtime.gti.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.List;

/**
 * Created by suhao on 2015/5/23.
 */
public class ItemBlockCarbonizeFurnace extends ItemBlock {

    public ItemBlockCarbonizeFurnace(Block block) {
        super(block);
    }

    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltipList, boolean par4) {
        tooltipList.add(I18n.format("gti.tooltip.CarbonizeFurnace"));
    }
}
