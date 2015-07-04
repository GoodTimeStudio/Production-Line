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
package com.mcgoodtime.gti.client.gui;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.inventory.ContainerCarbonizeFurnace;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.IC2;
import ic2.core.block.machine.tileentity.TileEntityIronFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import static com.mcgoodtime.gti.common.core.Gti.RESOURCE_DOMAIN;

/*
 * Created by suhao on 2015.7.4.
 */

@SideOnly(Side.CLIENT)
public class GuiCarbonizeFurnace extends GuiContainer {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(RESOURCE_DOMAIN + ":" + "textures/client/gui/GuiCarbonizeFurnace.png");
    private ContainerCarbonizeFurnace container;
    private String name;

    public GuiCarbonizeFurnace(ContainerCarbonizeFurnace container) {
        super(container);
        this.container = container;
        this.name = StatCollector.translateToLocal(Gti.GUI_PREFIX + "CarbonizeFurnace");
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(this.name, (this.xSize - this.fontRendererObj.getStringWidth(this.name)) / 2, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(BACKGROUND);
        int j = (this.width - this.xSize) / 2;
        int k = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(j, k, 0, 0, this.xSize, this.ySize);
        int i1;
        if(((TileCarbonizeFurnace)this.container.base).fuel > 0) {
            i1 = ((TileCarbonizeFurnace)this.container.base).gaugeFuelScaled(12);
            this.drawTexturedModalRect(j + 56, k + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
        }

        i1 = ((TileCarbonizeFurnace)this.container.base).gaugeProgressScaled(24);
        this.drawTexturedModalRect(j + 79, k + 34, 176, 14, i1 + 1, 16);
    }
}
