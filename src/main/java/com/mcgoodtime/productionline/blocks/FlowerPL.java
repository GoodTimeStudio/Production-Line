package com.mcgoodtime.productionline.blocks;

import com.mcgoodtime.productionline.core.ProductionLine;
import lib.LibBlockNames;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import javax.annotation.Nonnull;

public class FlowerPL extends BlockSpecialFlower {

    static{
        ProductionLine.subtilesForCreativeMenu.add(LibBlockNames.THE_FLOWER_OF_OSIRIS);
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, @Nonnull NonNullList<ItemStack> stacks) {
        for(String s : ProductionLine.subtilesForCreativeMenu) {
            stacks.add(ItemBlockSpecialFlower.ofType(s));
            if(BotaniaAPI.miniFlowers.containsKey(s))
                stacks.add(ItemBlockSpecialFlower.ofType(BotaniaAPI.miniFlowers.get(s)));
        }
    }
}
