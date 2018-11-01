package com.fxwhu.netty.server;

import com.fxwhu.exercise.LoggerUtil;
import com.fxwhu.netty.packet.LoginRequestPacket;
import com.fxwhu.netty.packet.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午11:17
 **/
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoggerUtil.ROOT.info("客户端请求登录验证");
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (msg == null) {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setMessage("还未登录");
        } else {
            loginResponsePacket.setSuccess(true);
            loginResponsePacket.setMessage("恭喜您，" + msg.getUserName() + "登录成功");
        }
        LoggerUtil.ROOT.info("loginResponsePacket:{}", loginResponsePacket);
        ctx.channel().writeAndFlush(loginResponsePacket);
    }
}
