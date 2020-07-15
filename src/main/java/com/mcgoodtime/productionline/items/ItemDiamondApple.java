package com.mcgoodtime.productionline.items;

import com.mcgoodtime.productionline.client.IItemModelProvider;
import com.mcgoodtime.productionline.core.ProductionLine;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by BestOwl on 2016.1.28.0028.
 *
 * @author BestOwl
 */
public class ItemDiamondApple extends ItemFood {

    public ItemDiamondApple() {
        super(1000, 10F, false);
        this.setUnlocalizedName(ProductionLine.MOD_ID + ".diamond_apple");
        this.setCreativeTab(ProductionLine.creativeTabPL);
        this.setHasSubtypes(true);
        this.setAlwaysEdible();
        this.setRegistryName(new ResourceLocation(ProductionLine.MOD_ID, "diamond_apple"));
        ForgeRegistries.ITEMS.register(this);
    }

    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, @Nonnull EntityPlayer player) {
        if (!world.isRemote) {
            player.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION, 2400, 0));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6000, 4));

            if (itemStack.getItemDamage() == 1) {
                player.addPotionEffect(new PotionEffect(MobEffects.HEALTH_BOOST, 12000, 0));
                player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 6000, 4));
                player.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 6000, 0));
            }
        }
        super.onFoodEaten(itemStack, world, player);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format(this.getUnlocalizedName() + ".desc1"));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        for (int i = 0; i < 2; i++) {
            items.add(new ItemStack(this, 1, i));
        }
    }

    /**
     * Return an item rarity from EnumRarity
     */
    @Nonnull
    public EnumRarity getRarity(ItemStack itemStack) {
        return itemStack.getItemDamage() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
    }

    @Override
    public boolean hasEffect(ItemStack itemStack) {
        return itemStack.getItemDamage() >= 1;
    }

}
