package com.mcgoodtime.productionline.common.items;

import com.mcgoodtime.productionline.common.init.PLBlocks;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Created by BestOwl on 2015.11.29.0029.
 *
 * @author BestOwl
 */
public class ItemBlockEUStorage extends ItemBlockPL {

    public ItemBlockEUStorage(Block block) {
        super(block);
    }

    /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SuppressWarnings("unchecked")
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
        super.addInformation(itemStack, player, list, bool);
        int meta = itemStack.getItemDamage();
        String info = StatCollector.translateToLocal("ic2.item.tooltip.Output") + " ";
        switch (meta) {
            case 0: info += "8192EU/t";
        }
        info += " " + StatCollector.translateToLocal("ic2.item.tooltip.Capacity") + " ";
        switch (meta) {
            case 0: info += "100m EU";
        }
        list.add(info);
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(itemStack);
        String internalEnergy = StatCollector.translateToLocal("ic2.item.tooltip.Store") + " " + nbt.getInteger("energy") + " EU";
        list.add(internalEnergy);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        super.getSubItems(item, creativeTabs, list);
        ItemStack itemStack;
        NBTTagCompound nbt;

        itemStack = PLBlocks.evsu.copy();
        nbt = StackUtil.getOrCreateNbtData(itemStack);
        nbt.setInteger("energy", (int) 1E8);
        list.add(itemStack);

        itemStack = PLBlocks.cseu.copy();
        nbt = StackUtil.getOrCreateNbtData(itemStack);
        nbt.setInteger("energy", (int) 720E3);
        list.add(itemStack);

        itemStack = PLBlocks.parallelSpaceSU.copy();
        nbt = StackUtil.getOrCreateNbtData(itemStack);
        nbt.setInteger("energy", (int) 2E8);
        list.add(itemStack);
    }
}
