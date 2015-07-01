package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.core.Gti;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mcgoodtime.gti.common.core.Gti.MOD_ID;

/**
 * Created by suhao on 2015.6.29.
 * @author suhao 
 */
public class ItemGtiRecord extends ItemRecord {

    private String name;

    public ItemGtiRecord(String name) {
        super(name);
        this.name = name;
        this.setCreativeTab(Gti.creativeTabGti);
        this.setUnlocalizedName(MOD_ID + "." + "record" + "." + name);
        this.setTextureName(Gti.RESOURCE_DOMAIN + ":" + name);
        GameRegistry.registerItem(this, name, MOD_ID);
    }

    @Override
    public String getRecordNameLocal() {
        return StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc");
    }

    @Override
    public ResourceLocation getRecordResource(String name) {
        return new ResourceLocation(Gti.RESOURCE_DOMAIN + ":" + this.name);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List tooltipList, boolean boo) {
        tooltipList.add(I18n.format(Gti.MOD_ID + ".tooltip.item.record" + "." + this.name + "." + "desc1"));
        tooltipList.add(I18n.format(Gti.MOD_ID + ".tooltip.item.record" + "." + this.name + "." + "desc2"));
    }

}
