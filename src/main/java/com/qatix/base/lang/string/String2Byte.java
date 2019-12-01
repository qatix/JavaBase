package com.qatix.base.lang.string;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class String2Byte {
    public static byte[] str2Bytes(String str) {
        if (null == str) {
            return null;
        }
        return str.getBytes();
    }

    public static byte[] str2Bytes(String str, String charsetName) throws UnsupportedEncodingException {
        if (null == str) {
            return null;
        }
        return str.getBytes(charsetName);
    }


    public static String bytes2String(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        return new String(bytes);
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(Arrays.toString(str2Bytes("123")));
        System.out.println(Arrays.toString(str2Bytes("123", "UTF-8")));
        System.out.println(bytes2String(new byte[]{49, 53, 52}));
    }
}
