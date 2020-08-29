package com.qatix.base.algorithm.simple;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Counting {
    public static void main(String[] args) throws IOException {
        test();
    }

    public static void test() throws IOException {
        Map<Long, Integer> resMap = new HashMap<>(1 << 20);
        String filename = "imgs/input.txt";
        File file = new File(filename);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        char[] line2 = new char[100];
//        br.read(line,0,100);
//        Scanner scanner = new Scanner(fr);
        while (line != null) {
            String[] parts = line.split("\\s+");
            Long userId = Long.parseLong(parts[2]);
            Integer val = resMap.get(userId);
            if (val == null) {
                val = 0;
            }
            val += 1;
            resMap.put(userId, val);
            line = br.readLine();
        }
        for (Map.Entry<Long, Integer> entry : resMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
        br.close();
        fr.close();
    }
}
