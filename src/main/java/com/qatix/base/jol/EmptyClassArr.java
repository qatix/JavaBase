package com.qatix.base.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class EmptyClassArr {
    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        A[] as = new A[2];
        System.out.println(ClassLayout.parseInstance(new A[2]).toPrintable());
    }

    public static class A {
        // no fields
    }
}
/*
 [Lcom.qatix.base.jol.EmptyClassArr$A; object internals:
 OFFSET  SIZE                                 TYPE DESCRIPTION                               VALUE
      0     4                                      (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4                                      (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4                                      (object header)                           73 f0 00 f8 (01110011 11110000 00000000 11111000) (-134156173)
     12     4                                      (object header)                           02 00 00 00 (00000010 00000000 00000000 00000000) (2)
     解释：上面是数组size
     16     8   com.qatix.base.jol.EmptyClassArr$A EmptyClassArr$A;.<elements>               N/A
     解释: 上面是数组元素
Instance size: 24 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
 */
