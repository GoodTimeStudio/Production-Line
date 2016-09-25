///*
// * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
// *
// * Copyright (c) 2015 GoodTime Studio <https://github.com/GoodTimeStudio>
// * Copyright (c) contributors
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in
// * all copies or substantial portions of the Software.
// *
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
// * THE SOFTWARE.
// */
//package com.mcgoodtime.productionline.common;
//
//import com.mcgoodtime.productionline.common.core.ProductionLine;
//import com.mcgoodtime.productionline.common.core.PLConfig;
//import net.minecraft.client.Minecraft;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.init.Items;
//import net.minecraft.item.ItemStack;
//import net.minecraft.potion.Potion;
//import net.minecraft.potion.PotionEffect;
//import net.minecraft.util.ResourceLocation;
//import org.apache.logging.log4j.Level;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by suhao on 2015.10.23.0023.
// *
// * @author suhao
// */
//public class PLPotion extends Potion {
//    public static PLPotion salty;
//
//    private List<ItemStack> curativeItems = new ArrayList<ItemStack>();
//
//    protected PLPotion(boolean deBuff, int liquidColor, ItemStack... curativeItems) {
//        super(deBuff, liquidColor);
//        this.curativeItems = Arrays.asList(curativeItems);
//    }
//
//    /**
//     * @note http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/2363067-forge-1-7-10-redds-advanced-tutorials-make-your
//     */
//    public static void initPotion() {
//        salty = new PLPotion(true, 0xFFFFFFF, new ItemStack(Items.WATER_BUCKET), new ItemStack(Items.MILK_BUCKET));
//        salty.setPotionName("productionline.potion.Salty");
//    }
//
//    /**
//     * Called to draw the this Potion onto the player's inventory when it's active.
//     * This can be used to e.g. render Potion icons from your own texture.
//     *
//     * @param x      the x coordinate
//     * @param y      the y coordinate
//     * @param effect the active PotionEffect
//     * @param mc     the Minecraft instance, for convenience
//     */
//    @Override
//    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
//        mc.getTextureManager().bindTexture(new ResourceLocation(ProductionLine.RESOURCE_DOMAIN, "textures/gui/misc.png"));
//        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 0, 0, 18, 18);
//    }
//
//    public boolean isReady(PLEffect effect) {
//        if (this.id == salty.id) {
//            if (effect.level > 15) {
//                effect.level = 15;
//            }
//
//            //20ticks = 1s
//            if (effect.timer < 20) {
//                ++effect.timer;
//                return false;
//            }
//            if (effect.timer == 20) {
//                effect.timer = 0;
//                --effect.level;
//                effect.hp = (effect.level / 3) + 1;
//
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public void performEffect(EntityLivingBase entityLivingBase, float hp) {
//        if (this.id == salty.id) {
//            entityLivingBase.attackEntityFrom(PLDamageSource.salty, hp);
//        }
//    }
//
//    public void applyPotion(EntityLivingBase entityLivingBase, int durationTime, int level) {
//        if (entityLivingBase.isPotionActive(salty)) {
//            level += ((PLEffect) entityLivingBase.getActivePotionEffect(salty)).level;
//        }
//
//        PLEffect effect = new PLEffect(this.id, durationTime, level);
//
//        if (this.id == salty.id) {
//            effect.setPotionDurationMax(true);
//        }
//
//        effect.setCurativeItems(this.curativeItems);
//        entityLivingBase.addPotionEffect(effect);
//    }
//}
