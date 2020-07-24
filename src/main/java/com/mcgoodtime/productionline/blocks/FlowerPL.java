package com.mcgoodtime.productionline.blocks;

import com.mcgoodtime.productionline.core.ProductionLine;
import com.mcgoodtime.productionline.init.PLBlocks;
import lib.LibBlockNames;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

import javax.annotation.Nonnull;

import static com.mcgoodtime.productionline.core.ProductionLine.MOD_ID;
import static com.mcgoodtime.productionline.core.ProductionLine.creativeTabPL;

public class FlowerPL extends BlockSpecialFlower {

    public FlowerPL(){
        this.setCreativeTab(creativeTabPL);
    }

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
