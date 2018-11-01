package com.fxwhu.netty.server;

import com.fxwhu.exercise.LoggerUtil;
import com.fxwhu.netty.packet.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午11:17
 **/
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        LoggerUtil.ROOT.info("收到客户端消息:{}", msg.getMessage());
        messageResponsePacket.setMessage("服务端回复： " + msg.getMessage());
        ctx.channel().writeAndFlush(messageResponsePacket);
    }
}
