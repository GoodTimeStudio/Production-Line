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

    public ItemMisc() {
        super("ItemMisc");
        GtiItems.roller = new ItemStack(this, 1, 0);
        GtiItems.heatInsulationMaterial = new ItemStack(this, 1, 1);
        GtiItems.smallCompressedWaterHyacinth = new ItemStack(this, 1, 2);
        GtiItems.airBrakeUnit = new ItemStack(this, 1, 3);
        GtiItems.carbonTube = new ItemStack(this, 1, 4);
        GtiItems.redstoneModule = new ItemStack(this, 1, 5);
        GtiItems.lazuliModule = new ItemStack(this, 1, 6);
        GtiItems.obsidianPlateGravityField = new ItemStack(this, 1, 7);
        GtiItems.electronicCircuitControl = new ItemStack(this, 1, 8);
        GtiItems.electronicCircuitCore = new ItemStack(this, 1, 9);
        GtiItems.pulseElectronicCircuitControl = new ItemStack(this, 1, 10);
        GtiItems.pulseElectronicCircuitCore = new ItemStack(this, 1, 11);
        GtiItems.cyclotronParticleAccelerator = new ItemStack(this, 1, 12);
        GtiItems.calculateUnit = new ItemStack(this, 1, 13);
        GtiItems.calculateChunk = new ItemStack(this, 1, 14);
        GtiItems.calculateArray = new ItemStack(this, 1, 15);
        GtiItems.floatPointCalculationsRegion = new ItemStack(this, 1, 16);
        GtiItems.parallelSpaceConverter = new ItemStack(this, 1, 17);
        GtiItems.obsidianMechanicalFrame = new ItemStack(this, 1, 18);
        GtiItems.obsidianMechanicalCasing = new ItemStack(this, 1, 19);
        GtiItems.carbonCrystal = new ItemStack(this, 1,20);
        GtiItems.enderCalculationCrystal = new ItemStack(this, 1, 21);
        GtiItems.millTeeth = new ItemStack(this, 1, 22);
        GtiItems.millWheel = new ItemStack(this, 1, 23);
        GtiItems.heatInsulationPlate = new ItemStack(this, 1, 24);
        GtiItems.uuMatterCore = new ItemStack(this, 1, 25);
        GtiItems.bambooCharcoal = new ItemStack(this, 1, 26);
        GtiItems.rigidPaper = new ItemStack(this, 1, 27);
        GtiItems.rigidPaperPack = new ItemStack(this, 1, 28);
        GtiItems.batten = new ItemStack(this,1,29);
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
        list.add("batten");
        return list;
    }
}
