package com.fxwhu.netty.packet;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午10:51
 **/
@Data
@NoArgsConstructor
public class MessageRequestPacket extends Packet {

    private String message;

    public MessageRequestPacket(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return CommandEnum.MESSAGE_REQUEST.getCode();
    }
}
