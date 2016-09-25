//package com.mcgoodtime.productionline.common.network.message;
//
//import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
//import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
//
///**
// * Created by BestOwl on 2015.12.4.0004.
// *
// * @author BestOwl
// */
//public class MessageHandlerBase implements IMessageHandler<MessageBase, IMessage> {
//    /**
//     * Called when a message is received of the appropriate type. You can optionally return a reply message, or null if no reply
//     * is needed.
//     *
//     * @param message The message
//     * @return an optional return message
//     */
//    @Override
//    public IMessage onMessage(MessageBase message, MessageContext ctx) {
//        return message.handlerMessage(message, ctx);
//    }
//}
