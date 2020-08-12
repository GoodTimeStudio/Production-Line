package com.mcgoodtime.productionline.blocks;

import com.mcgoodtime.productionline.client.IBlockModelProvider;
import com.mcgoodtime.productionline.core.GuiHandler;
import com.mcgoodtime.productionline.core.ProductionLine;
import com.mcgoodtime.productionline.tiles.TileFacing;
import ic2.api.item.IC2Items;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;
import java.util.Random;

public class BlockWirelessMachine extends BlockContainerPL implements IOrientableBlock, IMultiIDBlock<PropertyEnum<BlockWirelessMachine.Type>>, IBlockModelProvider {

    public static final PropertyEnum<Type> PROPERTY_TYPE = PropertyEnum.create("type", Type.class);
    public static final PropertyBool PROPERTY_ACTIVE = PropertyBool.create("active");

    public enum Type implements IStringSerializable, IBlockType {;

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


    public BlockWirelessMachine(Material material, String name) {
        super(Material.IRON, "wireless_machine");
        this.setHardness(2.0F);
        this.setLightLevel(10.0F);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PROPERTY_FACING, EnumFacing.NORTH)
                .withProperty(PROPERTY_ACTIVE, false));
        for (BlockWirelessMachine.Type t : BlockWirelessMachine.Type.values()) {
            GameRegistry.registerTileEntity(t.tileClass, t.getName());
        }
    }

    @Override
    public ModelResourceLocation getModelResourceLocation(int meta) {
        ResourceLocation res = new ResourceLocation(ProductionLine.RESOURCE_DOMAIN, BlockWirelessMachine.Type.values()[meta].getTypeName());
        return new ModelResourceLocation(res, "inventory");
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return IC2Items.getItem("wireless_machine").getItem();
    }

    @Override
    protected Class<? extends TileEntity> getTileEntityClass(IBlockState state) {
        return state.getValue(PROPERTY_TYPE).tileClass;
    }

    @Nonnull
    @Override
    public PropertyEnum<Type> getBlockTypeContainer(){
        return PROPERTY_TYPE;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        if (stack.getMetadata() < BlockMachine.Type.values().length) {
            world.setBlockState(pos, state.withProperty(PROPERTY_TYPE, BlockWirelessMachine.Type.values()[stack.getMetadata()]), 2);
        }

        TileFacing tile = (TileFacing) world.getTileEntity(pos);
        if (tile != null) {
            tile.setFacing(placer.getHorizontalFacing().getOpposite());
        }
    }

}
