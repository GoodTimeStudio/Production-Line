package com.mcgoodtime.productionline.client.gui;

import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.inventory.ContainerAdvSolar;
import ic2.core.util.GuiTooltipHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

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
            String tooltip = StatCollector.translateToLocal(ProductionLine.GUI_PREFIX + "AdvSolar.lens");
            GuiTooltipHelper.drawAreaTooltip(x - this.guiLeft, y - this.guiTop, tooltip, 3, 13, 13, 23);
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
            this.drawTexturedModalRect(this.x + 3, this.y + 3 +10, 0, 246, 10, 10);
        }
    }
}
