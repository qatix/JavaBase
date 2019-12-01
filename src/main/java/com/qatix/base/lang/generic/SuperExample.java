package com.qatix.base.lang.generic;

/**
 * @see https://www.cnblogs.com/lwbqqyumidi/p/3837629.html
 */
public class SuperExample {
    public static void main(String[] args) {
        Level1 l1 = new Level1("level1");
        Box<Level1> bl1 = new Box<>(l1);

        Level2 l2 = new Level2("level2");
        Box<Level2> bl2 = new Box<>(l2);

        Level31 l31 = new Level31("level31");
        Box<Level31> bl31 = new Box<>(l31);

        Level32 l32 = new Level32("level32");
        Box<Level32> bl32 = new Box<>(l32);


        printData1(bl1);
        printData1(bl2);

//        printData1(bl31); compile error
//        printData1(bl32); compile error

        printData2(bl1);
        printData2(bl31);
    }

    private static void printData1(Box<? super Level2> data) {
        System.out.println("super data:" + data.getData());
    }

    private static void printData2(Box<?> data) {
        System.out.println("generic data:" + data.getData());
    }

    static class Level1 {
        private String name;

        public Level1(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Level{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    static class Level2 extends Level1 {
        public Level2(String name) {
            super(name);
        }
    }

    static class Level31 extends Level2 {
        public Level31(String name) {
            super(name);
        }
    }

    static class Level32 extends Level2 {
        public Level32(String name) {
            super(name);
        }
    }
}
