/*
package com.mcgoodtime.productionline.common.blocks;

import com.mcgoodtime.productionline.common.core.ProductionLine;
import com.mcgoodtime.productionline.common.core.GuiHandler;
import com.mcgoodtime.productionline.common.init.PLBlocks;
import com.mcgoodtime.productionline.common.items.ItemBlockEUStorage;
import com.mcgoodtime.productionline.common.tiles.TilePL;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileCSEU;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileEUStorage;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileEVSU;
import com.mcgoodtime.productionline.common.tiles.eustorage.TileParallelSpaceSU;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ic2.api.item.IC2Items;
import ic2.core.block.BlockTextureStitched;
import ic2.core.util.StackUtil;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mcgoodtime.productionline.common.core.ProductionLine.MOD_NAME;
import static com.mcgoodtime.productionline.common.core.ProductionLine.RESOURCE_DOMAIN;

/**
 * Created by BestOwl on 2015.11.25.0025.
 *
 * @author BestOwl
 * @since 0.2
 *//*
public class BlockEUStorage extends BlockContainerPL implements IMultiMetaBlock {

    private static List<String> internalNameList = new ArrayList<String>();
    public IIcon textures[][];

    static {
        internalNameList.add("EVSU");
        internalNameList.add("CSEU");
        internalNameList.add("ParallelSpaceSU");
    }

    public BlockEUStorage() {
        super(Material.iron, "BlockEUStorage");
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
     *//*
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int i, float f1, float f2, float f3) {
        if (!world.isRemote) {
            GuiHandler.EnumGui gui = this.getGui(world.getBlockMetadata(x, y, z));
            if (gui != null) {
                player.openGui(ProductionLine.instance, gui.ordinal(), world, x, y, z);
            }
        } else {
            player.isInvisibleToPlayer(player);
        }
        return true;
    }

    private GuiHandler.EnumGui getGui(int meta) {
        switch (meta) {
            case 0: return GuiHandler.EnumGui.EVSU;
            case 1: return GuiHandler.EnumGui.CSEU;
            case 2: return GuiHandler.EnumGui.ParallelSpaceSU;
            default: return null;
        }
    }

    @Override
    protected Class<? extends TilePL> getTileEntityClass(int meta) {
        switch (meta) {
            case 0: return TileEVSU.class;
            case 1: return TileCSEU.class;
            case 2: return TileParallelSpaceSU.class;
            default: return null;
        }
    }

    /**
     * World only
     *//*
    @Override
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        if (iBlockAccess.getTileEntity(x, y, z) instanceof TilePL) {
            int facing = ((TilePL) iBlockAccess.getTileEntity(x, y, z)).facing;
            boolean isBurn = ((TilePL) iBlockAccess.getTileEntity(x, y, z)).active;

            int i = DIRECTION[facing][side];
            if (isBurn) {
                i += 6;
            }

            return textures[iBlockAccess.getBlockMetadata(x, y, z)][i];
        }
        return null;
    }

    /**
     * Hand only
     * side:
     * 1:top  5:east  3:south
     * 0:low  4:west  2:north
     *
     *//*
    @Override
    public IIcon getIcon(int side, int meta) {
        return this.textures[meta][DIRECTION[3][side]];
    }

    @Override
    public void registerBlockIcons(IIconRegister iir) {
        this.textures = new IIcon[this.getMaxMeta()][6];
        for (int meta = 0; meta < this.getMaxMeta(); meta++) {
            for(int side = 0; side < 6; ++side) {
                String name = RESOURCE_DOMAIN + ":" + "block" + this.getInternalName(meta) + ":" + side;
                BlockTextureStitched texture = new BlockTextureStitched(name, side);
                this.textures[meta][side] = texture;
                ((TextureMap)iir).setTextureEntry(name, texture);
            }
        }
    }

    @Override
    public Item getItemDropped(int var1, Random random, int var2) {
        return IC2Items.getItem("machine").getItem();
    }

    @Override
    public int getMaxMeta() {
        return internalNameList.size();
    }

    /**
     * Returns the unlocalized name of this block.
     *//*
    public String getBlockName(ItemStack itemStack) {
        return "tile." + MOD_NAME + ".block." + this.getInternalName(itemStack.getItemDamage());
    }

    /**
     * Get block's unlocalized name
     * @return unlocalized name
     *//*
    @Override
    public String getBlockName(int meta) {
        return "tile." + MOD_NAME + ".block." + this.getInternalName(meta);
    }

    @Override
    public String getInternalName(int meta) {
        return internalNameList.get(meta);
    }

    /**
     * Can this block provide power. Only wire currently seems to have this change based on its state.
     *//*
    @Override
    public boolean canProvidePower() {
        return true;
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        TileEUStorage tile = (TileEUStorage) iBlockAccess.getTileEntity(x, y, z);
        return tile.shouldEmitRedstonePower() ? 15 : 0;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        super.onBlockPlacedBy(world, x, y, z, entityLivingBase, itemStack);
        TileEUStorage tile = (TileEUStorage) world.getTileEntity(x, y, z);
        NBTTagCompound nbt = StackUtil.getOrCreateNbtData(itemStack);
        tile.energy = nbt.getDouble("energy");
    }
}*/
