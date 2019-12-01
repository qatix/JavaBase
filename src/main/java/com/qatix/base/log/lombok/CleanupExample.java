package com.qatix.base.log.lombok;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * https://projectlombok.org/features/Cleanup
 *
 * @Author: Logan.Tang
 * @Date: 2018/10/29 5:57 PM
 */
@Slf4j
public class CleanupExample {
    public static void main(String[] args) throws IOException {
        log.info("args length:{}", args.length);

        @Cleanup InputStream in = new FileInputStream(args[0]);
        @Cleanup OutputStream out = new FileOutputStream(args[1]);

        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) {
                break;
            }
            out.write(b, 0, r);
        }
        log.info("done");
    }
}
