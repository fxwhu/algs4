package com.fxwhu.netty.packet;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午12:38
 **/
public interface Serializer {

    static Serializer Default = new JSONSerializer();

    byte[] serializ(Object o);


    <T> T deserializ(byte[] bytes, Class<T> clazz);
}
