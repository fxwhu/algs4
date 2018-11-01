package com.fxwhu.netty.packet;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午12:29
 **/
@Data
public class LoginRequestPacket extends Packet{

    private Integer userId;

    private String userName;

    private String password;

    @Override
    public Byte getCommand() {
        return CommandEnum.LOGIN_REQUEST.getCode();
    }
}
