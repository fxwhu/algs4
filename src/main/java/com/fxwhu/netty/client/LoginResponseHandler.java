package com.fxwhu.netty.client;

import com.fxwhu.exercise.LoggerUtil;
import com.fxwhu.netty.packet.LoginRequestPacket;
import com.fxwhu.netty.packet.LoginResponsePacket;
import com.fxwhu.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午11:17
 **/
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 创建登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(1000);
        loginRequestPacket.setUserName("flash");
        loginRequestPacket.setPassword("pwd");

        // 写数据
        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        LoggerUtil.ROOT.info("服务端回应的登录信息");
        if (msg.isSuccess()) {
            LoggerUtil.ROOT.info("登录成功!");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            LoggerUtil.ROOT.info("登录失败!");
        }
    }
}
