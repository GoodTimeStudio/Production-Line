package com.mcgoodtime.productionline.common.blocks;

import com.mcgoodtime.productionline.common.core.GuiHandler;
import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.init.PLBlocks;
import com.mcgoodtime.productionline.common.items.ItemBlockEUStorage;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mcgoodtime.productionline.common.core.ProductionLine.*;

/**
 * Created by BestOwl on 2015.11.25.0025.
 *
 * @author BestOwl
 * @since 0.2
 */
public class BlockEUStorage extends BlockContainerPL implements IMultiMetaBlock {

    private static List<String> internalNameList = new ArrayList<>();
    private final PropertyEnum<Type> property = PropertyEnum.create("type", Type.class);

    static {
        for (Type type : Type.values()) {
            internalNameList.add(type.getName());
        }
    }

    public enum Type implements IStringSerializable {
        EVSU("EVSU"),
        CSEU("CSEU"),
        PARALLEL_SPACE_SU("ParallelSpaceSU"),;

        private final String name;

        Type(String name) {
            this.name = name;
        }

        @Override
        @Nonnull
        public String getName() {
            return name;
        }
    }

    public BlockEUStorage() {
        super(Material.IRON, "BlockEUStorage");
        this.setHardness(2.0F);
        for (int i = 0; i < this.getMaxMeta(); i++) {
            GameRegistry.registerTileEntity(this.getTileEntityClass(i), internalNameList.get(i));
        }

        PLBlocks.evsu = new ItemStack(this, 1, 0);
        PLBlocks.cseu = new ItemStack(this, 1, 1);
        PLBlocks.parallelSpaceSU = new ItemStack(this, 1, 2);
    }

    @Override
    public Class<? extends ItemBlock> getItemBlockClass() {
        return ItemBlockEUStorage.class;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            GuiHandler.EnumGui gui = this.getGui(state.getValue(property));
            if (gui != null) {
                player.openGui(ProductionLine.getInstance(), gui.ordinal(), world, pos.getX(), pos.getY(), pos.getZ());
            }
        }
        return true;
    }

    private GuiHandler.EnumGui getGui(Type type) {
        switch (type) {
            case EVSU:
                return GuiHandler.EnumGui.EVSU;
            case CSEU:
                return GuiHandler.EnumGui.CSEU;
            case PARALLEL_SPACE_SU:
                return GuiHandler.EnumGui.ParallelSpaceSU;
            default:
                throw new AssertionError();
        }
    }

    @Override
    protected Class<? extends TilePL> getTileEntityClass(int meta) {
        switch (meta) {
            case 0:
                return TileEVSU.class;
            case 1:
                return TileCSEU.class;
            case 2:
                return TileParallelSpaceSU.class;
            default:
                return null;
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return IC2Items.getItem("machine").getItem();
    }

    @Override
    public int getMaxMeta() {
        return internalNameList.size();
    }

    /**
     * Returns the unlocalized name of this block.
     */
    public String getBlockName(ItemStack itemStack) {
        return "tile." + MOD_NAME + ".block." + this.getInternalName(itemStack.getItemDamage());
    }

    /**
     * Get block's unlocalized name
     *
     * @return unlocalized name
     */
    @Override
    public String getBlockName(int meta) {
        return "tile." + MOD_NAME + ".block." + this.getInternalName(meta);
    }

    @Override
    public String getInternalName(int meta) {
        return internalNameList.get(meta);
    }

    @Override
    public boolean canProvidePower(IBlockState state) {
        return true;
    }

    @Override
    public int getWeakPower(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side) {
        TileEUStorage tile = (TileEUStorage) blockAccess.getTileEntity(pos);
        return tile.shouldEmitRedstonePower() ? 15 : 0;
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, placer, stack);
        TileEUStorage tile = (TileEUStorage) world.getTileEntity(pos);
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(stack);
        tile.energy = nbt.getDouble("energy");
    }
}
