/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://github.com/Minecraft-GoodTime>
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
package com.mcgoodtime.gti.common.init;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.core.GtiConfig;
import com.mcgoodtime.gti.common.entity.EntityPackagedSalt;
import com.mcgoodtime.gti.common.items.ItemGti;
import com.mcgoodtime.gti.common.items.ItemGtiFood;
import com.mcgoodtime.gti.common.items.ItemGtiRecord;
import com.mcgoodtime.gti.common.items.ItemMultiDamage;
import com.mcgoodtime.gti.common.items.tools.GtiToolMaterial;
import com.mcgoodtime.gti.common.items.tools.ItemGtiTreetap;
import com.mcgoodtime.gti.common.items.tools.ToolGti;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

/**
 * The list of all those items in GoodTime-Industrial.
 */
public class GtiItems implements IFuelHandler {

	public static Item diamondApple;
	public static Item packagedSalt;
	public static Item iridiumAxe;
	public static Item iridiumSpade;
	public static Item iridiumSword;
	public static Item iridiumPickaxe;
	public static Item salt;

	public static Item ironTreetap;
	public static Item bronzeTreetap;
	public static Item leadTreetap;
	public static Item refinedIronTreetap;
	public static Item advancedAlloyTreetap;
	public static Item carbonTreetap;

	public static Item record_theSaltWaterRoom;
	public static Item record_MusicSpring;
	//---------------------------------------

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

