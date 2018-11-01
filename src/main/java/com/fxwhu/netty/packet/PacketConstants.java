package com.fxwhu.netty.packet;

import com.google.common.collect.Maps;
import io.netty.util.AttributeKey;

import java.util.Map;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午12:34
 **/
public class PacketConstants {

    public static byte JSON = 1;

    public static AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

}
