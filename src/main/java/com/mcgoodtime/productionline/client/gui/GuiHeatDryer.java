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
//import com.mcgoodtime.productionline.common.PLUtil;
//        import com.mcgoodtime.productionline.common.core.ProductionLine;
//        import com.mcgoodtime.productionline.common.inventory.ContainerHeatDryer;
//        import com.mcgoodtime.productionline.common.tiles.TileHeatDryer;
//import net.minecraft.util.ResourceLocation;
///*
// * Created by suhao on 2015.7.10.
// */
//public class GuiHeatDryer extends GuiPL<ContainerHeatDryer> {
//
//    public GuiHeatDryer(ContainerHeatDryer container) {
//        super(container);
//    }
//
//    @Override
//    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
//        super.drawGuiContainerBackgroundLayer(f, x, y);
//        TileHeatDryer tile = this.container.getTileEntity();
//        int i1 = PLUtil.getGuiScaled(14, (float) tile.energy, tile.maxEnergy);
//        this.drawTexturedModalRect(this.x + 56, this.y + 36 + 14 - i1, 176, 14 - i1, 14, i1 + 1);
//        i1 = PLUtil.getGuiScaled(24, tile.progress, (float) tile.requireEnergy);
//        this.drawTexturedModalRect(this.x + 79, this.y + 34, 176, 14, i1 + 1, 16);
//    }
//
//    @Override
//    protected ResourceLocation getResource() {
//        return new ResourceLocation(ProductionLine.RESOURCE_DOMAIN, "textures/gui/GuiHeatDryer.png");
//    }
//}