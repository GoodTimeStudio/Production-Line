package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.core.Gti;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by BestOwl on 2016.1.28.0028.
 *
 * @author BestOwl
 */
public class ItemDiamondApple extends ItemFood {

    public ItemDiamondApple() {
        super(1000, 10F, false);
        this.setUnlocalizedName(Gti.MOD_ID + ".food.DiamondApple");
        this.setCreativeTab(Gti.creativeTabGti);
        this.setTextureName(Gti.MOD_ID + ":itemDiamondApple");
        this.setHasSubtypes(true);
        this.setAlwaysEdible();
        GameRegistry.registerItem(this, "DiamondApple");
    }

    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(Potion.field_76444_x.id, 2400, 0));
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 6000, 4));

            if (itemStack.getItemDamage() == 1) {
                player.addPotionEffect(new PotionEffect(Potion.field_76434_w.id, 12000, 0));
                player.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 4));
                player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
            }
        }
        super.onFoodEaten(itemStack, world, player);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean bool) {
        list.add(I18n.format(this.getUnlocalizedName() + ".desc1"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list) {
        for (int i = 0; i < 2; i++) {
            list.add(new ItemStack(this, 1, i));
        }
    }

    /**
     * Return an item rarity from EnumRarity
     */
    public EnumRarity getRarity(ItemStack itemStack) {
        return itemStack.getItemDamage() == 0 ? EnumRarity.rare : EnumRarity.epic;
    }

    @Override
    public boolean hasEffect(ItemStack itemStack) {
        return itemStack.getItemDamage() >= 1;
    }
}
