package com.mcgoodtime.productionline.common.items;

import com.mcgoodtime.productionline.common.init.PLItems;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by BestOwl on 2015.11.25.0025.
 *
 * @author BestOwl
 */
public class ItemCrafting extends ItemMulti {

    public ItemCrafting() {
        super("crafting");
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
        list.add("roller");
        list.add("heat_insulation_material");
        list.add("small_compressed_water_hyacinth");
        list.add("air_brake_unit");
        list.add("carbon_tube");
        list.add("redstone_module");
        list.add("lazuli_module");
        list.add("obsidian_plate_gravity_field");
        list.add("electronic_circuit_control");
        list.add("electronic_circuit_core");
        list.add("pulse_electronic_circuit_control");
        list.add("pulse_electronic_circuit_core");
        list.add("cyclotron_particle_accelerator");
        list.add("calculate_unit");
        list.add("calculate_chunk");
        list.add("calculate_array");
        list.add("float_point_calculations_region");
        list.add("parallel_space_converter");
        list.add("obsidian_mechanical_frame");
        list.add("obsidian_mechanical_casing");
        list.add("carbon_crystal");
        list.add("ender_calculation_crystal");
        list.add("mill_teeth");
        list.add("mill_wheel");
        list.add("heat_insulation_plate");
        list.add("uu_matter_core");
        list.add("bamboo_charcoal");
        list.add("rigid_paper");
        list.add("rigid_paper_pack");
        list.add("coarse_batten");
        list.add("firewood");
        list.add("faggot");
        list.add("sawdust");
        list.add("wood_pulp");
        list.add("corrugated_paper");
        list.add("paper_bag");
        list.add("bio_fuel");
        list.add("tiles");
        list.add("adv_solar_lens_unit");
        list.add("adv_solar_lens_group");
        list.add("adv_solar_lens_cluster");
        return list;
    }

    @Override
    public String getModelResourcePath() {
        return "crafting";
    }
}

