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
package com.mcgoodtime.productionline.client.gui;

import com.mcgoodtime.productionline.PLUtil;
import com.mcgoodtime.productionline.core.ProductionLine;
import com.mcgoodtime.productionline.inventory.ContainerFluidKineticGenerator;
import com.mcgoodtime.productionline.tiles.TileFluidKineticGenerator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Collections;

import static com.mcgoodtime.productionline.core.ProductionLine.*;

/**
 * The Gui.
 *
 * @author liach
 *         improve by BestOwl
 */
@SideOnly(Side.CLIENT)
public class GuiFluidKineticGenerator extends GuiPL<ContainerFluidKineticGenerator> {

    public GuiFluidKineticGenerator(ContainerFluidKineticGenerator container) {
        super(container);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        TileFluidKineticGenerator tile = this.container.getTileEntity();

        if (tile.active) {
            this.mc.renderEngine.bindTexture(this.getResource());
            this.drawTexturedModalRect(this.x + 51, this.y + 55, 176, 0, 14, 14);
        }

        if (tile.fluidTank.getFluidAmount() > 0) {
            ResourceLocation fluidIconIdent = this.container.tile.fluidTank.getFluid().getFluid().getStill();
            TextureAtlasSprite icon = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(fluidIconIdent.toString());
            if (icon != null) {
                this.mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
                int fy = PLUtil.getGuiScaled(47, tile.fluidTank.getFluidAmount(), tile.fluidTank.getCapacity());
                this.drawTexturedModalRect(this.x + 74, this.y + 24 + 47 - fy, icon, 12, fy);
                this.mc.renderEngine.bindTexture(this.getResource());
                this.drawTexturedModalRect(this.x + 74, this.y + 24, 176, 72, 12, 47);
            }
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        super.drawGuiContainerForegroundLayer(x, y);
        if (this.container.tile.fluidTank.getFluidAmount() > 0) {
            String tooltip = this.container.tile.fluidTank.getFluid().getLocalizedName() + ": " + this.container.tile.fluidTank.getFluidAmount() + "mB";
            drawTooltip(x - this.guiLeft, y - this.guiTop, Collections.singletonList(tooltip));
        }

        String output = I18n.format(GUI_PREFIX + "FluidKineticGenerator.output",
                this.container.getTileEntity().maxrequestkineticenergyTick(
                        this.container.getTileEntity().facing));
        this.drawString(this.fontRendererObj, output, 96, 33, 2157374);

        String max_output = I18n.format(GUI_PREFIX + "FluidKineticGenerator.max-output",
                this.container.getTileEntity().kuOutput);
        this.drawString(this.fontRendererObj, max_output, 96, 52, 2157374);
    }

    @Override
    protected ResourceLocation getResource() {
        return new ResourceLocation(ProductionLine.RESOURCE_DOMAIN, "textures/gui/GuiFluidKineticGenerator.png");
    }
}
