package com.mcgoodtime.gti.common.network;

import com.mcgoodtime.gti.GtiUtil;
import com.mcgoodtime.gti.common.tiles.eustorage.TileEUStorage;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;

import static com.mcgoodtime.gti.common.tiles.eustorage.TileEUStorage.RedstoneMode;

/**
 * Created by BestOwl on 2015.11.28.0028.
 *
 * @author BestOwl
 */
public class MessageEUStorage implements IMessage {

    private NBTTagCompound nbt;

    public MessageEUStorage() {}

    public MessageEUStorage(TileEUStorage tile) {
        this.nbt = new NBTTagCompound();
        this.nbt.setInteger("xPos", tile.xCoord);
        this.nbt.setInteger("yPos", tile.yCoord);
        this.nbt.setInteger("zPos", tile.zCoord);
        int i = tile.redstoneMode.ordinal();
        i++;
        if (i >= RedstoneMode.values().length) {
            i = 0;
        }
        this.nbt.setShort("redstoneMode", (short) i);
    }

    /**
     * Convert from the supplied buffer into your specific message type
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        this.nbt = ByteBufUtils.readTag(buf);
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     */
    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.nbt);
    }

    public static class Handler implements IMessageHandler<MessageEUStorage, IMessage> {

        /**
         * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
         * is needed.
         *
         * @param message The message
         * @return an optional return message
         */
        @Override
        public IMessage onMessage(MessageEUStorage message, MessageContext ctx) {
            int x = message.nbt.getInteger("xPos");
            int y = message.nbt.getInteger("yPos");
            int z = message.nbt.getInteger("zPos");
            short modeID = message.nbt.getShort("redstoneMode");
            RedstoneMode mode = RedstoneMode.values()[modeID];
            TileEUStorage tile = (TileEUStorage) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(x, y, z);
            tile.redstoneMode = mode;
            GtiUtil.messageToPlayer(ctx.getServerHandler().playerEntity, StatCollector.translateToLocal("ic2.EUStorage.gui.mod.redstone" + modeID));
            return null;
        }
    }
}
