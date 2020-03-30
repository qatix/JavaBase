package com.qatix.base.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.apache.commons.lang3.math.NumberUtils;

import java.nio.ByteBuffer;

public class BloomFilterIntegerExample {
    public static void main(String[] args) {

        BloomFilter<byte[]> filter = BloomFilter.create(Funnels.byteArrayFunnel(),
                10000000,0.01);
        for (int i=0;i<10000000;i++){
            filter.put(int2Bytes(i));
        }

        System.out.println(filter.mightContain(int2Bytes(1)));
        System.out.println(filter.mightContain(int2Bytes(100)));
        System.out.println(filter.mightContain(int2Bytes(300)));
        System.out.println(filter.mightContain(int2Bytes(940000000)));
    }

    private static byte[] int2Bytes(int num){
        return ByteBuffer.allocate(4).putInt(num).array();
    }
}