	public static void init() {
		ironTreetap = new ItemGtiTreetap("IronTreetap", 32);
		bronzeTreetap = new ItemGtiTreetap("BronzeTreetap", 32);
		leadTreetap = new ItemGtiTreetap("LeadTreetap", 48);
		refinedIronTreetap = new ItemGtiTreetap("RefinedIronTreetap", 64);
		advancedAlloyTreetap = new ItemGtiTreetap("AdvancedAlloyTreetap", 64);
		carbonTreetap = new ItemGtiTreetap("CarbonTreetap", 128);
        record_theSaltWaterRoom = new ItemGtiRecord("record_TheSaltwaterRoom");
        record_MusicSpring = new ItemGtiRecord("record_MusicSpring");
		salt = new ItemGtiFood("Salt", 0, 10F, true);


		//MultiMetaItem registry
		ItemMultiDamage itemOre = new ItemMultiDamage("ItemOre") {
			@Override
			public String getInternalName(int meta) {
				switch (meta) {
					case 0: return "IngotIridium";
					case 1: return "DiamondPlate";
					case 2: return "DenseDiamondPlate";
					case 3: return "CrushedIridium";
					case 4: return "CleanedCrushedIridium";
					case 5: return "DustIridium";
					case 6: return "SmallDustIridium";
					default: return null;
				}
			}
		};
		ingotIridium = new ItemStack(itemOre, 1, 0);
		diamondPlate = new ItemStack(itemOre, 1, 1);
		denseDiamondPlate = new ItemStack(itemOre, 1, 2);
		crushedIridium = new ItemStack(itemOre, 1, 3);
		cleanedCrushedIridium = new ItemStack(itemOre, 1, 4);
		dustIridium = new ItemStack(itemOre, 1, 5);
		smallDustIridium = new ItemStack(itemOre, 1, 6);

		ItemMultiDamage itemDisc = new ItemMultiDamage("ItemDisc") {
			@Override
			public String getInternalName(int meta) {
				switch (meta) {
					case 0: return "Roller";
					case 1: return "HeatInsulationMaterial";
					case 2: return "SmallCompressedWaterHyacinth";
					case 3: return "AirBrakeUnit";
					case 4: return "CarbonTube";
					case 5: return "RedstoneModule";
					case 6: return "LazuliModule";
					case 7: return "ObsidianPlateGravityField";
					case 8: return "ElectronicCircuitControl";
					case 9: return "ElectronicCircuitCore";
					case 10: return "PulseElectronicCircuitControl";
					case 11: return "PulseElectronicCircuitCore";
					case 12: return "CyclotronParticleAccelerator";
					case 13: return "CalculateUnit";
					case 14: return "CalculateChunk";
					case 15: return "CalculateArray";
					case 16: return "FloatPointCalculationsRegion";
					case 17: return "ParallelSpaceConverter";
					case 18: return "ObsidianMechanicalFrame";
					case 19: return "ObsidianMechanicalCasing";
					case 20: return "CarbonCrystal";
					case 21: return "EnderCalculationCrystal";
					case 22: return "MillTeeth";
					case 23: return "MillWheel";
					case 24: return "HeatInsulationPlate";
					case 25: return "UUMatterCore";
					case 26: return "BambooCharcoal";
					default: return null;
				}
			}
		};
		roller = new ItemStack(itemDisc, 1, 0);
		heatInsulationMaterial = new ItemStack(itemDisc, 1, 1);
		smallCompressedWaterHyacinth = new ItemStack(itemDisc, 1, 2);
		airBrakeUnit = new ItemStack(itemDisc, 1, 3);
		carbonTube = new ItemStack(itemDisc, 1, 4);
		redstoneModule = new ItemStack(itemDisc, 1, 5);
		lazuliModule = new ItemStack(itemDisc, 1, 6);
		obsidianPlateGravityField = new ItemStack(itemDisc, 1, 7);
		electronicCircuitControl = new ItemStack(itemDisc, 1, 8);
		electronicCircuitCore = new ItemStack(itemDisc, 1, 9);
		pulseElectronicCircuitControl = new ItemStack(itemDisc, 1, 10);
		pulseElectronicCircuitCore = new ItemStack(itemDisc, 1, 11);
		cyclotronParticleAccelerator = new ItemStack(itemDisc, 1, 12);
		calculateUnit = new ItemStack(itemDisc, 1, 13);
		calculateChunk = new ItemStack(itemDisc, 1, 14);
		calculateArray = new ItemStack(itemDisc, 1, 15);
		floatPointCalculationsRegion = new ItemStack(itemDisc, 1, 16);
		parallelSpaceConverter = new ItemStack(itemDisc, 1, 17);
		obsidianMechanicalFrame = new ItemStack(itemDisc, 1, 18);
		obsidianMechanicalCasing = new ItemStack(itemDisc, 1, 19);
		carbonCrystal = new ItemStack(itemDisc, 1,20);
		enderCalculationCrystal = new ItemStack(itemDisc, 1, 21);
		millTeeth = new ItemStack(itemDisc, 1, 22);
		millWheel = new ItemStack(itemDisc, 1, 23);
		heatInsulationPlate = new ItemStack(itemDisc, 1, 24);
		uuMatterCore = new ItemStack(itemDisc, 1, 25);
		bambooCharcoal = new ItemStack(itemDisc, 1, 26);

        // special registry TODO: Better registry system
 
        diamondApple = new ItemFood(1005, 10F, false) {
            @Override
            protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player) {
                if (!world.isRemote) {
                    player.addPotionEffect(new PotionEffect(Potion.field_76434_w.id, 12000, 0));
                    player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 6000, 4));
                    player.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 4));
                    player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
                }
                super.onFoodEaten(itemStack, world, player);
            }

            @Override
            @SuppressWarnings("unchecked")
            public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
                list.add(I18n.format(diamondApple.getUnlocalizedName() + ".desc1"));
            }
        };
        diamondApple.setUnlocalizedName("gti.food.DiamondApple").setCreativeTab(Gti.creativeTabGti).setTextureName("gti:itemDiamondApple");

		packagedSalt = new ItemGti("PackagedSalt") {
			/**
			 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
			 */
			@Override
			public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
				if (GtiConfig.instance.isThrowableUran238()) {
					if (!entityPlayer.capabilities.isCreativeMode) {
						--itemStack.stackSize;
					}
					world.playSoundAtEntity(entityPlayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
					if (!world.isRemote) {
						world.spawnEntityInWorld(new EntityPackagedSalt(world, entityPlayer));
					}
				}

				return itemStack;
			}
		};

        iridiumPickaxe = ToolGti.registerPickaxe(GtiToolMaterial.iridium, "IridiumPickaxe");
        iridiumAxe = ToolGti.registerAxe(GtiToolMaterial.iridium, "IridiumAxe");
        iridiumSpade = ToolGti.registerSpade(GtiToolMaterial.iridium, "IridiumSpade");
        iridiumSword = ToolGti.registerSword(GtiToolMaterial.iridium, "IridiumSword");

        // TODO: Better registry system
        GameRegistry.registerItem(diamondApple, "DiamondApple");
        GameRegistry.registerFuelHandler(new GtiItems());
    }

	@Override
	public int getBurnTime(ItemStack fuel) {
		if (fuel.isItemEqual(bambooCharcoal)) {
			return 800;
		}
		if (fuel.isItemEqual(smallCompressedWaterHyacinth)) {
			return 400;
		}
		if (fuel.getItem().equals(
				Item.getItemFromBlock(GtiBlocks.waterHyacinth))) {
			return 100;
		}
		if (fuel.getItem().equals(
				Item.getItemFromBlock(GtiBlocks.compressedWaterHyacinth))) {
			return 800;
		}
		if (fuel.getItem().equals(
				Item.getItemFromBlock(GtiBlocks.dehydratedWaterHyacinthblock))) {
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
