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
import com.mcgoodtime.gti.common.items.tools.ItemGtiTreetap;
import com.mcgoodtime.gti.common.items.tools.IridiumPickaxe;

import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * The list of all those items in GoodTime-Industrial.
 */
public class GtiItems implements IFuelHandler {
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
    public static ItemPickaxe iridiumPickaxe;
    public static Item ironTreetap;

    public static void init() {
        diamondPlate = new ItemGti("DiamondPlate");
        denseDiamondPlate = new ItemGti("DenseDiamondPlate");
        crushedIridium = new ItemGti("CrushedIridium");
        cleanedCrushedIridium = new ItemGti("CleanedCrushedIridium");
        dustIridium = new ItemGti("DustIridium");
        smallDustIridium = new ItemGti("SmallDustIridium");
        ingotIridium = new ItemGti("IngotIridium");
        airBrakeUnit = new ItemGti("AirBrakeUnit");
        airBrakeCasing = new ItemGti("AirBrakeCasing");
        bambooCharcoal = new ItemGti("BambooCharcoal");
        ironTreetap = new ItemGtiTreetap("IronTreetap", 32);
        ironTreetap = new ItemGtiTreetap("BronzeTreetap", 32);
        ironTreetap = new ItemGtiTreetap("RefinedIronTreetap", 64);

        // special registy TODO: Better registry system
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
        };

        diamondApple = diamondApple
                .setUnlocalizedName("gti.food.DiamondApple")
                .setCreativeTab(Gti.creativeTabGti)
                .setTextureName("gti:itemDiamondApple");
        iridiumPickaxe = new IridiumPickaxe();

        // TODO: Better registry system
        GameRegistry.registerItem(diamondApple, "DiamondApple");
        GameRegistry.registerItem(iridiumPickaxe, "gti.iridiumPickaxe");

        GameRegistry.registerFuelHandler(new GtiItems());
    }

    @Override
    public int getBurnTime(ItemStack fuel) {
        if (fuel.getItem() == bambooCharcoal) {
            return 800;
        }
        return 0;
    }
}
