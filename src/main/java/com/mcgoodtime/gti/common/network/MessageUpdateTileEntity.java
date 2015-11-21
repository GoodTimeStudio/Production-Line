package com.mcgoodtime.gti.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import org.apache.commons.lang3.mutable.MutableObject;


import java.io.*;

/**
 * Created by suhao on 2015.11.21.0021.
 *
 * @author BestOwl
 * @since 0.2
 */
public class MessageUpdateTileEntity implements IMessage {

    private TileEntityField tileEntityField;

    public MessageUpdateTileEntity(String fieldName, Object object, int x, int y, int z) {
        this.tileEntityField = new TileEntityField(fieldName, object, x, y, z);
    }

    /**
     * Convert from the supplied buffer into your specific message type
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            ByteArrayInputStream byteIn = new ByteArrayInputStream(buf.array());
            ObjectInputStream objIn = new ObjectInputStream(byteIn);
            Object obj = objIn.readObject();
            if (obj instanceof MutableObject) {
                MutableObject object = (MutableObject) objIn.readObject();
                this.tileEntityField = (TileEntityField) object.getValue();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deconstruct your message into the supplied byte buffer
     */
    @Override
    public void toBytes(ByteBuf buf) {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
            MutableObject<TileEntityField> object = new MutableObject<TileEntityField>();
            object.setValue(this.tileEntityField);
            objOut.writeObject(object);
            byte[] bytes = byteOut.toByteArray();
            objOut.close();
            byteOut.close();
            buf.writeBytes(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Handler implements IMessageHandler<MessageUpdateTileEntity, IMessage> {

        /**
         * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
         * is needed.
         *
         * @param message The message
         * @return an optional return message
         */
        @Override
        public IMessage onMessage(MessageUpdateTileEntity message, MessageContext ctx) {
            WorldClient client = Minecraft.getMinecraft().theWorld;
            TileEntityField field = message.tileEntityField;
            TileEntity tileEntity = client.getTileEntity(field.x, field.y, field.z);
            try {
                tileEntity.getClass().getField(field.fieldName).set(tileEntity, field.object);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public static class TileEntityField implements Serializable {
        public String fieldName;
        public Object object;
        public int x;
        public int y;
        public int z;

        public TileEntityField(String fieldName, Object object, int x, int y, int z) {
            this.fieldName = fieldName;
            this.object = object;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
