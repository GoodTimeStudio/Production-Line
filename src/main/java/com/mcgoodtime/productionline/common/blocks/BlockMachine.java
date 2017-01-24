package com.mcgoodtime.productionline.common.blocks;

import static com.mcgoodtime.productionline.common.core.ProductionLine.MOD_ID;

import com.mcgoodtime.productionline.common.core.GuiHandler;
import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.init.PLBlocks;
import com.mcgoodtime.productionline.common.items.ItemBlockEUStorage;
import com.mcgoodtime.productionline.common.tiles.TileAdvSolar;
import com.mcgoodtime.productionline.common.tiles.TileCarbonizeFurnace;
import com.mcgoodtime.productionline.common.tiles.TileFluidKineticGenerator;
import com.mcgoodtime.productionline.common.tiles.TileHeatDryer;
import com.mcgoodtime.productionline.common.tiles.TilePL;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileCSEU;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileEUStorage;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileEVSU;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileParallelSpaceSU;
import ic2.api.item.IC2Items;
import ic2.core.util.StackUtil;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by BestOwl on 2015.11.25.0025.
 *
 * @author BestOwl
 * @since 0.2
 */
public class BlockMachine extends BlockContainerPL {

    private final PropertyEnum<Type> property = PropertyEnum.create("type", Type.class);

    public enum Type implements IStringSerializable {
        CARBONIZE_FURNACE("CarbonizeFurnace", TileCarbonizeFurnace.class, GuiHandler.EnumGui.CarbonizeFurnace),
        HEAT_DRYER("HeatDryer", TileHeatDryer.class, GuiHandler.EnumGui.HeatDryer),
        EVSU("EVSU", TileEVSU.class, GuiHandler.EnumGui.EVSU),
        CSEU("CSEU", TileCSEU.class, GuiHandler.EnumGui.CSEU),
        PARALLEL_SPACE_SU("ParallelSpaceSU", TileParallelSpaceSU.class, GuiHandler.EnumGui.ParallelSpaceSU),
        ADV_SOLAR("AdvSolar", TileAdvSolar.class, GuiHandler.EnumGui.AdvSolar),
        FLUID_KINETIC_GENERATOR("FluidKineticGenerator", TileFluidKineticGenerator.class, GuiHandler.EnumGui.FluidKineticGenerator);

        private final String name;
        public final Class<? extends TilePL> tileClass;
        public final GuiHandler.EnumGui gui;

        Type(String name, Class<? extends TilePL> tileClass, GuiHandler.EnumGui gui) {
            this.name = name;
            this.tileClass = tileClass;
            this.gui = gui;
        }

        @Override
        @Nonnull
        public String getName() {
            return name;
        }
    }

    public BlockMachine() {
        super(Material.IRON, "BlockMachine");
        this.setHardness(2.0F);
        for (Type t : Type.values()) {
            GameRegistry.registerTileEntity(t.tileClass, t.getName());
        }

//        PLBlocks.evsu = new ItemStack(this, 1, 0);
//        PLBlocks.cseu = new ItemStack(this, 1, 1);
//        PLBlocks.parallelSpaceSU = new ItemStack(this, 1, 2);
    }

    @Override
    public Class<? extends ItemBlock> getItemBlockClass() {
        return ItemBlockEUStorage.class;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem,
            EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(ProductionLine.getInstance(), state.getValue(property).gui.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    protected Class<? extends TilePL> getTileEntityClass(IBlockState state) {
        return state.getValue(property).tileClass;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return IC2Items.getItem("machine").getItem();
    }

    /**
     * Returns the unlocalized name of this block.
     */
    public String getBlockName(ItemStack itemStack) {
        return "tile." + MOD_ID + ".block.machine";
    }

//    @Override
//    public boolean canProvidePower(IBlockState state) {
//        return true;
//    }
//
//    @Override
//    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
//        TileEUStorage tile = (TileEUStorage) blockAccess.getTileEntity(pos);
//        return tile != null && tile.shouldEmitRedstonePower() ? 15 : 0;
//    }
//
//    @Override
//    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
//        super.onBlockPlacedBy(world, pos, state, placer, stack);
//        TileEUStorage tile = (TileEUStorage) world.getTileEntity(pos);
//        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
//        tile.energy = nbt.getDouble("energy");
//    }
}
