package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.core.GuiHandler;
import com.mcgoodtime.gti.common.init.GtiBlocks;
import com.mcgoodtime.gti.common.tiles.TileCarbonizeFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class BlockCarbonizeFurnace extends BlockContainer {
    private final Random rand1 = new Random();
    private final boolean ifBurn;
    private static boolean boo1;
    @SideOnly(Side.CLIENT)
    private IIcon sideTop;
    @SideOnly(Side.CLIENT)
    private IIcon sideLow;
    @SideOnly(Side.CLIENT)
    private IIcon sideFront;

    public BlockCarbonizeFurnace(boolean boo) {
        super(Material.rock);
        this.ifBurn = boo;
        this.setBlockName("gti.block.CarbonizeFurnace");
        this.setHardness(3.5F);
        this.setStepSound(soundTypePiston);
        this.setCreativeTab(Gti.creativeTabGti);
    }

    public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_) {
        return Item.getItemFromBlock(GtiBlocks.carbonizeFurnace);
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
        this.func_149930_e(world, x, y, z);
    }

    private void func_149930_e(World world, int x, int y, int z) {
        if (!world.isRemote) {
            Block block = world.getBlock(x, y, z - 1);
            Block block1 = world.getBlock(x, y, z + 1);
            Block block2 = world.getBlock(x - 1, y, z);
            Block block3 = world.getBlock(x + 1, y, z);
            byte b0 = 3;

            if (block.func_149730_j() && !block1.func_149730_j()) {
                b0 = 3;
            }

            if (block1.func_149730_j() && !block.func_149730_j()) {
                b0 = 2;
            }

            if (block2.func_149730_j() && !block3.func_149730_j()) {
                b0 = 5;
            }

            if (block3.func_149730_j() && !block2.func_149730_j()) {
                b0 = 4;
            }

            world.setBlockMetadataWithNotify(x, y, z, b0, 2);
        }
    }

    /**
     * Gets the block's texture. Args: side, meta
     */
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        return p_149691_1_ == 1 ? this.sideTop : (p_149691_1_ == 0
                ? this.sideLow : (p_149691_1_ != p_149691_2_ ? this.blockIcon : this.sideFront));
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iir) {
        this.blockIcon = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "blockCarbonizeFurnace_side");
        this.sideFront = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":"
                + "blockCarbonizeFurnace_front_" + (this.ifBurn ? "on" : "off"));
        this.sideTop = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":"
                + "blockCarbonizeFurnace_top_" + (this.ifBurn ? "on" : "off"));
        this.sideLow = iir.registerIcon(Gti.RESOURCE_DOMAIN + ":" + "blockCarbonizeFurnace_low");
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer,
                                    int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        if (world.isRemote) {
            return true;
        } else {
            entityPlayer.openGui(Gti.instance, GuiHandler.Guis.CarbonizeFurnace.ordinal(), world, x, y, z);
            return true;
        }
    }

    /**
     * Update which block the furnace is using depending on whether or not it is burning
     */
    public static void updateFurnaceBlockState(boolean p_149931_0_, World p_149931_1_, int p_149931_2_, int p_149931_3_, int p_149931_4_) {
        int l = p_149931_1_.getBlockMetadata(p_149931_2_, p_149931_3_, p_149931_4_);
        TileEntity tileentity = p_149931_1_.getTileEntity(p_149931_2_, p_149931_3_, p_149931_4_);
        boo1 = true;

        if (p_149931_0_) {
            p_149931_1_.setBlock(p_149931_2_, p_149931_3_, p_149931_4_, GtiBlocks.litCarbonizeFurnace);
        } else {
            p_149931_1_.setBlock(p_149931_2_, p_149931_3_, p_149931_4_, GtiBlocks.carbonizeFurnace);
        }

        boo1 = false;
        p_149931_1_.setBlockMetadataWithNotify(p_149931_2_, p_149931_3_, p_149931_4_, l, 2);

        if (tileentity != null) {
            tileentity.validate();
            p_149931_1_.setTileEntity(p_149931_2_, p_149931_3_, p_149931_4_, tileentity);
        }
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileCarbonizeFurnace();
    }

    /**
     * Called when the block is placed in the world.
     */
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase placer, ItemStack fromItem) {
        int l = MathHelper.floor_double((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0) {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1) {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2) {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3) {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }

        if (fromItem.hasDisplayName()) {
            ((TileCarbonizeFurnace)world.getTileEntity(x, y, z)).setName(fromItem.getDisplayName());
        }
    }

    public void breakBlock(World world, int x, int y, int z, Block blockBroken, int meta) {
        if (!boo1) {
            TileCarbonizeFurnace furnace = (TileCarbonizeFurnace)world.getTileEntity(x, y, z);

            if (furnace != null) {
                for (int i1 = 0; i1 < furnace.getSizeInventory(); ++i1) {
                    ItemStack itemstack = furnace.getStackInSlot(i1);

                    if (itemstack != null) {
                        float f = this.rand1.nextFloat() * 0.8F + 0.1F;
                        float f1 = this.rand1.nextFloat() * 0.8F + 0.1F;
                        float f2 = this.rand1.nextFloat() * 0.8F + 0.1F;

                        while (itemstack.stackSize > 0) {
                            int j1 = this.rand1.nextInt(21) + 10;

                            if (j1 > itemstack.stackSize) {
                                j1 = itemstack.stackSize;
                            }

                            itemstack.stackSize -= j1;
                            EntityItem entityitem = new EntityItem(
                                    world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2),
                                    new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage())
                            );

                            if (itemstack.hasTagCompound()) {
                                entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                            }

                            float f3 = 0.05F;
                            entityitem.motionX = (double)((float)this.rand1.nextGaussian() * f3);
                            entityitem.motionY = (double)((float)this.rand1.nextGaussian() * f3 + 0.2F);
                            entityitem.motionZ = (double)((float)this.rand1.nextGaussian() * f3);
                            world.spawnEntityInWorld(entityitem);
                        }
                    }
                }

                world.func_147453_f(x, y, z, blockBroken);
            }
        }

        super.breakBlock(world, x, y, z, blockBroken, meta);
    }

    /**
     * A randomly called display update to be able to add particles or other items for display
     */
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (this.ifBurn) {
            int l = world.getBlockMetadata(x, y, z);
            float f = (float) x + 0.5F;
            float f1 = (float) y + 0.0F + rand.nextFloat() * 6.0F / 16.0F;
            float f2 = (float) z + 0.5F;
            float f3 = 0.52F;
            float f4 = rand.nextFloat() * 0.6F - 0.3F;

            switch (l) {
                case 2:
                    world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 - f3), 0.0D, 0.0D, 0.0D);
                    break;
                case 3:
                    world.spawnParticle("smoke", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", (double)(f + f4), (double)f1, (double)(f2 + f3), 0.0D, 0.0D, 0.0D);
                    break;
                case 4:
                    world.spawnParticle("smoke", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", (double)(f - f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                    break;
                case 5:
                    world.spawnParticle("smoke", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                    world.spawnParticle("flame", (double)(f + f3), (double)f1, (double)(f2 + f4), 0.0D, 0.0D, 0.0D);
                    break;
                default:
            }
        }
    }

    /**
     * If this returns true, then comparators facing away from this block will use the value from
     * getComparatorInputOverride instead of the actual redstone signal strength.
     */
    public boolean hasComparatorInputOverride() {
        return true;
    }

    /**
     * If hasComparatorInputOverride returns true, the return value from this is used instead of the redstone signal
     * strength when this block inputs to a comparator.
     */
    public int getComparatorInputOverride(World world, int x, int y, int z, int p_149736_5_) {
        return Container.calcRedstoneFromInventory((IInventory) world.getTileEntity(x, y, z));
    }

    /**
     * Gets an item for the block being called on. Args: world, x, y, z
     */
    @SideOnly(Side.CLIENT)
    public Item getItem(World world, int x, int y, int z) {
        return Item.getItemFromBlock(GtiBlocks.carbonizeFurnace);
    }

    public static class BlockLitCarbonizeFurnace extends BlockCarbonizeFurnace {
        public BlockLitCarbonizeFurnace(boolean boo1) {
            super(boo1);
            this.setBlockName("gti.block.lit_CarbonizeFurnace");
            this.setCreativeTab(null);
            this.setLightLevel(0.875F);
        }
    }
}

