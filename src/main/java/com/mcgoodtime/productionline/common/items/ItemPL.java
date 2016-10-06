/*
 * This file is part of GoodTime-Industrial, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 Minecraft-GoodTime <http://mcgoodtime.com>
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
package com.mcgoodtime.productionline.common.items;

import com.mcgoodtime.productionline.common.core.ProductionLine;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

import static com.mcgoodtime.productionline.common.core.ProductionLine.*;

/**
 * Created by liach on 5/22/2015.
 *
 * @author liach
 */
public class ItemPL extends Item {

    private String itemName;

    public ItemPL(String name) {
        this.setUnlocalizedName(MOD_ID + "." + name);
        this.setRegistryName(ProductionLine.loc(name));
//        this.setTextureName(ProductionLine.RESOURCE_DOMAIN + ":" + "item" + name);

        this.itemName = name;
        this.setCreativeTab(creativeTabGti);
        GameRegistry.registerItem(this, name);
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List<String> list, boolean bool) {
        int i = 1;
        String unLocal = this.getUnlocalizedName() + ".desc" + i;

        while (I18n.hasKey(unLocal)) {
            list.add(I18n.format(unLocal));
            i++;
            unLocal = this.getUnlocalizedName() + ".desc" + i;
        }
    }

//    @Override
//    public void registerIcons(IIconRegister iconRegister) {
//        this.itemIcon = iconRegister.registerIcon(ProductionLine.RESOURCE_DOMAIN + ":" + this.getTextureFolder() + this.getIconName());
//    }

    public String getTextureFolder() {
        return "";
    }

    public String getIconName() {
        return "item" + itemName;
    }
}
