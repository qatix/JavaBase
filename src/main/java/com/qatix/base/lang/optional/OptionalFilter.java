package com.qatix.base.lang.optional;

import java.util.Optional;

public class OptionalFilter {
    public static void main(String[] args) {
        User user = new User( "1234","anna@gmail.com");
        Optional<User> result = Optional.ofNullable(user)
                .filter(u -> u.getEmail() != null && u.getEmail().contains("@"));
        System.out.println(result);
    }
}
