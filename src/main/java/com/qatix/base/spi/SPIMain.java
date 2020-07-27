package com.qatix.base.spi;

import java.util.ServiceLoader;

/**
 * 参考 https://www.jianshu.com/p/46b42f7f593c
 */
public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout s : shouts) {
            s.shout();
        }
    }
}
