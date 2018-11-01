package com.fxwhu.netty.util;

import com.fxwhu.exercise.LoggerUtil;
import com.fxwhu.netty.packet.Packet;
import com.fxwhu.netty.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午11:12
 **/
public class PacketEncodeHandler extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        out.writeBytes(PacketCodeC.INSTANCE.encode(msg));
        LoggerUtil.ROOT.info("out:{}", out.toString(Charset.forName("utf-8")));
    }
}
