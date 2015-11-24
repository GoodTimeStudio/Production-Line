package com.mcgoodtime.gti.common.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.tileentity.TileEntity;
import org.jboss.serial.io.JBossObjectInputStream;
import org.jboss.serial.io.JBossObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by suhao on 2015.11.21.0021.
 *
 * @author BestOwl
 * @since 0.2
 */
public class MessageUpdateTileEntity implements IMessage {

    private TileEntityField tileEntityField;

    public MessageUpdateTileEntity() {}

    public MessageUpdateTileEntity(String fieldName, Object object, int x, int y, int z) {
        this.tileEntityField = new TileEntityField(fieldName, object, x, y, z);
        System.out.print(x);
        System.out.print(y);
        System.out.print(z);
    }

    /**
     * Convert from the supplied buffer into your specific message type
     */
    @Override
    public void fromBytes(ByteBuf buf) {
        try {
            int len = buf.readInt();
            byte[] bytes = new byte[len];
            buf.readBytes(bytes, 0, len);
            System.out.println("In:bytes len: " + bytes.length);
            System.out.println("In:array len: " + buf.array().length);
            System.out.println("In:array offset: " + buf.arrayOffset());
            /*
            for (byte b : buf.array()) {
                System.out.println("In:    " + b);
            }
            System.out.println("--------");*/

            ByteArrayInputStream byteIn = new ByteArrayInputStream((bytes));
            JBossObjectInputStream objIn = new JBossObjectInputStream(byteIn);
            this.tileEntityField = (TileEntityField) objIn.readObject();
            objIn.close();
            byteIn.close();
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
            JBossObjectOutputStream objOut = new JBossObjectOutputStream(byteOut);
            objOut.writeObject(this.tileEntityField);
            byte[] bytes = byteOut.toByteArray();
            objOut.close();
            byteOut.close();
            buf.writeInt(bytes.length);
            buf.writeBytes(bytes);
            System.out.println("Out:bytes len: " + bytes.length);
            System.out.println("Out:array len: " + buf.array().length);
            /*
            System.out.println("Out:array offset: " + buf.arrayOffset());
            for (byte b : buf.array()) {
                System.out.println("Out:    " + b);
            }*/
            System.out.println("--------");
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
            System.out.print(field.x);
            System.out.print(field.y);
            System.out.print(field.z);
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
