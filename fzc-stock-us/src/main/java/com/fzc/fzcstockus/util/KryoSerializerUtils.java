package com.fzc.fzcstockus.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * kryo序列化工具类
 * @author flamenco.xxx
 * @date 2021/11/25 14:34
 */
public class KryoSerializerUtils {



    public static byte[] serialize(Object object) {
        byte[] bytes = null;
        if (object == null) {
            return bytes;
        }
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Output output = new Output(baos)) {
            kryo.writeClassAndObject(output, object);
            output.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    public static Object deserialize(byte[] bytes){
        Object object = null;
        if (bytes == null) {
            return null;
        }
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        try (InputStream abis = new ByteArrayInputStream(bytes);
             Input input = new Input(abis)) {
            object = kryo.readClassAndObject(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return object;
    }
}
