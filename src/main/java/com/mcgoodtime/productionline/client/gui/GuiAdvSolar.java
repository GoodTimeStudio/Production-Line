package com.mcgoodtime.productionline.client.gui;

import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.inventory.ContainerAdvSolar;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 */
public class GuiAdvSolar extends GuiPL<ContainerAdvSolar> {

    public GuiAdvSolar(ContainerAdvSolar container) {
        super(container);
    }

    @Override
    protected ResourceLocation getResource() {
        return new ResourceLocation(ProductionLine.RESOURCE_DOMAIN, "textures/gui/GuiAdvSolar.png");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        super.drawGuiContainerForegroundLayer(x, y);

        if (!this.container.tile.hasLens) {
            String tooltip = I18n.format(ProductionLine.GUI_PREFIX + "AdvSolar.lens");
            drawTooltip(x - this.guiLeft, y - this.guiTop, Collections.singletonList(tooltip));
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);

        if (this.container.tile.sunIsVisible && this.container.tile.hasLens) {
            this.drawTexturedModalRect(this.x + 81, this.y + 45, 176, 0, 14, 14);
        }

        if (!this.container.tile.hasLens) {
            this.mc.renderEngine.bindTexture(new ResourceLocation(ProductionLine.RESOURCE_DOMAIN, "textures/gui/misc.png"));
            this.drawTexturedModalRect(this.x + 3, this.y + 3 + 10, 0, 246, 10, 10);
        }
    }
}
