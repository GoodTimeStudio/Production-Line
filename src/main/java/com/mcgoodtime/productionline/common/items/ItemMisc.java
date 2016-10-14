package com.mcgoodtime.productionline.common.items;

import com.mcgoodtime.productionline.common.init.PLItems;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BestOwl on 2015.11.25.0025.
 *
 * @author BestOwl
 */
public class ItemMisc extends ItemMultiDamage {

    private int count = 0;

    public ItemMisc() {
        super("ItemMisc");
        PLItems.roller = this.next();
        PLItems.heatInsulationMaterial = this.next();
        PLItems.smallCompressedWaterHyacinth = this.next();
        PLItems.airBrakeUnit = this.next();
        PLItems.carbonTube = this.next();
        PLItems.redstoneModule = this.next();
        PLItems.lazuliModule = this.next();
        PLItems.obsidianPlateGravityField = this.next();
        PLItems.electronicCircuitControl = this.next();
        PLItems.electronicCircuitCore = this.next();
        PLItems.pulseElectronicCircuitControl = this.next();
        PLItems.pulseElectronicCircuitCore = this.next();
        PLItems.cyclotronParticleAccelerator = this.next();
        PLItems.calculateUnit = this.next();
        PLItems.calculateChunk = this.next();
        PLItems.calculateArray = this.next();
        PLItems.floatPointCalculationsRegion = this.next();
        PLItems.parallelSpaceConverter = this.next();
        PLItems.obsidianMechanicalFrame = this.next();
        PLItems.obsidianMechanicalCasing = this.next();
        PLItems.carbonCrystal = this.next();
        PLItems.enderCalculationCrystal = this.next();
        PLItems.millTeeth = this.next();
        PLItems.millWheel = this.next();
        PLItems.heatInsulationPlate = this.next();
        PLItems.uuMatterCore = this.next();
        PLItems.bambooCharcoal = this.next();
        PLItems.rigidPaper = this.next();
        PLItems.rigidPaperPack = this.next();
//        PLItems.coarseBatten = this.next();
//        PLItems.firewood = this.next();
        PLItems.faggot = this.next();
        PLItems.sawdust = this.next();
        PLItems.woodPulp = this.next();
        PLItems.corrugatedPaper = this.next();
        PLItems.paperBag = this.next();
//        PLItems.bioFuel = this.next();
        PLItems.tiles = this.next();
        PLItems.advSolarLensUnit = this.next();
        PLItems.advSolarLensGroup = this.next();
        PLItems.advSolarLensCluster = this.next();
    }

    @Override
    protected List<String> getInternalNameList() {
        List<String> list = new ArrayList<String>();
        list.add("Roller");
        list.add("HeatInsulationMaterial");
        list.add("SmallCompressedWaterHyacinth");
        list.add("AirBrakeUnit");
        list.add("CarbonTube");
        list.add("RedstoneModule");
        list.add("LazuliModule");
        list.add("ObsidianPlateGravityField");
        list.add("ElectronicCircuitControl");
        list.add("ElectronicCircuitCore");
        list.add("PulseElectronicCircuitControl");
        list.add("PulseElectronicCircuitCore");
        list.add("CyclotronParticleAccelerator");
        list.add("CalculateUnit");
        list.add("CalculateChunk");
        list.add("CalculateArray");
        list.add("FloatPointCalculationsRegion");
        list.add("ParallelSpaceConverter");
        list.add("ObsidianMechanicalFrame");
        list.add("ObsidianMechanicalCasing");
        list.add("CarbonCrystal");
        list.add("EnderCalculationCrystal");
        list.add("MillTeeth");
        list.add("MillWheel");
        list.add("HeatInsulationPlate");
        list.add("UUMatterCore");
        list.add("BambooCharcoal");
        list.add("RigidPaper");
        list.add("RigidPaperPack");
        list.add("CoarseBatten");
        list.add("Firewood");
        list.add("Faggot");
        list.add("Sawdust");
        list.add("WoodPulp");
        list.add("CorrugatPaper");
        list.add("PaperBag");
        list.add("BioFuel");
        list.add("Tiles");
        list.add("AdvSolarLensUnit");
        list.add("AdvSolarLensGroup");
        list.add("AdvSolarLensCluster");
        return list;
    }
}

