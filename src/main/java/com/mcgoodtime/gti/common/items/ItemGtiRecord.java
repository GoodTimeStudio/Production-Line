package com.mcgoodtime.gti.common.items;

import com.mcgoodtime.gti.common.core.Gti;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.audio.SoundRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IWorldAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent17;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.client.event.sound.SoundEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;

import static com.mcgoodtime.gti.common.core.Gti.MOD_ID;

/**
 * Created by suhao on 2015.6.29.
 */
public class ItemGtiRecord extends ItemRecord {

    public ItemGtiRecord(String name) {
        super(name);
        this.setCreativeTab(Gti.creativeTabGti);
        this.setUnlocalizedName(MOD_ID + "." + name);
        this.setTextureName("record_13");
        GameRegistry.registerItem(this, name, MOD_ID);
        SoundRegistry soundRegistry = new SoundRegistry();
        soundRegistry.registerSound();
    }

}
