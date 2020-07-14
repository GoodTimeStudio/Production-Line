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
package com.mcgoodtime.productionline.items;

import com.mcgoodtime.productionline.core.ProductionLine;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

import static com.mcgoodtime.productionline.core.ProductionLine.MOD_ID;

/**
 * Created by suhao on 2015.6.29.
 * @author suhao
 */
public class ItemPLRecord extends ItemRecord {

    private String name;

    public ItemPLRecord(String name, SoundEvent soundEvent) {
        super(name, soundEvent);
        this.name = name;
        this.setCreativeTab(ProductionLine.creativeTabPL);
        this.setUnlocalizedName(MOD_ID + "." + name);
        this.setRegistryName(new ResourceLocation(MOD_ID, name));
        ForgeRegistries.ITEMS.register(this);
    }

    @Override
    public String getRecordNameLocal() {
        return net.minecraft.util.text.translation.I18n.translateToLocal(this.getUnlocalizedName() + ".desc");
    }

    /*
    @Override
    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation(ProductionLine.RESOURCE_DOMAIN, this.name);
    }*/

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format(this.getUnlocalizedName() + "." + "desc1"));
        tooltip.add(I18n.format(this.getUnlocalizedName() + "." + "desc2"));
    }
}
