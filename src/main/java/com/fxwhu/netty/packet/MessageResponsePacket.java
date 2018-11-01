package com.fxwhu.netty.packet;

import lombok.Data;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午10:51
 **/
@Data
public class MessageResponsePacket extends Packet {

    private String message;


    @Override
    public Byte getCommand() {
        return CommandEnum.MESSAGE_RESPONSE.getCode();
    }
}
