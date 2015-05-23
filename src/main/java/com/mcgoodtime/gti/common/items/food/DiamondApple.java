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
package com.mcgoodtime.gti.common.items.food;

import static com.mcgoodtime.gti.common.core.CreativeTabGti.creativeTabGti;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class DiamondApple extends ItemAppleGold {
    public DiamondApple() {
        super(1005, 10F, false);
        this.setUnlocalizedName("gti.food.DiamondApple");
        this.setCreativeTab(creativeTabGti);
        this.setTextureName("gti:itemDiamondApple");
    }

    @Override
    protected void onFoodEaten(ItemStack p_77849_1_, World p_77849_2_, EntityPlayer p_77849_3_)
    {
        if (!p_77849_2_.isRemote)
        {
            p_77849_3_.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2400, 0));
        }

        if (p_77849_1_.getItemDamage() > 0)
        {
            if (!p_77849_2_.isRemote)
            {
                p_77849_3_.addPotionEffect(new PotionEffect(Potion.regeneration.id, 6000, 4));
                p_77849_3_.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
                p_77849_3_.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
            }
        }
        else
        {
            super.onFoodEaten(p_77849_1_, p_77849_2_, p_77849_3_);
        }
    }
}
