package com.qatix.base.log.lombok;


import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AccessorsObject {
    private String name;
    private String type;
    private Integer status;

    @Accessors(prefix = "f")
    private String fNo;
}
