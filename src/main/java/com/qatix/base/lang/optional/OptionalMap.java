package com.qatix.base.lang.optional;

import java.util.Optional;

public class OptionalMap {
    public static void main(String[] args) {
        User user = new User("1234","anna@gmail.com");
        String email = Optional.ofNullable(user)
                .map(u -> u.getEmail()).orElse("default@gmail.com");

        System.out.println(email);
    }
}
