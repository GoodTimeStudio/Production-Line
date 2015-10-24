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

import com.mcgoodtime.gti.common.core.GtiConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import org.apache.logging.log4j.Level;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by suhao on 2015.10.23.0023.
 *
 * @author suhao
 */
public class GtiPotion extends Potion {
    public static GtiPotion Salty;

    private List<ItemStack> curativeItems = new ArrayList<ItemStack>();

    protected GtiPotion(int id, boolean deBuff, int liquidColor, ItemStack... curativeItems) {
        super(id, deBuff, liquidColor);
        this.curativeItems = Arrays.asList(curativeItems);
    }

    public static void initPotion() {
        Potion[] potionTypes = null;
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

        Salty = new GtiPotion(100, true, 0xFFFFFFF, new ItemStack(Items.water_bucket), new ItemStack(Items.milk_bucket));
        Salty.setPotionName("gti.potion.Salty");
        Salty.setIconIndex(0, 0);
    }

    @Override
    public boolean isReady(int duration, int level) {
        if (this.id == Salty.id) {
            int k = 150 >> level;
            return k <= 0 || duration % k == 0;
        }
        return true;
    }

    @Override
    public void performEffect(EntityLivingBase entityLivingBase, int level) {
        if (this.id == Salty.id) {
            entityLivingBase.attackEntityFrom(GtiDamageSource.salty, 1.0F);
        }
    }

    public void applyPotion(EntityLivingBase entityLivingBase, int durationTime, int level) {
        PotionEffect effect = new PotionEffect(this.id, durationTime, level);
        effect.setCurativeItems(this.curativeItems);
        entityLivingBase.addPotionEffect(effect);
    }
}
