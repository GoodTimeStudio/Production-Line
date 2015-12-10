package com.mcgoodtime.gti.common.items.tools;

import com.mcgoodtime.gti.common.core.Gti;
import com.mcgoodtime.gti.common.entity.EntityRay;
import com.mcgoodtime.gti.common.items.ItemElectricGti;
import ic2.api.item.ElectricItem;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

/**
 * Created by BestOwl on 2015.12.7.0007.
 *
 * @author BestOwl
 */
public class ItemGravityRay extends ItemElectricGti {

    private IIcon[] icons;

    public ItemGravityRay() {
        super("GravityRay", 3, (int) 11E6);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer player, int itemInUseCount) {
        if (ElectricItem.manager.getCharge(itemStack) >= 100) {
            int i = this.getMaxItemUseDuration(itemStack) - itemInUseCount;

            float damge = (float) i / 20.0F;
            damge = (damge * damge + damge * 2.0F) / 3.0F;
            if ((double)damge < 0.1D) {
                return;
            }
            if (damge > 1.0F) {
                damge = 1.0F;
            }

            world.playSoundAtEntity(player, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + damge * 0.5F);
            ElectricItem.manager.discharge(itemStack, 100, this.tier, false, true, false);
            if (!world.isRemote) {
                world.spawnEntityInWorld(new EntityRay(world, player, damge * 2.0F));
            }
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
        if (entityPlayer.capabilities.isCreativeMode || ElectricItem.manager.getCharge(itemStack) >= 100) {
            entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }
        return itemStack;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack itemStack) {
        return EnumAction.bow;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
    public int getMaxItemUseDuration(ItemStack itemStack) {
        return 72000;
    }

    /**
     * Player, Render pass, and item usage sensitive version of getIconIndex.
     *
     * @param stack        The item stack to get the icon for. (Usually this, and usingItem will be the same if usingItem is not null)
     * @param renderPass   The pass to get the icon for, 0 is default.
     * @param player       The player holding the item
     * @param usingItem    The item the player is actively using. Can be null if not using anything.
     * @param useRemaining The ticks remaining for the active item.
     * @return The icon index
     */
    @Override
    public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining) {
        int i = this.getMaxItemUseDuration(stack) - useRemaining;
        if (useRemaining == 0) {
            return this.itemIcon;
        }
        else if (i >= 18) {
            return this.icons[2];
        }
        else if (i > 13) {
            return this.icons[1];
        }
        else if (i > 0) {
            return this.icons[0];
        }
        else {
            return this.itemIcon;
        }
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(Gti.RESOURCE_DOMAIN + ":tools/" + this.getIconName() + "_standby");
        this.icons = new IIcon[3];
        for (int i = 0; i < 3; i++) {
            this.icons[i] = iconRegister.registerIcon(Gti.RESOURCE_DOMAIN + ":tools/" + this.getIconName() + "_pulling_" + i);
        }
    }

}
