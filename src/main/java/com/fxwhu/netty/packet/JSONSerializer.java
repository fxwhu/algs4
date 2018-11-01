package com.fxwhu.netty.packet;

import com.alibaba.fastjson.JSON;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午12:39
 **/
public class JSONSerializer implements Serializer {
    @Override
    public byte[] serializ(Object o) {
        return JSON.toJSONBytes(o);
    }

    @Override
    public Object deserializ(byte[] bytes, Class clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
