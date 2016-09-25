//package com.mcgoodtime.productionline.common.items;
//
//import com.mcgoodtime.productionline.common.core.ProductionLine;
//import ic2.api.item.IBoxable;
//import net.minecraft.client.renderer.texture.IIconRegister;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.IIcon;
//
///**
// * Created by BestOwl on 2015.12.6.0006.
// *
// * @author BestOwl
// */
//public class ItemCEU extends ItemElectricPL implements IBoxable {
//
//    private IIcon[] icons;
//
//    public ItemCEU() {
//        super("CEU", 1, 20000);
//    }
//
//    @Override
//    public void registerIcons(IIconRegister iconRegister) {
//        this.icons = new IIcon[5];
//        for (int i = 0; i < 5; i++) {
//            this.icons[i] = iconRegister.registerIcon(ProductionLine.RESOURCE_DOMAIN + ":" + "eustorage/itemCEU." + (i + 1));
//        }
//    }
//
//    /**
//     * Gets an icon index based on an item's damage value
//     */
//    @Override
//    public IIcon getIconFromDamage(int meta) {
//        if (meta < 3) {
//            return this.icons[0];
//        }
//        else if (meta < 10) {
//            return this.icons[1];
//        }
//        else if (meta < 17) {
//            return this.icons[2];
//        }
//        else if (meta < 24) {
//            return this.icons[3];
//        } else {
//            return this.icons[4];
//        }
//    }
//
//    @Override
//    public boolean canProvideEnergy(ItemStack itemStack) {
//        return true;
//    }
//
//    /**
//     * Determine whether an item can be stored in a toolbox or not.
//     *
//     * @param itemstack item to be stored
//     * @return Whether to store the item in the toolbox or not
//     */
//    @Override
//    public boolean canBeStoredInToolbox(ItemStack itemstack) {
//        return true;
//    }
//}
