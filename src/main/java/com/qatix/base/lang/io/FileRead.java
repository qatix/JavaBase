package com.qatix.base.lang.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {
    public static void main(String[] args) throws IOException {
        String fileName = "logs/test-user.json";
        FileReader fr = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fr);

        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            line = br.readLine();
            System.out.println(line);
        }
        br.close();
        fr.close();
    }
}
