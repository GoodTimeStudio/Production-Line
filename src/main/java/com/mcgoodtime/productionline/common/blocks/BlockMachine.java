package com.mcgoodtime.productionline.common.blocks;

import com.mcgoodtime.productionline.client.IBlockModelProvider;
import com.mcgoodtime.productionline.common.PLUtil;
import com.mcgoodtime.productionline.common.core.GuiHandler;
import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.init.PLBlocks;
import com.mcgoodtime.productionline.common.items.ItemBlockPL;
import com.mcgoodtime.productionline.common.tiles.*;
import com.mcgoodtime.productionline.common.tiles.TilePL;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileCSEU;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileEUStorage;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileEVSU;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileParallelSpaceSU;
import ic2.api.item.IC2Items;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
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
public class BlockMachine extends BlockContainerPL implements IOrientableBlock, IMultiIDBlock<PropertyEnum<BlockMachine.Type>>, IBlockModelProvider {

    public static final PropertyEnum<Type> PROPERTY_TYPE = PropertyEnum.create("type", Type.class);
    public static final PropertyBool PROPERTY_ACTIVE = PropertyBool.create("active");

    public enum Type implements IStringSerializable, IBlockType {
        CARBONIZE_FURNACE("carbonize_furnace", TileCarbonizeFurnace.class, GuiHandler.EnumGui.CarbonizeFurnace),
        HEAT_DRYER("heat_dryer", TileHeatDryer.class, GuiHandler.EnumGui.HeatDryer),
        EVSU("evsu",TileEVSU.class, GuiHandler.EnumGui.EVSU),
        CSEU("cseu",TileCSEU.class, GuiHandler.EnumGui.CSEU),
        PARALLEL_SPACE_SU("parallel_space_su",TileParallelSpaceSU.class, GuiHandler.EnumGui.ParallelSpaceSU),
        ADV_SOLAR("adv_solar", TileAdvSolar.class, GuiHandler.EnumGui.AdvSolar),
        FLUID_KINETIC_GENERATOR("fluid_kinetic_generator", TileFluidKineticGenerator.class, GuiHandler.EnumGui.FluidKineticGenerator);

        private final String name;
        public final Class<? extends TileFacing> tileClass;
        public final GuiHandler.EnumGui gui;

        Type(String name, Class<? extends TileFacing> tileClass, GuiHandler.EnumGui gui) {
            this.name = name;
            this.tileClass = tileClass;
            this.gui = gui;
        }

        @Override
        @Nonnull
        public String getName() {
            return name;
        }

        @Override
        public String getTypeName() {
            return this.name;
        }
    }

    public BlockMachine() {
        super(Material.IRON, "machine");
        this.setHardness(2.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PROPERTY_FACING, EnumFacing.NORTH)
                .withProperty(PROPERTY_ACTIVE, false));
        for (Type t : Type.values()) {
            GameRegistry.registerTileEntity(t.tileClass, t.getName());
        }

        PLBlocks.carbonizeFurnace = new ItemStack(this);
        PLBlocks.heatDryer = new ItemStack(this, 1, 1);
        PLBlocks.evsu = new ItemStack(this, 1, 2);
        PLBlocks.cseu = new ItemStack(this, 1, 3);
        PLBlocks.parallelSpaceSU = new ItemStack(this, 1, 4);
        PLBlocks.advSolar = new ItemStack(this, 1, 5);
        PLBlocks.fluidKineticGenerator = new ItemStack(this, 1, 6);
    }

    @Override
    public ModelResourceLocation getModelResourceLocation(int meta) {
        ResourceLocation res = new ResourceLocation(ProductionLine.RESOURCE_DOMAIN, BlockMachine.Type.values()[meta].getTypeName());
        return new ModelResourceLocation(res, "inventory");
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileCarbonizeFurnace) {
            if (((TileCarbonizeFurnace) te).active) {
                float xmod;
                float ymod;
                float zmod;

                float x = (float)pos.getX() + 1.0F;
                float y = (float)pos.getY() + 1.0F;
                float z = (float)pos.getZ() + 1.0F;

                for(int i = 0; i < 4; ++i) {
                    xmod = -0.2F - rand.nextFloat() * 0.6F;
                    ymod = -0.1F + rand.nextFloat() * 0.2F;
                    zmod = -0.2F - rand.nextFloat() * 0.6F;
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)(x + xmod), (double)(y + ymod),
                            (double)(z + zmod), 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

    @Nonnull
    @Override
    public PropertyEnum<Type> getBlockTypeContainer() {
        return PROPERTY_TYPE;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem,
            EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            player.openGui(ProductionLine.getInstance(), state.getValue(PROPERTY_TYPE).gui.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
        }
        return true;
    }

    @Override
    protected Class<? extends TileFacing> getTileEntityClass(IBlockState state) {
        return state.getValue(PROPERTY_TYPE).tileClass;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return IC2Items.getItem("machine").getItem();
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
    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.getMetadata() < Type.values().length) {
            world.setBlockState(pos, state.withProperty(PROPERTY_TYPE, Type.values()[stack.getMetadata()]), 2);
        }

        TileFacing tile = (TileFacing) world.getTileEntity(pos);
        if (tile != null) {
            if (tile instanceof TileEUStorage) {
                tile.setFacing(PLUtil.getFacingFromEntity(pos, placer));
            }
            else {
                tile.setFacing(placer.getHorizontalFacing().getOpposite());
            }

        }

//        TileEUStorage tile = (TileEUStorage) world.getTileEntity(pos);
//        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
//        tile.energy = nbt.getDouble("energy");
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(PROPERTY_TYPE).ordinal();
    }

    /**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     *
     */
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        // safety check #see https://mcforge-cn.readthedocs.io/zh/latest/blockstates/states/
        TileFacing tile = (TileFacing) (worldIn instanceof ChunkCache ? ((ChunkCache)worldIn).getTileEntity(pos,
                Chunk.EnumCreateEntityType.CHECK) : worldIn.getTileEntity(pos));

        state = state.withProperty(PROPERTY_FACING, tile.facing);
        state = state.withProperty(PROPERTY_ACTIVE, tile.active);

        return state;
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta) {
        if (meta < Type.values().length) {
            return this.getDefaultState().withProperty(PROPERTY_TYPE, Type.values()[meta]);
        }
        return null;
    }

    @Override
    protected void registerItemBlock() {
        GameRegistry.<Item>register(new ItemBlockPL(this), this.getRegistryName());
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, PROPERTY_FACING, PROPERTY_TYPE, PROPERTY_ACTIVE);
    }
}
