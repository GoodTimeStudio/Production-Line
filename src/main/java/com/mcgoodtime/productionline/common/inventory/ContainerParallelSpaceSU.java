//package com.mcgoodtime.productionline.common.inventory;
//
//import com.mcgoodtime.productionline.common.tiles.eustorage.TileEUStorage;
//import com.mcgoodtime.productionline.common.tiles.eustorage.TileParallelSpaceSU;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.ICrafting;
//
///**
// * Created by BestOwl on 2015.11.29.0029.
// *
// * @author BestOwl
// */
//public class ContainerParallelSpaceSU extends ContainerPL<TileParallelSpaceSU> {
//
//    public double lastEnergy;
//    public int lastMode;
//
//    public ContainerParallelSpaceSU(EntityPlayer player, TileParallelSpaceSU tile) {
//        super(player, tile);
//    }
//
//    
//    @Override
//    public void addCraftingToCrafters(ICrafting iCrafting) {
//        super.addCraftingToCrafters(iCrafting);
//        iCrafting.sendProgressBarUpdate(this, 0, (int) this.tile.energy);
//        iCrafting.sendProgressBarUpdate(this, 1, this.tile.redstoneMode.ordinal());
//    }
//
//    /**
//     * Looks for changes made in the container, sends them to every listener.
//     */
//    @Override
//    public void detectAndSendChanges() {
//        super.detectAndSendChanges();
//
//        for (Object object : this.crafters) {
//            if (this.lastEnergy != this.tile.energy) {
//                ((ICrafting) object).sendProgressBarUpdate(this, 0, (int) this.tile.energy);
//            }
//            if (this.lastMode != this.tile.redstoneMode.ordinal()) {
//                ((ICrafting) object).sendProgressBarUpdate(this, 1, this.tile.redstoneMode.ordinal());
//            }
//        }
//
//        this.lastEnergy = this.tile.energy;
//        this.lastMode = this.tile.redstoneMode.ordinal();
//    }
//
//    @Override
//    public void updateProgressBar(int id, int var) {
//        super.updateProgressBar(id, var);
//        switch (id) {
//            case 0:
//                this.tile.energy = var;
//                break;
//            case 1:
//                this.tile.redstoneMode = TileEUStorage.RedstoneMode.values()[var];
//                break;
//        }
//    }
//}
