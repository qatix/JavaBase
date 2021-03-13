package com.qatix.base.redis.jedis;

import java.io.IOException;
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) throws IOException {


        while (true) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();

            char pre = '\0';
            int count = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (ch != pre) {
                    if (pre != '\0') {
                        sb.append(pre);
                        if (count > 1) {
                            sb.append(String.valueOf(count));
                        }
                    }

                    pre = ch;
                    count = 0;
                }
                count++;
            }

            if (pre != '\0') {
                sb.append(pre);
                if (count > 1) {
                    sb.append(String.valueOf(count));
                }
            }

            System.out.println(sb.toString());
        }

    }
}
