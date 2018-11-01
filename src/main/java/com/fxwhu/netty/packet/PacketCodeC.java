package com.fxwhu.netty.packet;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.Map;

/**
 * @author: fengxuan
 * @create 2018-10-28 上午12:36
 **/
public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x12345678;

    public static PacketCodeC INSTANCE = new PacketCodeC();

    public static Map<CommandEnum, Class<? extends Packet>> COMMAND_PACKAGE = Maps.newHashMap();

    static {
        COMMAND_PACKAGE.put(CommandEnum.LOGIN_REQUEST, LoginRequestPacket.class);
        COMMAND_PACKAGE.put(CommandEnum.LOGIN_RESPONSE, LoginResponsePacket.class);
        COMMAND_PACKAGE.put(CommandEnum.MESSAGE_REQUEST, MessageRequestPacket.class);
        COMMAND_PACKAGE.put(CommandEnum.MESSAGE_RESPONSE, MessageResponsePacket.class);
    }

    public ByteBuf encode(Packet packet) {
        ByteBuf byteBuf = ByteBufAllocator.DEFAULT.ioBuffer();
        byte[] bytes = Serializer.Default.serializ(packet);
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(PacketConstants.JSON);
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }


    public Packet decode(ByteBuf byteBuf) {
        byteBuf.skipBytes(4);
        byte version = byteBuf.readByte();
        byte serialize = byteBuf.readByte();
        byte command = byteBuf.readByte();
        int length = byteBuf.readInt();
        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);
        Class<? extends Packet> clazz = COMMAND_PACKAGE.get(CommandEnum.findByCode(command));
        if (clazz != null && bytes != null) {
            Packet packet = Serializer.Default.deserializ(bytes, clazz);
            return packet;
        }
        return null;
    }
}
