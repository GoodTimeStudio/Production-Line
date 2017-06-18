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

import com.mcgoodtime.productionline.core.ProductionLine;
import com.mcgoodtime.productionline.inventory.ContainerPL;
import ic2.core.IC2;
import ic2.core.upgrade.IUpgradableBlock;
import ic2.core.upgrade.IUpgradeItem;
import ic2.core.upgrade.UpgradableProperty;
import ic2.core.upgrade.UpgradeRegistry;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorldNameable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by BestOwl on 2015.10.31.0031.
 *
 * ProductionLine base gui.
 * @author BestOwl
 */
@SideOnly(Side.CLIENT)
public abstract class GuiPL<T extends ContainerPL> extends GuiContainer {
    public T container;
    public String name;

    protected int x;
    protected int y;

    public GuiPL(T container) {
        super(container);
        this.container = container;
        this.name = ((IWorldNameable) container.tile).getName();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        String displayName = I18n.format(ProductionLine.GUI_PREFIX + this.name);
        this.fontRendererObj.drawString(displayName, (this.xSize - this.fontRendererObj.getStringWidth(displayName)) / 2, 6, 4210752);
        if (this.container.tile instanceof IUpgradableBlock) {
            handleUpgradeTooltip(x, y);
        }
    }

    private void handleUpgradeTooltip(int mouseX, int mouseY) {
        if(mouseX >= 0 && mouseX <= 12 && mouseY >= 0 && mouseY <= 12) {
            Stream.Builder<String> builder = Stream.<String>builder().add(I18n.format("ic2.generic.text.upgrade"));
            getCompatibleUpgrades((IUpgradableBlock)this.container.tile).stream().map(ItemStack::getDisplayName).forEach(builder);
            this.drawTooltip(mouseX, mouseY, builder.build().collect(Collectors.toList()));
        }
    }

    private static List<ItemStack> getCompatibleUpgrades(IUpgradableBlock block) {
        Set<UpgradableProperty> properties = block.getUpgradableProperties();
        return StreamSupport
                .stream(UpgradeRegistry.getUpgrades().spliterator(), false)
                .filter(stack -> ((IUpgradeItem) stack.getItem()).isSuitableFor(stack, properties))
                .collect(Collectors.toList());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(getResource());
        this.x = (this.width - this.xSize) / 2;
        this.y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.x, this.y, 0, 0, this.xSize, this.ySize);

        if (this.container.tile instanceof IUpgradableBlock) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation(IC2.RESOURCE_DOMAIN, "textures/gui/infobutton.png"));
            this.drawTexturedModalRect(this.x + 3, this.y + 3, 0, 0, 10, 10);
            this.mc.getTextureManager().bindTexture(this.getResource());
        }
    }

    public void drawTooltip(int x, int y, List<String> text) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawHoveringText(text, x, y);
        GlStateManager.disableLighting();
    }

    protected abstract ResourceLocation getResource();
}
