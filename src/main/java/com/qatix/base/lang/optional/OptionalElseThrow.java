package com.qatix.base.lang.optional;

import java.util.Optional;

public class OptionalElseThrow {
    public static void main(String[] args) {
        User user = null;
        User result = Optional.ofNullable(user)
                .orElseThrow( () -> new IllegalArgumentException("user is null"));
        System.out.println(result);
    }
}
