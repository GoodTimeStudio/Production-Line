package com.mcgoodtime.productionline.client.gui;

import com.mcgoodtime.productionline.PLUtil;
import com.mcgoodtime.productionline.inventory.ContainerEUStorage;
import com.mcgoodtime.productionline.network.PLNetwork;
import com.mcgoodtime.productionline.tiles.eustorage.TileEUStorage;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import java.io.IOException;
import java.util.Collections;

import static com.mcgoodtime.productionline.core.ProductionLine.*;
import static net.minecraft.client.resources.I18n.*;

/**
 * Created by BestOwl on 2015.11.28.0028.
 *
 * @author BestOwl
 */
public class GuiEUStorage extends GuiPL<ContainerEUStorage> {

    public GuiEUStorage(ContainerEUStorage container) {
        super(container);
        this.ySize = 196;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        super.initGui();
        /*this.buttonList.add(new GuiIconButton(0, (this.width - this.xSize) / 2 +
                152, (this.height - this.ySize) / 2 + 4, 20, 20, new ItemStack(Items.REDSTONE), true));*/
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        super.drawGuiContainerForegroundLayer(x, y);
        this.fontRenderer.drawString(format("ic2.EUStorage.gui.info.armor"), 8, this.ySize - 126 + 3, 4210752);
        this.fontRenderer.drawString(format("ic2.EUStorage.gui.info.level"), 79, 25, 4210752);
        this.fontRenderer.drawString(" " + ((int) ((TileEUStorage) this.container.tile).energy), 110, 35, 4210752);
        this.fontRenderer.drawString("/" + ((TileEUStorage) this.container.tile).maxEnergy, 110, 45, 4210752);
        this.fontRenderer.drawString(format("ic2.EUStorage.gui.info.output",
                ((TileEUStorage) this.container.tile).energyTick), 85, 60, 4210752);
        drawTooltip(x - this.guiLeft, y - this.guiTop, Collections.singletonList(format("ic2.EUStorage.gui.mod.redstone"
                + ((TileEUStorage) this.container.tile).redstoneMode.ordinal())));

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        if (((TileEUStorage) this.container.tile).energy > 0.0D) {
            int i = PLUtil.getGuiScaled(24, (float) ((TileEUStorage) this.container.tile).energy, ((TileEUStorage) this.container.tile).maxEnergy);
            this.drawTexturedModalRect(this.x + 79, this.y + 34, 176, 14, i + 1, 16);
        }
    }

    @Override
    protected ResourceLocation getResource() {
        return new ResourceLocation(RESOURCE_DOMAIN, "textures/gui/GuiEUStorage.png");
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);
        if (button.id == 0) {
            PLNetwork.updateTileEUStorage((TileEUStorage) this.container.tile);
        }
    }
}
