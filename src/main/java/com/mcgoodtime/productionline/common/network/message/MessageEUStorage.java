//package com.mcgoodtime.productionline.common.network.message;
//
//import com.mcgoodtime.productionline.common.PLUtil;
//import com.mcgoodtime.productionline.common.tiles.eustorage.TileEUStorage;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
//import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
//import net.minecraft.util.StatCollector;
//
//import static com.mcgoodtime.productionline.common.tiles.eustorage.TileEUStorage.RedstoneMode;
//
///**
// * Created by BestOwl on 2015.11.28.0028.
// *
// * @author BestOwl
// */
//public class MessageEUStorage extends MessageBase {
//
//    public MessageEUStorage() {}
//
//    public MessageEUStorage(TileEUStorage tile) {
//        super(tile);
//        int i = tile.redstoneMode.ordinal();
//        i++;
//        if (i >= RedstoneMode.values().length) {
//            i = 0;
//        }
//        this.nbt.setShort("redstoneMode", (short) i);
//    }
//
//    @Override
//    protected IMessage handlerMessage(MessageBase message, MessageContext ctx) {
//        int x = message.nbt.getInteger("xPos");
//        int y = message.nbt.getInteger("yPos");
//        int z = message.nbt.getInteger("zPos");
//        short modeID = message.nbt.getShort("redstoneMode");
//        RedstoneMode mode = RedstoneMode.values()[modeID];
//        TileEUStorage tile = (TileEUStorage) ctx.getServerHandler().playerEntity.worldObj.getTileEntity(x, y, z);
//        tile.redstoneMode = mode;
//        PLUtil.messageToPlayer(ctx.getServerHandler().playerEntity, StatCollector.translateToLocal("ic2.EUStorage.gui.mod.redstone" + modeID));
//        return null;
//    }
//
//}
