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
import com.mcgoodtime.gti.common.items.ItemGti;
import com.mcgoodtime.gti.common.items.ItemGtiFood;
import com.mcgoodtime.gti.common.items.ItemGtiRecord;
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
	public static Item roller;
	public static Item heatInsulationPlate;
	public static Item heatInsulationMaterial;
	public static Item smallCompressedWaterHyacinth;
	public static Item crushedIridium;
	public static Item cleanedCrushedIridium;
	public static Item dustIridium;
	public static Item smallDustIridium;
	public static Item ingotIridium;
	public static Item denseDiamondPlate;
	public static Item diamondPlate;
	public static Item airBrakeUnit;
	public static Item airBrakeCasing;
	public static Item bambooCharcoal;
	public static Item diamondApple;
	public static Item iridiumPickaxe;
	public static Item ironTreetap;
	public static Item bronzeTreetap;
	public static Item leadTreetap;
	public static Item refinedIronTreetap;
	public static Item advancedAlloyTreetap;
	public static Item carbonTreetap;
	public static Item record_theSaltWaterRoom;
	public static Item salt;
	public static Item packagedSalt;
	public static Item carbonTube;
	public static Item record_MusicSpring;
	public static Item iridiumAxe;
	public static Item iridiumSpade;
	public static Item iridiumSword;
	public static Item redstoneModule;
	public static Item lazuliModule;
	public static Item obsidianPlateGravityField;
	public static Item electronicCircuitControl;
	public static Item electronicCircuitCore;
	public static Item pulseElectronicCircuitControl;
	public static Item pulseElectronicCircuitCore;
	public static Item cyclotronParticleAccelerator;
	public static Item calculateUnit;
	public static Item calculateChunk;
	public static Item calculateArray;
	public static Item floatPointCalculationsRegion;
	public static Item parallelSpaceConverter;
	public static Item uuMatterCore;
	public static Item obsidianMechanicalFrame;
	public static Item obsidianMechanicalCasing;
	public static Item carbonCrystal;
	public static Item enderCalculationCrystal;
	public static Item millTeeth;
	public static Item millWheel;

	// Food
	
	
	//tools
	public static Item rollingPin;
	

	// Ingredients
	public static Item saltyYolk;
	public static Item saltyEgg;
	public static Item celery;
	public static Item blackSesame;
	public static Item wheatFlour;

	// Processed ingredients
	public static Item dough;
	public static Item stickyRiceFlour;
	public static Item stickyRiceBalls;
	public static Item redBeanPaste;
	public static Item dumplingSkin;
	public static Item bread;
	public static Item thinSkinned;
	public static Item pumpkinBlock;

	// Dumplings
	public static Item carrotDumplings;
	public static Item celeryDumplings;
	public static Item beefDumplings;
	public static Item porkDumplings;
	public static Item beefAndCeleryDumplings;
	public static Item beefAndCarrotDumplings;
	public static Item porkAndCeleryDumplings;
	public static Item porkAndCarrotDumplings;
	public static Item aBowlOfCarrotDumplings;
	public static Item aBowlOfCeleryDumplings;
	public static Item aBowlOfBeefDumplings;
	public static Item aBowlOfPorkDumplings;
	public static Item aBowlOfBeefAndCeleryDumplings;
	public static Item aBowlOfBeefAndCarrotDumplings;
	public static Item aBowlOfPorkAndCeleryDumplings;
	public static Item aBowlOfPorkAndCarrotDumplings;

	// Fried dumplings
	public static Item beefFriedDumplings;
	public static Item porkFriedDumplings;
	public static Item carrotAndPorkFriedDumplings;
	public static Item celeryAndPorkFriedDumplings;
	public static Item carrotAndBeefFriedDumplings;
	public static Item celeryAndBeefFriedDumplings;

	//Sticky Rice balls
	public static Item redBeanPasteStickyRiceBalls;
	public static Item whiteSesameStickyRiceBalls;
	public static Item blackSesameStickyRiceBalls;
	public static Item sugerStickyRiceBalls;

	// Flapjack
	public static Item whiteFlapjack;
	public static Item sweetFlapjack;
	public static Item saltyFlapjack;
	public static Item doubleFlapjack;
	public static Item doubleWhiteFlapjack;
	public static Item doubleSugerFlapjack;
	public static Item doubleSaltyFlapjack;

	// Chinese hamburger
	public static Item porkChineseHamburger;
	public static Item beefChineseHamburger;

	// Wonton
	public static Item beefWonton;
	public static Item porkWonton;
	public static Item aBowlOfBeefWonton;
	public static Item aBowlOfPorkWonton;

	// Moon cake
	public static Item pumpkinMoonCake;
	public static Item saltyYolkMoonCake;

	public static void init() {
		
    	roller = new ItemGti("Roller", true);
    	heatInsulationPlate = new ItemGti("HeatInsulationPlate");
        heatInsulationMaterial = new ItemGti("HeatInsulationMaterial");
    	smallCompressedWaterHyacinth = new ItemGti("smallCompressedWaterHyacinth");
        diamondPlate = new ItemGti("DiamondPlate", true);
        denseDiamondPlate = new ItemGti("DenseDiamondPlate", true);
        crushedIridium = new ItemGti("CrushedIridium");
        cleanedCrushedIridium = new ItemGti("CleanedCrushedIridium");
        dustIridium = new ItemGti("DustIridium");
        smallDustIridium = new ItemGti("SmallDustIridium");
        ingotIridium = new ItemGti("IngotIridium");
        airBrakeUnit = new ItemGti("AirBrakeUnit");
        airBrakeCasing = new ItemGti("AirBrakeCasing");
        bambooCharcoal = new ItemGti("BambooCharcoal", true);
        ironTreetap = new ItemGtiTreetap("IronTreetap", 32);
        bronzeTreetap = new ItemGtiTreetap("BronzeTreetap", 32);
        leadTreetap = new ItemGtiTreetap("LeadTreetap", 48);
        refinedIronTreetap = new ItemGtiTreetap("RefinedIronTreetap", 64);
        advancedAlloyTreetap = new ItemGtiTreetap("AdvancedAlloyTreetap", 64);
        carbonTreetap = new ItemGtiTreetap("CarbonTreetap", 128);
        record_theSaltWaterRoom = new ItemGtiRecord("record_TheSaltwaterRoom");
        salt = new ItemGti("Salt");
        packagedSalt = new ItemGti("PackagedSalt");
        carbonTube = new ItemGti("CarbonTube", true);
        record_MusicSpring = new ItemGtiRecord("record_MusicSpring");
        redstoneModule = new ItemGti("RedstoneModule");
        lazuliModule = new ItemGti("LazuliModule");
        obsidianPlateGravityField = new ItemGti("ObsidianPlateGravityField");
        electronicCircuitControl = new ItemGti("ElectronicCircuitControl");
        electronicCircuitCore = new ItemGti("ElectronicCircuitCore");
        pulseElectronicCircuitControl = new ItemGti("PulseElectronicCircuitControl");
        pulseElectronicCircuitCore = new ItemGti("PulseElectronicCircuitCore");
        cyclotronParticleAccelerator = new ItemGti("CyclotronParticleAccelerator");
        calculateUnit = new ItemGti("CalculateUnit");
        calculateChunk = new ItemGti("CalculateChunk");
        calculateArray = new ItemGti("CalculateArray");
        floatPointCalculationsRegion = new ItemGti("FloatPointCalculationsRegion");
        parallelSpaceConverter = new ItemGti("ParallelSpaceConverter");
        uuMatterCore = new ItemGti("UUMatterCore");
        obsidianMechanicalFrame = new ItemGti("ObsidianMechanicalFrame");
        obsidianMechanicalCasing = new ItemGti("ObsidianMechanicalCasing");
        carbonCrystal = new ItemGti("CarbonCrystal");
        enderCalculationCrystal = new ItemGti("EnderCalculationCrystal");
        millTeeth = new ItemGti("MillTeeth");
        millWheel = new ItemGti("MillWheel");
        
        //Food registry
        rollingPin = new ItemGti("RollingPin");
        saltyYolk = new ItemGtiFood("SaltyYolk");
        saltyEgg = new ItemGtiFood("SaltyEgg");
        celery = new ItemGtiFood("Celery");
        blackSesame = new ItemGtiFood("BlackSesame");
        wheatFlour = new ItemGtiFood("WheatFlour");
        dough = new ItemGtiFood("Dough");
        stickyRiceFlour = new ItemGtiFood("StickyRiceFlour");
        stickyRiceBalls = new ItemGtiFood("StickyRiceBalls");
        redBeanPaste = new ItemGtiFood("RedBeanPaste");
        dumplingSkin = new ItemGtiFood("DumplingSkin");
        bread = new ItemGtiFood("Bread");
        thinSkinned = new ItemGtiFood("ThinSkinned");
        pumpkinBlock = new ItemGtiFood("PumpkinBlock");
        carrotDumplings = new ItemGtiFood("CarrotDumplings");
        celeryDumplings = new ItemGtiFood("CeleryDumplings");
        beefDumplings = new ItemGtiFood("BeefDumplings");
        porkDumplings = new ItemGtiFood("PorkDumplings");
        beefAndCeleryDumplings = new ItemGtiFood("BeefAndCeleryDumplings");
        beefAndCarrotDumplings = new ItemGtiFood("BeefAndCarrotDumplings");
        porkAndCeleryDumplings = new ItemGtiFood("PorkAndCeleryDumplings");
        porkAndCarrotDumplings = new ItemGtiFood("PorkAndCarrotDumplings");
        aBowlOfCarrotDumplings = new ItemGtiFood("ABowlOfCarrotDumplings");
        aBowlOfCeleryDumplings = new ItemGtiFood("ABowlOfCeleryDumplings");
        aBowlOfBeefDumplings = new ItemGtiFood("ABowlOfBeefDumplings");
        aBowlOfPorkDumplings = new ItemGtiFood("ABowlOfPorkDumplings");
        aBowlOfBeefAndCeleryDumplings = new ItemGtiFood("ABowlOfBeefAndCeleryDumplings");
        aBowlOfBeefAndCarrotDumplings = new ItemGtiFood("ABowlOfBeefAndCarrotDumplings");
        aBowlOfPorkAndCeleryDumplings = new ItemGtiFood("ABowlOfPorkAndCeleryDumplings");
        aBowlOfPorkAndCarrotDumplings = new ItemGtiFood("ABowlOfPorkAndCarrotDumplings");
        beefFriedDumplings = new ItemGtiFood("BeefFriedDumplings");
        porkFriedDumplings = new ItemGtiFood("PorkFriedDumplings");
        carrotAndPorkFriedDumplings = new ItemGtiFood("CarrotAndPorkFriedDumplings");
        celeryAndPorkFriedDumplings = new ItemGtiFood("CeleryAndPorkFriedDumplings");
        carrotAndBeefFriedDumplings = new ItemGtiFood("CarrotAndBeefFriedDumplings");
        celeryAndBeefFriedDumplings = new ItemGtiFood("CeleryAndBeefFriedDumplings");
        redBeanPasteStickyRiceBalls = new ItemGtiFood("RedBeanPasteStickyRiceBalls");
        whiteSesameStickyRiceBalls = new ItemGtiFood("WriteSesameStickyRiceBalls");
        blackSesameStickyRiceBalls = new ItemGtiFood("BlackSesameStickyRiceBalls");
        sugerStickyRiceBalls = new ItemGtiFood("SugerStickyRiceBalls");
        whiteFlapjack = new ItemGtiFood("WhiteFlapjack");
        sweetFlapjack = new ItemGtiFood("SweetFlapjack");
        saltyFlapjack = new ItemGtiFood("SaltyFlapjack");
        doubleFlapjack = new ItemGtiFood("DoubleFlapjack");
        doubleWhiteFlapjack = new ItemGtiFood("DoubleWhiteFlapjack");
        doubleSugerFlapjack = new ItemGtiFood("DoubleSugerFlapjack");
        doubleSaltyFlapjack = new ItemGtiFood("DoubleSaltyFlapjack");
        porkChineseHamburger = new ItemGtiFood("PorkChineseHamburger");
        beefChineseHamburger = new ItemGtiFood("BeefChineseHamburger");
        beefWonton = new ItemGtiFood("BeefWonton");
        porkWonton = new ItemGtiFood("PorkWonton");
        aBowlOfBeefWonton = new ItemGtiFood("ABowlOfBeefWonton");
        aBowlOfPorkWonton = new ItemGtiFood("ABowlOfPorkWonton");
        pumpkinMoonCake = new ItemGtiFood("PumpkinMoonCake");
        saltyYolkMoonCake = new ItemGtiFood("SaltyYolkMoonCake");
        
        
        
        

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

        diamondApple = diamondApple
                .setUnlocalizedName("gti.food.DiamondApple")
                .setCreativeTab(Gti.creativeTabGti)
                .setTextureName("gti:itemDiamondApple");
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
		if (fuel.getItem() == bambooCharcoal) {
			return 800;
		}
		if (fuel.getItem() == smallCompressedWaterHyacinth) {
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
}
