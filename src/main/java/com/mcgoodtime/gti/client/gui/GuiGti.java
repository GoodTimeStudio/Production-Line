package com.mcgoodtime.gti.client.gui;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.inventory.ContainerGti;
import ic2.core.IC2;
import ic2.core.block.IUpgradableBlock;
import ic2.core.util.GuiTooltiphelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

/**
 * Created by BestOwl on 2015.10.31.0031.
 *
 * Gti base gui.
 * @author BestOwl
 */
public abstract class GuiGti<T extends ContainerGti> extends GuiContainer {
    public T container;
    public String name;

    protected int x;
    protected int y;

    public GuiGti(T container) {
        super(container);
        this.container = container;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int par1, int par2) {
        this.fontRendererObj.drawString(StatCollector.translateToLocal(Gti.GUI_PREFIX + this.name), (this.xSize - this.fontRendererObj.getStringWidth(this.name)) / 2, 6, 4210752);
        if(this.container.tile instanceof IUpgradableBlock) {
            GuiTooltiphelper.drawUpgradeslotTooltip(par1 - this.guiLeft, par2 - this.guiTop, 0, 0, 12, 12, (IUpgradableBlock) this.container.tile, 25, 0);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(getResource());
        this.x = (this.width - this.xSize) / 2;
        this.y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(this.x, this.y, 0, 0, this.xSize, this.ySize);

        if (this.container.tile instanceof IUpgradableBlock) {
            this.mc.getTextureManager().bindTexture(new ResourceLocation(IC2.textureDomain, "textures/gui/infobutton.png"));
            this.drawTexturedModalRect(this.x + 3, this.y + 3, 0, 0, 10, 10);
            this.mc.getTextureManager().bindTexture(this.getResource());
        }
    }

    protected abstract ResourceLocation getResource();
}
