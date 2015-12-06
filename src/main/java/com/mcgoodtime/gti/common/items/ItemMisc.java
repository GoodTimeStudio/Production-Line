package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.init.GtiItems;
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
        GtiItems.roller = this.next();
        GtiItems.heatInsulationMaterial = this.next();
        GtiItems.smallCompressedWaterHyacinth = this.next();
        GtiItems.airBrakeUnit = this.next();
        GtiItems.carbonTube = this.next();
        GtiItems.redstoneModule = this.next();
        GtiItems.lazuliModule = this.next();
        GtiItems.obsidianPlateGravityField = this.next();
        GtiItems.electronicCircuitControl = this.next();
        GtiItems.electronicCircuitCore = this.next();
        GtiItems.pulseElectronicCircuitControl = this.next();
        GtiItems.pulseElectronicCircuitCore = this.next();
        GtiItems.cyclotronParticleAccelerator = this.next();
        GtiItems.calculateUnit = this.next();
        GtiItems.calculateChunk = this.next();
        GtiItems.calculateArray = this.next();
        GtiItems.floatPointCalculationsRegion = this.next();
        GtiItems.parallelSpaceConverter = this.next();
        GtiItems.obsidianMechanicalFrame = this.next();
        GtiItems.obsidianMechanicalCasing = this.next();
        GtiItems.carbonCrystal = this.next();
        GtiItems.enderCalculationCrystal = this.next();
        GtiItems.millTeeth = this.next();
        GtiItems.millWheel = this.next();
        GtiItems.heatInsulationPlate = this.next();
        GtiItems.uuMatterCore = this.next();
        GtiItems.bambooCharcoal = this.next();
        GtiItems.rigidPaper = this.next();
        GtiItems.rigidPaperPack = this.next();
        GtiItems.coarseBatten = this.next();
        GtiItems.firewood = this.next();
        GtiItems.faggot = this.next();
        GtiItems.sawdust = this.next();
        GtiItems.woodPulp = this.next();
        GtiItems.corrugatedPaper = this.next();
        GtiItems.paperBag = this.next();
        GtiItems.bioFuel = this.next();
        GtiItems.tiles = this.next();
        GtiItems.advSolarLensUnit = this.next();
        GtiItems.advSolarLensGroup = this.next();
        GtiItems.advSolarLensCluster = this.next();
    }

    private ItemStack next() {
        return new ItemStack(this, 1, this.count++);
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

