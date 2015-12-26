package com.mcgoodtime.gti.client.gui;

import com.mcgoodtime.gti.common.GtiUtil;
import com.mcgoodtime.gti.common.inventory.ContainerParallelSpaceSU;
import com.mcgoodtime.gti.common.network.GtiNetwork;
import ic2.core.GuiIconButton;
import ic2.core.util.GuiTooltipHelper;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static com.mcgoodtime.gti.common.core.Gti.RESOURCE_DOMAIN;
import static net.minecraft.util.StatCollector.translateToLocal;
import static net.minecraft.util.StatCollector.translateToLocalFormatted;

/**
 * Created by BestOwl on 2015.11.29.0029.
 *
 * @author BestOwl
 */
public class GuiParallelSpaceSU extends GuiGti<ContainerParallelSpaceSU> {

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
                152, (this.height - this.ySize) / 2 + 4, 20, 20, new ItemStack(Items.redstone), true));
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        super.drawGuiContainerForegroundLayer(x, y);
        this.fontRendererObj.drawString("§l" + translateToLocal("ic2.EUStorage.gui.info.level"), 60, 25, 0x9A00FF);
        GuiTooltipHelper.drawAreaTooltip(x - this.guiLeft, y - this.guiTop, "§l§5" + ((int) this.container.tile.energy)
                + "/" + this.container.tile.maxEnergy, 62, 36, 115, 44);
        this.fontRendererObj.drawString(translateToLocalFormatted("ic2.EUStorage.gui.info.output",
                this.container.tile.energyTick), 60, 51, 0x9A00FF);
        GuiTooltipHelper.drawAreaTooltip(x - this.guiLeft, y - this.guiTop, translateToLocal("ic2.EUStorage.gui.mod.redstone"
                + this.container.tile.redstoneMode.ordinal()), 153, 3, 172, 22);

    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        super.drawGuiContainerBackgroundLayer(f, x, y);
        if(this.container.tile.energy > 0.0D) {
            int i = GtiUtil.getGuiScaled(58, (float) this.container.tile.energy, this.container.tile.maxEnergy);
            this.drawTexturedModalRect(this.x + 60, this.y + 34, 176, 15, i + 1, 13);
        }
    }

    @Override
    protected ResourceLocation getResource() {
        return new ResourceLocation(RESOURCE_DOMAIN, "textures/gui/GuiParallelSpaceSU.png");
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
        if (button.id == 0) {
            GtiNetwork.updateTileEUStorage(this.container.tile);
        }
    }
}
