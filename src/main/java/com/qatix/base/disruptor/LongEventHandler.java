package com.qatix.base.disruptor;

import com.lmax.disruptor.EventHandler;

public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("MyEvent:" + longEvent + ",sequence=" + sequence + ",b=" + endOfBatch);
    }
}
