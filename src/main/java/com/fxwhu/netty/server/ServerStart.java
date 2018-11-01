package com.fxwhu.netty.server;

import com.fxwhu.exercise.LoggerUtil;
import com.fxwhu.netty.packet.Packet;
import com.fxwhu.netty.packet.PacketCodeC;
import com.fxwhu.netty.util.PacketDecodeHandler;
import com.fxwhu.netty.util.PacketEncodeHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.nio.charset.Charset;

/**
 * @author: fengxuan
 * @create 2018-10-27 下午9:21
 **/
public class ServerStart {

    public static void main(String[] args) {
        NioEventLoopGroup boosGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();


        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap
                .group(boosGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        LoggerUtil.ROOT.info("服务端启动中");
                        //ch.pipeline().addLast(new FirstServerHandler());
                        ch.pipeline().addLast(new PacketDecodeHandler());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncodeHandler());
                    }
                });
        band(serverBootstrap, 8000);
        //其他方法如
        //serverBootstrap.attr(AttributeKey.valueOf("clientKey"), "abc");
        //serverBootstrap.childAttr()
        //TCP_NODELAY:表示是否开启Nagle算法，true表示关闭，false表示开启，通俗地说，如果要求高实时性
        // ，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
        //SO_KEEPALIVE 表示是否开启TCP底层心跳机制，true为开启
        //serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
    }


    private static void band(final ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port)
                .addListener(new GenericFutureListener<Future<? super Void>>() {

                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            LoggerUtil.ROOT.info("服务端绑定端口成功, port:{}", port);
                        } else {
                            LoggerUtil.ROOT.info("服务端绑定端口失败, port:{}", port);
                            band(serverBootstrap, port + 1);
                        }
                    }
                });
    }


    static class FirstServerHandler extends ChannelInboundHandlerAdapter{

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf msg1 = (ByteBuf) msg;
            LoggerUtil.ROOT.info("服务端接受到数据：{}", msg1.toString(Charset.forName("utf-8")));
            writeMessage(ctx);
        }

        private void writeMessage(ChannelHandlerContext ctx) {
            ByteBuf buffer = ctx.alloc().buffer();
            LoggerUtil.ROOT.info("服务端写出数据");
            byte[] bytes = "hello, server response first message!".getBytes(Charset.forName("utf-8"));

            buffer.writeBytes(bytes);
            ctx.channel().writeAndFlush(buffer);
        }
    }

    static class LoginServerHandler extends ChannelInboundHandlerAdapter{

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf msg1 = (ByteBuf) msg;
            Packet decode = PacketCodeC.INSTANCE.decode(msg1);
            LoggerUtil.ROOT.info("服务端接受到数据：{}", decode);
        }

    }


}
