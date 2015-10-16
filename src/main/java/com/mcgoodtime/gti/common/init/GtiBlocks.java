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

import com.mcgoodtime.gti.common.blocks.*;
import com.mcgoodtime.gti.common.core.GtiConfig;
import com.mcgoodtime.gti.common.items.ItemBlackSesame;
import com.mcgoodtime.gti.common.items.ItemCelery;
import com.mcgoodtime.gti.common.items.ItemRedBean;
import com.mcgoodtime.gti.common.items.ItemStickyRice;
import com.mcgoodtime.gti.common.items.ItemWaterHyacinth;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.Level;

/**
 * Gti blocks.
 *
 * @author liach
 */
public class GtiBlocks {
	public static Block celery;
	public static Block stickyRice;
	public static Block blackSesame;
	public static Block redBean;
	public static Block dehydratedWaterHyacinthblock;
	public static Block compressedWaterHyacinth;
    public static Block waterHyacinth;
    public static BlockContainer genGasKU;
    public static Block oreIridium;
    public static BlockContainerGti carbonizeFurnace;
    public static Block litCarbonizeFurnace;
    public static BlockContainer evsu;
    public static BlockContainer heatDryer;

    public static void init() {
        oreIridium = new BlockGti(Material.rock, "oreIridium", 10, 20, "pickaxe", 3);
        compressedWaterHyacinth = new BlockGti(Material.rock, "CompressedWaterHyacinth", 0.5F, 0.3F, null, 0);
        dehydratedWaterHyacinthblock=new BlockGti(Material.rock, "DehydratedWaterHyacinthBlock", 1.0F, 0.3F, null, 0);
        carbonizeFurnace = new BlockCarbonizeFurnace(Material.iron, "CarbonizeFurnace");

        // special registry TODO: Better registry system
        celery = new BlockCelery();
        stickyRice = new BlockStickyRice();
        blackSesame = new BlockBlackSesame();
        redBean = new BlockRedBean(); 
        waterHyacinth = new BlockWaterHyacinth();
        genGasKU = new GenGasKu();
        evsu = new BlockEVSU();
        
        GameRegistry.registerBlock(celery, ItemCelery.class, "Celery");
        GameRegistry.registerBlock(stickyRice, ItemStickyRice.class, "StickyRice");
        GameRegistry.registerBlock(blackSesame, ItemBlackSesame.class, "BlackSesame");
        GameRegistry.registerBlock(redBean, ItemRedBean.class, "RedBean" );
        GameRegistry.registerBlock(waterHyacinth, ItemWaterHyacinth.class, "WaterHyacinth");
        GameRegistry.registerBlock(genGasKU, "GenGasKu");
        GameRegistry.registerBlock(evsu, "EVSU");
        
        GtiConfig.gtiLogger.log(Level.INFO, "Celery" + Integer.toString(Block.getIdFromBlock(celery)));
        GtiConfig.gtiLogger.log(Level.INFO, "stickyRice" + Integer.toString(Block.getIdFromBlock(stickyRice)));
        GtiConfig.gtiLogger.log(Level.INFO, "blackSesame" + Integer.toString(Block.getIdFromBlock(blackSesame)));
        GtiConfig.gtiLogger.log(Level.INFO, "redbean" + Integer.toString(Block.getIdFromBlock(redBean)));
        GtiConfig.gtiLogger.log(Level.INFO, "waterhyacinth" + Integer.toString(Block.getIdFromBlock(waterHyacinth)));
        GtiConfig.gtiLogger.log(Level.INFO, "gengasku" + Integer.toString(Block.getIdFromBlock(genGasKU)));
        GtiConfig.gtiLogger.log(Level.INFO, "carbonizefurnace" + Integer.toString(Block.getIdFromBlock(carbonizeFurnace)));
    }
}
