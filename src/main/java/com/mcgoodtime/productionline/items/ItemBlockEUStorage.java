package com.mcgoodtime.productionline.items;

import com.mcgoodtime.productionline.init.PLBlocks;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
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
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(@Nonnull ItemStack itemStack, @Nonnull EntityPlayer player, @Nonnull List<String> list, boolean bool) {
        super.addInformation(itemStack, player, list, bool);
        int meta = itemStack.getItemDamage();
        String info = I18n.format("ic2.item.tooltip.Output") + " ";
        switch (meta) {
            case 0:
                info += "8192EU/t";
        }
        info += " " + I18n.format("ic2.item.tooltip.Capacity") + " ";
        switch (meta) {
            case 0:
                info += "100m EU";
        }
        list.add(info);
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(itemStack);
        String internalEnergy = I18n.format("ic2.item.tooltip.Store") + " " + nbt.getInteger("energy") + " EU";
        list.add(internalEnergy);
    }

    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(@Nonnull Item item, @Nonnull CreativeTabs creativeTabs, @Nonnull List<ItemStack> list) {
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
