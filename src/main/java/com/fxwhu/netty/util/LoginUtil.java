package com.fxwhu.netty.util;

import com.fxwhu.netty.packet.PacketConstants;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午11:30
 **/
public class LoginUtil {
    public static void markAsLogin(Channel channel) {
        channel.attr(PacketConstants.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(PacketConstants.LOGIN);

        return loginAttr.get() != null;
    }
}
