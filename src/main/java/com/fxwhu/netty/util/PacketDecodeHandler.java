package com.fxwhu.netty.util;

import com.fxwhu.netty.packet.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午11:12
 **/
public class PacketDecodeHandler extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("PacketDecoder");
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
