package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.core.GuiHandler;
import com.mcgoodtime.gti.common.tiles.TileEVSU;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import ic2.core.block.TileEntityBlock;
import ic2.core.block.wiring.TileEntityElectricBlock;
import ic2.core.util.StackUtil;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockPistonBase;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import static com.mcgoodtime.gti.common.core.Gti.RESOURCE_DOMAIN;

/**
 * Created by suhao on 2015-6-3-0003.
 */
public class BlockEVSU extends BlockContainer {

    @SideOnly(Side.CLIENT)
    private IIcon sideTop;
    @SideOnly(Side.CLIENT)
    private IIcon sideOut;
    @SideOnly(Side.CLIENT)
    private IIcon sideIn;

    public BlockEVSU() {
        super(Material.iron);
        this.setCreativeTab(Gti.creativeTabGti);
        this.setHardness(1.5f);
        this.setStepSound(soundTypeMetal);
        this.setBlockName("gti.block.EVSU");
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEVSU();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        TileEntity tile = iBlockAccess.getTileEntity(x, y, z);
        if (tile instanceof TileEntityBlock) {
            switch (new Short(((TileEntityBlock)tile).getFacing()).intValue()) {
                // Top
                case 0:
                    switch (side) {
                        case 2: return this.sideTop;
                        case 3: return this.sideTop;
                        case 0: return this.sideOut;
                        default: return this.sideIn;
                    }

                //Low
                case 1:
                    switch (side) {
                        case 2:
                            return this.sideTop;
                        case 3:
                            return this.sideTop;
                        case 1:
                            return this.sideOut;
                        default:
                            return this.sideIn;
                    }

                //South
                case 2:
                    switch (side) {
                        case 0:
                            return this.sideTop;
                        case 1:
                            return this.sideTop;
                        case 2:
                            return this.sideOut;
                        default:
                            return this.sideIn;
                    }

                //North
                case 3:
                    switch (side) {
                        case 0:
                            return this.sideTop;
                        case 1:
                            return this.sideTop;
                        case 3:
                            return this.sideOut;
                        default:
                            return this.sideIn;
                    }

                //East
                case 4:
                    switch (side) {
                        case 0:
                            return this.sideTop;
                        case 1:
                            return this.sideTop;
                        case 4:
                            return this.sideOut;
                        default:
                            return this.sideIn;
                    }

                //West
                case 5:
                    switch (side) {
                        case 0:
                            return this.sideTop;
                        case 1:
                            return this.sideTop;
                        case 5:
                            return this.sideOut;
                        default:
                            return this.sideIn;
                    }

                //Unknown
                default:
                    return sideIn;
            }
        }
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        switch (side) {
            case 0: return this.sideTop;
            case 1: return this.sideTop;
            case 3: return this.sideOut;
            default: return this.sideIn;
        }
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer,
                                    int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote) {
            return true;
        } else {
            entityPlayer.openGui(Gti.instance, GuiHandler.Guis.EVSU.ordinal(), world, x, y, z);
            return true;
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iir) {
        this.sideTop = iir.registerIcon(RESOURCE_DOMAIN + ":" + "blockEVSU_top");
        this.sideIn = iir.registerIcon(RESOURCE_DOMAIN + ":" + "blockEVSU_in");
        this.sideOut = iir.registerIcon(RESOURCE_DOMAIN + ":" + "blockEVSU_out");
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
}
