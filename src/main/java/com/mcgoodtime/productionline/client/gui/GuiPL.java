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
//package com.mcgoodtime.productionline.client.gui;
//
//import com.mcgoodtime.productionline.common.core.ProductionLine;
//import com.mcgoodtime.productionline.common.inventory.ContainerPL;
//import ic2.core.IC2;
//import ic2.core.upgrade.IUpgradableBlock;
//import ic2.core.util.GuiTooltipHelper;
//import net.minecraft.client.gui.inventory.GuiContainer;
//import net.minecraft.util.ResourceLocation;
//import net.minecraft.util.StatCollector;
//import org.lwjgl.opengl.GL11;
//
///**
// * Created by BestOwl on 2015.10.31.0031.
// *
// * ProductionLine base gui.
// * @author BestOwl
// */
//public abstract class GuiPL<T extends ContainerPL> extends GuiContainer {
//    public T container;
//    public String name;
//
//    protected int x;
//    protected int y;
//
//    public GuiPL(T container) {
//        super(container);
//        this.container = container;
//        this.name = container.tile.getInventoryName();
//    }
//
//
//
//    @Override
//    protected void drawGuiContainerForegroundLayer(int x, int y) {
//        this.fontRendererObj.drawString(StatCollector.translateToLocal(ProductionLine.GUI_PREFIX + this.name), (this.xSize - this.fontRendererObj.getStringWidth(this.name)) / 2, 6, 4210752);
//        if(this.container.tile instanceof IUpgradableBlock) {
//            GuiTooltipHelper.drawUpgradeslotTooltip(x - this.guiLeft, y - this.guiTop, 0, 0, 12, 12, (IUpgradableBlock) this.container.tile, 25, 0);
//        }
//    }
//
//    @Override
//    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
//        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//        this.mc.getTextureManager().bindTexture(getResource());
//        this.x = (this.width - this.xSize) / 2;
//        this.y = (this.height - this.ySize) / 2;
//        this.drawTexturedModalRect(this.x, this.y, 0, 0, this.xSize, this.ySize);
//
//        if (this.container.tile instanceof IUpgradableBlock) {
//            this.mc.getTextureManager().bindTexture(new ResourceLocation(IC2.textureDomain, "textures/gui/infobutton.png"));
//            this.drawTexturedModalRect(this.x + 3, this.y + 3, 0, 0, 10, 10);
//            this.mc.getTextureManager().bindTexture(this.getResource());
//        }
//    }
//
//    protected abstract ResourceLocation getResource();
//}
