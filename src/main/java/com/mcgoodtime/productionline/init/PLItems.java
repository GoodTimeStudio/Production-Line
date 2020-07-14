/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.mcgoodtime.productionline.init;

import com.mcgoodtime.productionline.core.PLConfig;
import com.mcgoodtime.productionline.core.ProductionLine;
import com.mcgoodtime.productionline.entity.EntityThrownItem;
import com.mcgoodtime.productionline.items.ItemCrafting;
import com.mcgoodtime.productionline.items.ItemDiamondApple;
import com.mcgoodtime.productionline.items.ItemOre;
import com.mcgoodtime.productionline.items.ItemPL;
import com.mcgoodtime.productionline.items.ItemPLFood;
import com.mcgoodtime.productionline.items.ItemPLRecord;
import com.mcgoodtime.productionline.items.tools.ItemGravityRay;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static com.mcgoodtime.productionline.core.ProductionLine.MOD_ID;

//import coreom.mcgoodtime.productionline.common.entity.EntityThrownItem;
//import com.mcgoodtime.productionline.items.tools.ItemGravityRay;
//import com.mcgoodtime.productionline.items.tools.ItemPLTreetap;
//import com.mcgoodtime.productionline.items.tools.ToolPL;

/**
 * The list of all those items in GoodTime-Industrial.
 */
public class PLItems implements IFuelHandler {

    public static Item diamondApple;
    public static Item packagedSalt;
    public static Item iridiumSword;
    public static Item salt;
    public static Item saltWaterBucket;
    public static Item hammer;


    public static Item gravityRay;

    public static Item record_MusicSpring;

    public static Item itemCrafting;
    public static Item itemOre;
    
    //This is New Pans`s Item
    public static Item liquidExpBucket;
    public static Item enderPearlBucket;
    public static Item brineBucket;
    public static Item toadCoin;
    public static Item superTrashBag;

    
    //--------------------------------------
    public static ItemStack dustIridium;
    public static ItemStack ingotIridium;
    public static ItemStack yourHouseBombed;
    public static ItemStack smallCompressedWaterHyacinth;
    public static ItemStack redstoneModule;
    public static ItemStack lazuliModule;
    public static ItemStack advSolarLensUnit;
    public static ItemStack advSolarLensGroup;
    public static ItemStack advSolarLensCluster;
    public static ItemStack opticalGlass;
    public static ItemStack sawdust;
    public static ItemStack rigidPaper;

    public static ItemStack heatInsulationMaterial;
    public static ItemStack heatResistantBrick;
    
    //This is New Plans`s ItemStack
    public static ItemStack enderPearlPowder;
    public static ItemStack CPUmk1;
    public static ItemStack CPUmk2;
    public static ItemStack CPUmk3;
    public static ItemStack fakeHead;
    public static ItemStack lifeConverter;
    public static ItemStack condensedImpurities;
    public static ItemStack heartOfLava;
    public static ItemStack heartOfPureWhite;
    public static ItemStack heartOfEnder;


    public static void init() {
        diamondApple = new ItemDiamondApple();


        record_MusicSpring = new ItemPLRecord("record_musicspring", PLSounds.recordMusicSpring);
        salt = new ItemPLFood("salt", 0, 10F, true);
        gravityRay = new ItemGravityRay();

        //MultiMetaItem registry
        itemOre = new ItemOre();
        itemCrafting = new ItemCrafting();


        // special registry TODO: Better registry system

        packagedSalt = new ItemPL("packaged_salt") {
            @Override
            public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
                ItemStack itemStack = playerIn.getHeldItem(handIn);
                if (PLConfig.instance.throwablePackagedSalt) {
                    if (!playerIn.capabilities.isCreativeMode) {
                        itemStack.shrink(1);
                    }
                    worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                    if (!worldIn.isRemote) {
                        worldIn.spawnEntity(new EntityThrownItem(worldIn, playerIn, itemStack));
                    }
                }
                return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
            }
        };

        saltWaterBucket = new ItemBucket(Blocks.WATER);
        saltWaterBucket.setCreativeTab(ProductionLine.creativeTabPL)
                .setUnlocalizedName(MOD_ID + ".saltwater_bucket");

//        iridiumPickaxe = ToolPL.registerPickaxe(PLToolMaterial.iridium, "iridium_pickaxe");
//        iridiumAxe = ToolPL.registerAxe(PLToolMaterial.iridium, "iridium_axe");
//        iridiumSpade = ToolPL.registerSpade(PLToolMaterial.iridium, "iridium_spade");
//        iridiumSword = ToolPL.registerSword(PLToolMaterial.iridium, "iridium_sword");

        // TODO: Better registry system
        ForgeRegistries.ITEMS.register(saltWaterBucket);
        GameRegistry.registerFuelHandler(new PLItems());
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.isItemEqual(sawdust)) {
            return 50;
        }
        if (fuel.isItemEqual(smallCompressedWaterHyacinth)) {
            return 400;
        }
        if (fuel.getItem().equals(
                Item.getItemFromBlock(PLBlocks.waterHyacinth))) {
            return 100;
        }
        if (fuel.isItemEqual(PLBlocks.compressedWaterHyacinth)) {
            return 800;
        }
        if (fuel.isItemEqual(PLBlocks.dehydratedWaterHyacinthblock)) {
            return 1000;
        }
        return 0;
    }

    public static ItemStack getItems(ItemStack itemStack, int count) {
        ItemStack ret = itemStack.copy();
        ret.setCount(count);
        return ret;
    }
}
