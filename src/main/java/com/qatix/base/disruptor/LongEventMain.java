package com.qatix.base.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;

public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        LongEventFactory factory = new LongEventFactory();
        int bufferSize = 16;

        Disruptor<LongEvent> disruptor =
                 new Disruptor<LongEvent>(factory,bufferSize, DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith(new LongEventHandler());

        disruptor.start();

        RingBuffer<LongEvent> ringBuffer =
                disruptor.getRingBuffer();
        LongEventProducer producer =
                new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for(long l=0;true;l++){
            bb.putLong(0,l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }
}
