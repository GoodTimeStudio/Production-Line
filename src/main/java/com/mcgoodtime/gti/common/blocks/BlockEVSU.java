package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.core.GuiHandler;
import com.mcgoodtime.gti.common.tiles.TileEVSU;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.api.item.IC2Items;
import ic2.api.tile.IWrenchable;
import ic2.core.IC2;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.util.StackUtil;
import ic2.core.util.Util;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;

import static com.mcgoodtime.gti.common.core.Gti.MOD_ID;
import static com.mcgoodtime.gti.common.core.Gti.RESOURCE_DOMAIN;
import static com.mcgoodtime.gti.common.core.Gti.creativeTabGti;

/**
 * Created by suhao on 2015-6-3-0003.
 */
public class BlockEVSU extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon top, output, input;

    public BlockEVSU() {
        super(Material.iron);
        this.setCreativeTab(creativeTabGti);
        this.setHardness(1.5F);
        this.setStepSound(soundTypeMetal);
        this.setBlockName(MOD_ID + "." + "block" + "." + "EVSU");
    }

    /**
     * World only
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int side){
        TileEntity tile = iBlockAccess.getTileEntity(x, y, z);
        if (tile instanceof TileEntityBlock) {
            switch (new Short(((TileEntityBlock)tile).getFacing()).intValue()) {
                case 0://Up
                    switch (side) {
                        case 2:
                            return this.top;
                        case 3:
                            return this.top;
                        case 0:
                            return this.output;
                        default:
                            return this.input;
                    }
                case 1://Down
                    switch (side) {
                        case 2:
                            return this.top;
                        case 3:
                            return this.top;
                        case 1:
                            return this.output;
                        default:
                            return this.input;
                    }
                case 2://South
                    switch (side) {
                        case 0:
                            return this.top;
                        case 1:
                            return this.top;
                        case 2:
                            return this.output;
                        default:
                            return this.input;
                    }
                case 3://North
                    switch (side) {
                        case 0:
                            return this.top;
                        case 1:
                            return this.top;
                        case 3:
                            return this.output;
                        default:
                            return this.input;
                    }
                case 4://East
                    switch (side) {
                        case 0:
                            return this.top;
                        case 1:
                            return this.top;
                        case 4:
                            return this.output;
                        default:
                            return this.input;
                    }
                case 5://West
                    switch (side) {
                        case 0:
                            return this.top;
                        case 1:
                            return this.top;
                        case 5:
                            return this.output;
                        default:
                            return this.input;
                    }
                default://Unknown
                    return input;
            }
        }
        return null;
    }

    /**
     * Hand only (side- not west or east
     */
    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata){
        switch(side){
            case 0: return this.top;
            case 1: return this.top;
            case 3: return this.output;
            default: return this.input;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register){
        this.top = register.registerIcon(RESOURCE_DOMAIN + ":" + "blockEVSU_top");
        this.output = register.registerIcon(RESOURCE_DOMAIN + ":" + "blockEVSU_out");
        this.input = register.registerIcon(RESOURCE_DOMAIN + ":" + "blockEVSU_in");
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess blockAccess, int x, int y, int z, int side) {
        TileEntity tile = blockAccess.getTileEntity(x, y, z);
        if (tile instanceof TileEntityElectricBlock) {
            return ((TileEntityElectricBlock)tile).isEmittingRedstone() ? 15 : 0;
        }
        return super.isProvidingWeakPower(blockAccess, x, y, z, side);
    }

    @Override
    public boolean canProvidePower(){
        return true;
    }

    @Override
    public boolean isNormalCube(IBlockAccess world, int i, int j, int k){
        return false;
    }

    @Override
    public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int side){
        return true;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack){
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityElectricBlock) {
            TileEntityElectricBlock electricBlock = (TileEntityElectricBlock)tile;
            NBTTagCompound nbt = StackUtil.getOrCreateNbtData(itemStack);
            electricBlock.energy = nbt.getDouble("energy");
            if (entityliving == null) {
                electricBlock.setFacing(convertIntegerToShort(1));
            } else {
                electricBlock.setFacing(convertIntegerToShort(BlockPistonBase.determineOrientation(world, x, y, z, entityliving)));
            }
        }
    }

    private static short convertIntegerToShort(int integer_n) {
        return new Integer(integer_n).shortValue();
    }

    @Override
    public boolean hasComparatorInputOverride(){
        return true;
    }

    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int side){
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof TileEntityElectricBlock) {
            TileEntityElectricBlock teb = (TileEntityElectricBlock) tile;
            return new Long(Math.round(Util.map(teb.energy, teb.maxStorage, 15.0D))).intValue();
        }
        return super.getComparatorInputOverride(world, x, y, z, side);
    }

    @Override
    public final boolean hasTileEntity(int metadata){
        return true;
    }

    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z){
        return false;
    }

    @Override
    public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis){
        if (axis == ForgeDirection.UNKNOWN) return false;
        TileEntity tileEntity = worldObj.getTileEntity(x, y, z);

        if ((tileEntity instanceof IWrenchable)) {
            IWrenchable te = (IWrenchable)tileEntity;

            short newFacing = convertIntegerToShort(ForgeDirection.getOrientation(te.getFacing()).getRotation(axis).ordinal());

            if (te.wrenchCanSetFacing(null, newFacing)) {
                te.setFacing(newFacing);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int var6, float var7, float var8, float var9){
        if (player.getCurrentEquippedItem() == IC2Items.getItem("wrench") || player.getCurrentEquippedItem() == IC2Items.getItem("electricWrench")) {
            return true;
        }
        if (!player.isSneaking()) {
            player.openGui(Gti.instance, GuiHandler.EnumGui.EVSU.ordinal(), world, x, y, z);
            return true;
        }
        return false;
    }

    @Override
    public void getSubBlocks(Item item, CreativeTabs tab, List stackList) {
        ItemStack zeroStack = new ItemStack(this, 1, 0);
        StackUtil.getOrCreateNbtData(zeroStack).setInteger("energy", 0);
        stackList.add(zeroStack);
        ItemStack fullStack = new ItemStack(this, 1, 1);
        StackUtil.getOrCreateNbtData(fullStack).setInteger("energy", TileEVSU.MAX_STORAGE);
        stackList.add(fullStack);
    }

    @Override
    public void breakBlock(World world, int xCoord, int yCoord, int zCoord, Block block, int par6) {
        if (world.isRemote) {
            return;
        }
        TileEntity tile = world.getTileEntity(xCoord, yCoord, zCoord);
        if (tile instanceof IInventory) {
            IInventory inventory = (IInventory)tile;
            for (int j1 = 0; j1 < inventory.getSizeInventory(); ++j1) {
                ItemStack itemstack = inventory.getStackInSlot(j1);
                if (itemstack != null) {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
                    while (itemstack.stackSize > 0) {
                        int k1 = world.rand.nextInt(21) + 10;
                        if (k1 > itemstack.stackSize) {
                            k1 = itemstack.stackSize;
                        }
                        itemstack.stackSize -= k1;
                        EntityItem entityitem = new EntityItem(world, xCoord + f, yCoord + f1, zCoord + f2, new ItemStack(itemstack.getItem(), k1, itemstack.getItemDamage()));
                        if (itemstack.hasTagCompound()) {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
                        }
                        entityitem.motionX = world.rand.nextGaussian() * 0.05F;
                        entityitem.motionY = world.rand.nextGaussian() * 0.05F + 0.2F;
                        entityitem.motionZ = world.rand.nextGaussian() * 0.05F;
                        world.spawnEntityInWorld(entityitem);
                    }
                }
            }
            world.func_147453_f(xCoord, yCoord, zCoord, block);
        }
        super.breakBlock(world, xCoord, yCoord, zCoord, block, par6);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEVSU();
    }
}
