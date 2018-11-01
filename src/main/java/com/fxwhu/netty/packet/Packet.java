package com.fxwhu.netty.packet;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.accessibility.AccessibleValue;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午12:30
 **/
@Data
public abstract class Packet {

    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    @JSONField(serialize = false)
    public abstract Byte getCommand();

}
