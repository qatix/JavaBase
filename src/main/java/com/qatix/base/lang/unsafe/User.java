package com.qatix.base.lang.unsafe;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
final class User {
    private Integer id;
    private String name;
    private String role;
    private Integer age;
}
