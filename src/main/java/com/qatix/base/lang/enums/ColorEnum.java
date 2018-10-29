package com.qatix.base.lang.enums;

public enum ColorEnum {
    RED("红色",1),
    GREEN("绿色",2),
    BLACK("黑色",3),
    YELLOW("黄色",4);

    private String name;
    private int index;

    /**
     * 只能由编译器调用
     * @param name
     * @param index
     */
    ColorEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public String toString() {
        return "ColorEnum{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }
}
