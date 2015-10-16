package com.mcgoodtime.gti.common.blocks;

import com.mcgoodtime.gti.common.tiles.TileGti;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/*
 * Created by suhao on 2015.7.13.
 */
public class BlockMultiGti extends BlockGti {

    @SideOnly(Side.CLIENT)
    protected IIcon top, low, front, back, left, right;

    public BlockMultiGti(Material material, String name, float hardness, float resistance, String harvestLevelToolClass, int harvestLevel) {
        super(material, name, hardness, resistance, harvestLevelToolClass, harvestLevel);
    }

    public BlockMultiGti(Material material, String name) {
        super(material, name);
    }

    /**
     * World only
     */
    @Override
    public IIcon getIcon(IBlockAccess iBlockAccess, int x, int y, int z, int side) {
        TileEntity te = iBlockAccess.getTileEntity(x, y, z);
        if (te instanceof TileGti) {
            switch (((TileGti) te).facing) {
                case 2:
                    switch (side) {
                        case 5: return left;
                        case 4: return right;
                        case 2: return front;
                        case 3: return back;
                    }
                case 5:
                    switch (side) {
                        case 3: return left;
                        case 2: return right;
                        case 5: return front;
                        case 4: return back;
                    }
                case 3:
                    switch (side) {
                        case 4: return left;
                        case 5: return right;
                        case 3: return front;
                        case 2: return back;
                    }
                case 4:
                    switch (side) {
                        case 2: return left;
                        case 3: return right;
                        case 4: return front;
                        case 5: return back;
                    }
            }
        }

        if (side == 1) {
            return top;
        }
        if (side == 0) {
            return low;
        }
        return null;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        TileEntity te = world.getTileEntity(x, y, z);
        if (te instanceof TileGti) {
            if(entityLivingBase == null) {
                ((TileGti) te).facing = (short) 2;
            } else {
                int l = MathHelper.floor_double((double) (entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
                switch(l) {
                    case 0:
                        ((TileGti) te).facing = (short) 2;
                        break;
                    case 1:
                        ((TileGti) te).facing = (short) 5;
                        break;
                    case 2:
                        ((TileGti) te).facing = (short) 3;
                        break;
                    case 3:
                        ((TileGti) te).facing = (short) 4;
                }
            }
        }

        System.out.println( ((TileGti) te).facing );
    }
}
