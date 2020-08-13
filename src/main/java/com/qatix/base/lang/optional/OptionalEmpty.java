package com.qatix.base.lang.optional;

import java.util.Optional;

public class OptionalEmpty {
    public static void main(String[] args) {
        Optional<User> emptyOpt = Optional.empty();
        System.out.println("present:" + emptyOpt.isPresent());
        User user = emptyOpt.get();
        System.out.println(user);
    }
}
