package com.fxwhu.netty.client;

import com.fxwhu.exercise.LoggerUtil;
import com.fxwhu.netty.packet.*;
import com.fxwhu.netty.util.*;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.joda.time.DateTime;

import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author: fengxuan
 * @create 2018-10-27 下午11:35
 **/
public class ClientStart {

    final static int MAX_RETRY = 5;


    public static void main(String[] args) {

        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //ch.pipeline().addLast(new FirstClientHandler());
                        ch.pipeline().addLast(new PacketDecodeHandler());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncodeHandler());
                    }
                });


        connect(bootstrap, 8000, MAX_RETRY);
    }


    private static void connect(final Bootstrap bootstrap, int port, int retry) {
        bootstrap.connect("localhost", port)
                .addListener(new GenericFutureListener<Future<? super Void>>() {
                    @Override
                    public void operationComplete(Future<? super Void> future) throws Exception {
                        if (future.isSuccess()) {
                            LoggerUtil.ROOT.info("绑定端口成功, port:{}", port);
                            Channel channel = ((ChannelFuture) future).channel();
                            startConsoleThread(channel);
                        } else if (retry == 0) {
                            LoggerUtil.ROOT.info("绑定端口失败, port:{}", port);
                        } else {
                            int order = MAX_RETRY - retry + 1;
                            int delay = 1 << order;
                            LoggerUtil.ROOT.info(DateTime.now().toDate() + ",第{}次连接失败", order);
                            bootstrap.config().group()
                                    .schedule(() -> connect(bootstrap, port, retry - 1), delay, TimeUnit.SECONDS);
                        }
                    }
                });
    }



    private static void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    channel.writeAndFlush(new MessageRequestPacket(line));
                }
            }
        }).start();
    }





    static class FirstClientHandler extends ChannelInboundHandlerAdapter{
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            LoggerUtil.ROOT.info("客户端写入数据");

            ByteBuf byteBuf = getByteBuf(ctx);

            ctx.channel().writeAndFlush(byteBuf);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf msg1 = (ByteBuf) msg;
            LoggerUtil.ROOT.info("客户端接受到数据：{}", msg1.toString(Charset.forName("utf-8")));
        }


        private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
            ByteBuf buffer = ctx.alloc().buffer();

            byte[] bytes = "hello, first message!".getBytes(Charset.forName("utf-8"));
            buffer.writeBytes(bytes);
            return buffer;
        }
    }


    static class LoginClientHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            LoggerUtil.ROOT.info("客户端登录");

            LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
            loginRequestPacket.setUserId(100);
            loginRequestPacket.setUserName("vici");
            loginRequestPacket.setPassword("123");

            ByteBuf encode = PacketCodeC.INSTANCE.encode(loginRequestPacket);
            ctx.channel().writeAndFlush(encode);
        }
    }
}

