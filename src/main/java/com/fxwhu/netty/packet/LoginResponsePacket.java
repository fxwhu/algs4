package com.fxwhu.netty.packet;

import lombok.Data;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午12:29
 **/
@Data
public class LoginResponsePacket extends Packet{

    private boolean success;

    private String message;

    @Override
    public Byte getCommand() {
        return CommandEnum.LOGIN_RESPONSE.getCode();
    }
}
