package com.mcgoodtime.productionline.network.message;

import com.mcgoodtime.productionline.PLUtil;
import com.mcgoodtime.productionline.tiles.eustorage.TileEUStorage;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static com.mcgoodtime.productionline.tiles.eustorage.TileEUStorage.RedstoneMode;

/**
 * Created by BestOwl on 2015.11.28.0028.
 *
 * @author BestOwl
 */
public class MessageEUStorage extends MessageBase {

    public MessageEUStorage() {}

    public MessageEUStorage(TileEUStorage tile) {
        super(tile);
        int i = tile.redstoneMode.ordinal();
        i++;
        if (i >= RedstoneMode.values().length) {
            i = 0;
        }
        this.nbt.setShort("redstoneMode", (short) i);
    }

    @Override
    protected IMessage handlerMessage(MessageBase message, MessageContext ctx) {
        long pos = message.nbt.getLong("pos");
        short modeID = message.nbt.getShort("redstoneMode");
        RedstoneMode mode = RedstoneMode.values()[modeID];
        TileEUStorage tile = (TileEUStorage) ctx.getServerHandler().playerEntity.world.getTileEntity(BlockPos.fromLong(pos));
        tile.redstoneMode = mode;
        PLUtil.messageToPlayer(ctx.getServerHandler().playerEntity, I18n.translateToLocal("ic2.EUStorage.gui.mod.redstone" + modeID));
        return null;
    }

}
