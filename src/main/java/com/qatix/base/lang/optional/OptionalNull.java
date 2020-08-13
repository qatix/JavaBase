package com.qatix.base.lang.optional;

import java.util.Optional;

public class OptionalNull {
    public static void main(String[] args) {
        User user = null;
        //ofNullable对象可为null
        User optionalUser = Optional.ofNullable(user)
                .orElse(new User("default","default@qq.com"));
        System.out.println(optionalUser.toString());

        //of()对象不能为null
        User optionalUser2 = Optional.of(optionalUser).orElse(new User("default2","default@qq.com"));
        System.out.println(optionalUser2.toString());
    }

}
