package com.mcgoodtime.productionline.common.network.message;

import com.mcgoodtime.productionline.common.tiles.TilePL;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by BestOwl on 2015.12.1.0001.
 *
 * @author BestOwl
 */
public abstract class MessageBase implements IMessage {

    public NBTTagCompound nbt;

    public MessageBase() {}

    public MessageBase(TilePL tile) {
        this.nbt = new NBTTagCompound();
        this.nbt.setInteger("xPos", tile.xCoord);
        this.nbt.setInteger("yPos", tile.yCoord);
        this.nbt.setInteger("zPos", tile.zCoord);
    }

    protected abstract IMessage handlerMessage(MessageBase message, MessageContext ctx);

    /**
     * Deconstruct your message into the supplied byte buffer
     */
    @Override
    public final void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.nbt);
    }

    /**
     * Convert from the supplied buffer into your specific message type
     */
    @Override
    public final void fromBytes(ByteBuf buf) {
        this.nbt = ByteBufUtils.readTag(buf);
    }
}
