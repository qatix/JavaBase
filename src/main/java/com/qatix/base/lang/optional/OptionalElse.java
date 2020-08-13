package com.qatix.base.lang.optional;

import java.util.Optional;

public class OptionalElse {
    public static void main(String[] args) {
        //对象为空
        User user = null;
        System.out.println("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        System.out.println("result:"+result.toString());
        System.out.println("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(OptionalElse::createNewUser);
        System.out.println("result2:"+result2.toString());


        //对象不为空
        User user2 = new User("john@gmail.com", "1234");
        System.out.println("Using orElse");
        //仍会创建新对象
        User result3 = Optional.ofNullable(user2).orElse(createNewUser());
        System.out.println("result3:"+result3.toString());
        System.out.println("Using orElseGet");
        //以下不会创建新对象
        User result4 = Optional.ofNullable(user2).orElseGet(() -> createNewUser());
        System.out.println("result4:"+result4.toString());
        /**
         * 两个 Optional  对象都包含非空值，两个方法都会返回对应的非空值。不过，orElse() 方法仍然创建了 User 对象。
         * 与之相反，orElseGet() 方法不创建 User 对象。
         * 在执行较密集的调用时，比如调用 Web 服务或数据查询，这个差异会对性能产生重大影响。
         */

    }

    private static User createNewUser() {
        System.out.println("Creating New User");
        return new User("1234", "extra@gmail.com");
    }
}
