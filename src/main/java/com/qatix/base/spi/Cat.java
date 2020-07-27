package com.qatix.base.spi;

import com.qatix.base.spi.IShout;

public class Cat implements IShout {
    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}