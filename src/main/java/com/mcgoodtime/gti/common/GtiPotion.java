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
package com.mcgoodtime.gti.common;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.core.GtiConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by suhao on 2015.10.23.0023.
 *
 * @author suhao
 */
public class GtiPotion extends Potion {
    public static GtiPotion salty;

    private static int index = 0;
    private List<ItemStack> curativeItems = new ArrayList<ItemStack>();

    protected GtiPotion(boolean deBuff, int liquidColor, ItemStack... curativeItems) {
        super(100 + index, deBuff, liquidColor);
        this.curativeItems = Arrays.asList(curativeItems);
        index++;
    }

    /**
     * @note http://www.minecraftforum.net/forums/mapping-and-modding/mapping-and-modding-tutorials/2363067-forge-1-7-10-redds-advanced-tutorials-make-your
     */
    public static void initPotion() {
        if (Potion.potionTypes.length < 256) {
            Potion[] potionTypes;
            for (Field f : Potion.class.getDeclaredFields()) {
                f.setAccessible(true);
                try {
                    if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
                        Field modfield = Field.class.getDeclaredField("modifiers");
                        modfield.setAccessible(true);
                        modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

                        potionTypes = (Potion[])f.get(null);
                        final Potion[] newPotionTypes = new Potion[256];
                        System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                        f.set(null, newPotionTypes);
                    }
                }
                catch (Exception e) {
                    GtiConfig.gtiLogger.log(Level.ERROR, "Severe error, please report this to the mod author:");
                    GtiConfig.gtiLogger.log(Level.ERROR, e);
                }
            }
        }

        salty = new GtiPotion(true, 0xFFFFFFF, new ItemStack(Items.water_bucket), new ItemStack(Items.milk_bucket));
        salty.setPotionName("gti.potion.Salty");
    }

    /**
     * If the standard PotionEffect text (name and duration) should be drawn when this potion is active.
     *
     * @param effect the active PotionEffect
     * @return true to draw the standard text
     */
    @Override
    public boolean shouldRenderInvText(PotionEffect effect) {
        return false;
    }

    /**
     * Called to draw the this Potion onto the player's inventory when it's active.
     * This can be used to e.g. render Potion icons from your own texture.
     *
     * @param x      the x coordinate
     * @param y      the y coordinate
     * @param effect the active PotionEffect
     * @param mc     the Minecraft instance, for convenience
     */
    @Override
    public void renderInventoryEffect(int x, int y, PotionEffect effect, Minecraft mc) {
        mc.getTextureManager().bindTexture(new ResourceLocation(Gti.RESOURCE_DOMAIN, "textures/gui/potion.png"));
        mc.currentScreen.drawTexturedModalRect(x + 6, y + 7, 0, 0, 18, 18);
        if (effect instanceof GtiEffect) {
            String s = I18n.format(effect.getEffectName()) + " " + ((GtiEffect) effect).level + 1;
            mc.currentScreen.drawString(mc.fontRenderer, s, x + 10 + 18, y + 6, 16777215);
        }

    }

    public boolean isReady(GtiEffect effect) {
        if (this.id == salty.id) {
            if (effect.level > 14) {
                effect.level = 0;
            }

            //20ticks = 1s
            if (effect.timer < 20) {
                ++effect.timer;
                return false;
            }
            if (effect.timer == 20) {
                effect.timer = 0;
                --effect.level;
                effect.hp = effect.level / 3;
                return true;
            }
        }
        return false;
    }

    public void performEffect(EntityLivingBase entityLivingBase, float hp) {
        if (this.id == salty.id) {
            entityLivingBase.attackEntityFrom(GtiDamageSource.salty, hp);
        }
    }

    public void applyPotion(EntityLivingBase entityLivingBase, int durationTime, int level) {
        GtiEffect effect = new GtiEffect(this.id, durationTime, level);
        effect.setCurativeItems(this.curativeItems);
        entityLivingBase.addPotionEffect(effect);
    }

    public static class GtiEffect extends PotionEffect {
        private float hp = 1F;
        private int timer;
        private int level = this.getAmplifier();
        private int id = this.getPotionID();

        public GtiEffect(int id, int durationTime, int level) {
            super(id, durationTime, level);
            if (id == salty.id) {
                timer = 20;
            }
        }

        @Override
        public boolean onUpdate(EntityLivingBase entityLivingBase) {
            if (this.id == salty.id) {
                if (this.level > 0) {
                    if (((GtiPotion)Potion.potionTypes[this.id]).isReady(this)) {
                        ((GtiPotion) Potion.potionTypes[this.id]).performEffect(entityLivingBase, hp);
                    }
                    return true;
                }
            }

            return false;
        }
    }
}
