package com.mcgoodtime.productionline.items;

import com.mcgoodtime.productionline.init.PLBlocks;
import ic2.core.util.StackUtil;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        int meta = stack.getItemDamage();
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
        tooltip.add(info);
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
        String internalEnergy = I18n.format("ic2.item.tooltip.Store") + " " + nbt.getInteger("energy") + " EU";
        tooltip.add(internalEnergy);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        super.getSubItems(tab, items);
        ItemStack itemStack;
        NBTTagCompound nbt;

        itemStack = PLBlocks.evsu.copy();
        nbt = StackUtil.getOrCreateNbtData(itemStack);
        nbt.setInteger("energy", (int) 1E8);
        items.add(itemStack);

        itemStack = PLBlocks.cseu.copy();
        nbt = StackUtil.getOrCreateNbtData(itemStack);
        nbt.setInteger("energy", (int) 720E3);
        items.add(itemStack);

        itemStack = PLBlocks.parallelSpaceSU.copy();
        nbt = StackUtil.getOrCreateNbtData(itemStack);
        nbt.setInteger("energy", (int) 2E8);
        items.add(itemStack);
    }
}
