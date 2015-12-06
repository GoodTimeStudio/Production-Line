package com.mcgoodtime.gti.client.gui;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.inventory.ContainerAdvSolar;
import ic2.core.util.GuiTooltiphelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by BestOwl on 2015.12.5.0005.
 *
 * @author BestOwl
 */
public class GuiAdvSolar extends GuiGti<ContainerAdvSolar> {

    public GuiAdvSolar(ContainerAdvSolar container) {
        super(container);
    }

    @Override
    protected ResourceLocation getResource() {
        return new ResourceLocation(Gti.RESOURCE_DOMAIN, "textures/gui/GuiAdvSolar.png");
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        super.drawGuiContainerForegroundLayer(x, y);

        if (!this.container.tile.hasLens) {
            String tooltip = StatCollector.translateToLocal(Gti.GUI_PREFIX + "AdvSolar.lens");
            GuiTooltiphelper.drawAreaTooltip(x - this.guiLeft, y - this.guiTop, tooltip, 3, 13, 13, 23);
        }
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);

        if (this.container.tile.sunIsVisible && this.container.tile.hasLens) {
            this.drawTexturedModalRect(this.x + 81, this.y + 45, 176, 0, 14, 14);
        }

        if (!this.container.tile.hasLens) {
            this.mc.renderEngine.bindTexture(new ResourceLocation(Gti.RESOURCE_DOMAIN, "textures/gui/misc.png"));
            this.drawTexturedModalRect(this.x + 3, this.y + 3 +10, 0, 246, 10, 10);
        }
    }
}
