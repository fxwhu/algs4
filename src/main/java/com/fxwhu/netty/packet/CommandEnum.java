package com.fxwhu.netty.packet;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午10:55
 **/
public enum CommandEnum {
    LOGIN_REQUEST((byte) 1),
    LOGIN_RESPONSE((byte) 2),
    MESSAGE_REQUEST((byte) 3),
    MESSAGE_RESPONSE((byte) 4);

    private byte code;


    CommandEnum(byte code) {
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public static CommandEnum findByCode(byte code) {
        for (CommandEnum commandEnum : CommandEnum.values()) {
            if (commandEnum.code == code) {
                return commandEnum;
            }
        }
        return null;
    }
}
