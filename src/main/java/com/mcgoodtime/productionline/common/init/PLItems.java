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
package com.mcgoodtime.productionline.common.init;

import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.core.PLConfig;
//import coreom.mcgoodtime.productionline.common.entity.EntityThrowable;
import com.mcgoodtime.productionline.common.items.*;
import com.mcgoodtime.productionline.common.items.tools.PLToolMaterial;
//import com.mcgoodtime.productionline.common.items.tools.ItemGravityRay;
//import com.mcgoodtime.productionline.common.items.tools.ItemPLTreetap;
//import com.mcgoodtime.productionline.common.items.tools.ToolPL;
import net.minecraftforge.fml.common.IFuelHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * The list of all those items in GoodTime-Industrial.
 */
public class PLItems implements IFuelHandler {

	public static Item diamondApple;
	public static Item packagedSalt;
	public static Item iridiumAxe;
	public static Item iridiumSpade;
	public static Item iridiumSword;
	public static Item iridiumPickaxe;
	public static Item salt;
	public static Item saltWaterBucket;

	public static Item ironTreetap;
	public static Item bronzeTreetap;
	public static Item leadTreetap;
	public static Item refinedIronTreetap;
	public static Item advancedAlloyTreetap;
	public static Item carbonTreetap;

    public static Item ceu;
	public static Item gravityRay;

	public static Item record_MusicSpring;
	//--------------------------------------
	public static ItemStack crushedIridium;
	public static ItemStack cleanedCrushedIridium;
	public static ItemStack dustIridium;
	public static ItemStack smallDustIridium;
	public static ItemStack ingotIridium;
	public static ItemStack denseDiamondPlate;
	public static ItemStack diamondPlate;

	public static ItemStack heatInsulationPlate;
	public static ItemStack roller;
	public static ItemStack heatInsulationMaterial;
	public static ItemStack smallCompressedWaterHyacinth;
	public static ItemStack airBrakeUnit;
	public static ItemStack bambooCharcoal;
	public static ItemStack carbonTube;
	public static ItemStack redstoneModule;
	public static ItemStack lazuliModule;
	public static ItemStack obsidianPlateGravityField;
	public static ItemStack electronicCircuitControl;
	public static ItemStack electronicCircuitCore;
	public static ItemStack pulseElectronicCircuitControl;
	public static ItemStack pulseElectronicCircuitCore;
	public static ItemStack cyclotronParticleAccelerator;
	public static ItemStack calculateUnit;
	public static ItemStack calculateChunk;
	public static ItemStack calculateArray;
	public static ItemStack floatPointCalculationsRegion;
	public static ItemStack parallelSpaceConverter;
	public static ItemStack uuMatterCore;
	public static ItemStack obsidianMechanicalFrame;
	public static ItemStack obsidianMechanicalCasing;
	public static ItemStack carbonCrystal;
	public static ItemStack enderCalculationCrystal;
	public static ItemStack millTeeth;
	public static ItemStack millWheel;
	public static ItemStack rigidPaper;
	public static ItemStack rigidPaperPack;
    public static ItemStack advSolarLensUnit;
    public static ItemStack advSolarLensGroup;
    public static ItemStack advSolarLensCluster;
	public static ItemStack tiles;
	public static ItemStack bioFuel;
	public static ItemStack paperBag;
	public static ItemStack corrugatedPaper;
	public static ItemStack woodPulp;
	public static ItemStack sawdust;
	public static ItemStack faggot;
	public static ItemStack firewood;
	public static ItemStack coarseBatten;

	public static void init() {
//        diamondApple = new ItemDiamondApple();

//		ironTreetap = new ItemPLTreetap("IronTreetap", 32);
//		bronzeTreetap = new ItemPLTreetap("BronzeTreetap", 32);
//		leadTreetap = new ItemPLTreetap("LeadTreetap", 48);
//		refinedIronTreetap = new ItemPLTreetap("RefinedIronTreetap", 64);
//		advancedAlloyTreetap = new ItemPLTreetap("AdvancedAlloyTreetap", 64);
//		carbonTreetap = new ItemPLTreetap("CarbonTreetap", 128);

//        record_MusicSpring = new ItemPLRecord("record_MusicSpring");
//		salt = new ItemPLFood("Salt", 0, 10F, true);
//        ceu = new ItemCEU();
//		gravityRay = new ItemGravityRay();

		//MultiMetaItem registry
		new ItemOre();
		new ItemMisc();
//		new ItemDryFood();

        // special registry TODO: Better registry system

//		packagedSalt = new ItemPL("PackagedSalt") {
//			/**
//			 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
//			 */
//			@Override
//			public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
//				if (PLConfig.instance.throwableUran238) {
//					if (!entityPlayer.capabilities.isCreativeMode) {
//						--itemStack.stackSize;
//					}
//					world.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
//					if (!world.isRemote) {
//						world.spawnEntityInWorld(new EntityThrowable(world, entityPlayer, itemStack));
//					}
//				}
//
//				return itemStack;
//			}
//		};

//		saltWaterBucket = new ItemBucket(Blocks.water);
//		saltWaterBucket.setCreativeTab(ProductionLine.creativeTabGti)
//				.setUnlocalizedName(ProductionLine.MOD_NAME + ".SaltWaterBucket").setTextureName(ProductionLine.RESOURCE_DOMAIN + ":itemSaltWaterBucket");
//
//        iridiumPickaxe = ToolPL.registerPickaxe(PLToolMaterial.iridium, "IridiumPickaxe");
//        iridiumAxe = ToolPL.registerAxe(PLToolMaterial.iridium, "IridiumAxe");
//        iridiumSpade = ToolPL.registerSpade(PLToolMaterial.iridium, "IridiumSpade");
//        iridiumSword = ToolPL.registerSword(PLToolMaterial.iridium, "IridiumSword");
//
//        // TODO: Better registry system
//		GameRegistry.registerItem(saltWaterBucket, "SaltWaterBucket");
//        GameRegistry.registerFuelHandler(new PLItems());
    }

	@Override
	public int getBurnTime(ItemStack fuel) {
		if(fuel.isItemEqual(bioFuel)){
			return  1000;
		}
		if(fuel.isItemEqual(sawdust)){
			return 50;
		}
		if(fuel.isItemEqual(faggot)){
			return  1200;
		}
		if(fuel.isItemEqual(firewood)) {
			return  100;
		}
		if(fuel.isItemEqual(coarseBatten)){
			return 100;
		}
		if (fuel.isItemEqual(bambooCharcoal)) {
			return 800;
		}
		if (fuel.isItemEqual(smallCompressedWaterHyacinth)) {
			return 400;
		}
		if (fuel.getItem().equals(
				Item.getItemFromBlock(PLBlocks.waterHyacinth))) {
			return 100;
		}
//		if(fuel.getItem().equals(
//				Item.getItemFromBlock(PLBlocks.dryLog))){
//			return 300;
//		}
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
		ret.stackSize = count;
		return ret;
	}
}
