package com.qatix.base.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class EmptyClass {

    public static void main(String[] args) throws Exception {
        System.out.println(VM.current().details());

        final A a = new A();

        ClassLayout layout = ClassLayout.parseInstance(a);
        System.out.println(layout.toPrintable());
    }

    public static class A {
        // no fields
    }
}
/*
 * # WARNING: Unable to attach Serviceability Agent. You can try again with escalated privileges. Two options: a) use -Djol.tryWithSudo=true to try with sudo; b) echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope
 # Running 64-bit HotSpot VM.
 # Using compressed oop with 3-bit shift.
 # Using compressed klass with 3-bit shift.
 # WARNING | Compressed references base/shifts are guessed by the experiment!
 # WARNING | Therefore, computed addresses are just guesses, and ARE NOT RELIABLE.
 # WARNING | Make sure to attach Serviceability Agent to get the reliable addresses.
 # Objects are 8 bytes aligned.
 # Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
 # Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]

 com.qatix.base.jol.EmptyClass$A object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
 0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
 4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
 8     4        (object header)                           35 f0 00 f8 (00110101 11110000 00000000 11111000) (-134156235)
 12     4        (loss due to the next object alignment)
 Instance size: 16 bytes
 Space losses: 0 bytes internal + 4 bytes external = 4 bytes total


 Process finished with exit code 0
*/