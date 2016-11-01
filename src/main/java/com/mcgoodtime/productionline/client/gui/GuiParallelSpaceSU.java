package com.mcgoodtime.productionline.client.gui;

import com.mcgoodtime.productionline.common.PLUtil;
import com.mcgoodtime.productionline.common.inventory.ContainerParallelSpaceSU;
import com.mcgoodtime.productionline.common.network.PLNetwork;
import ic2.core.GuiIconButton;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.Collections;

import static com.mcgoodtime.productionline.common.core.ProductionLine.*;
import static net.minecraft.client.resources.I18n.format;

/**
 * Created by BestOwl on 2015.11.29.0029.
 *
 * @author BestOwl
 */
public class GuiParallelSpaceSU extends GuiPL<ContainerParallelSpaceSU> {

    public GuiParallelSpaceSU(ContainerParallelSpaceSU container) {
        super(container);
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        super.initGui();
        this.buttonList.add(new GuiIconButton(0, (this.width - this.xSize) / 2 +
                152, (this.height - this.ySize) / 2 + 4, 20, 20, new ItemStack(Items.REDSTONE), true));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        super.drawGuiContainerForegroundLayer(x, y);
        this.fontRendererObj.drawString("§l" + format("ic2.EUStorage.gui.info.level"), 60, 25, 0x9A00FF);
        drawTooltip(x - this.guiLeft, y - this.guiTop, Collections.singletonList("§l§5" + ((int) this.container.tile.energy)
                + "/" + this.container.tile.maxEnergy));
        this.fontRendererObj.drawString(format("ic2.EUStorage.gui.info.output",
                this.container.tile.energyTick), 60, 51, 0x9A00FF);
        drawTooltip(x - this.guiLeft, y - this.guiTop, Collections.singletonList(format("ic2.EUStorage.gui.mod.redstone"
                + this.container.tile.redstoneMode.ordinal())));

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        if (this.container.tile.energy > 0.0D) {
            int i = PLUtil.getGuiScaled(58, (float) this.container.tile.energy, this.container.tile.maxEnergy);
            this.drawTexturedModalRect(this.x + 60, this.y + 34, 176, 15, i + 1, 13);
        }
    }

    @Override
    protected ResourceLocation getResource() {
        return new ResourceLocation(RESOURCE_DOMAIN, "textures/gui/GuiParallelSpaceSU.png");
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button.id == 0) {
            PLNetwork.updateTileEUStorage(this.container.tile);
        }
    }
}
