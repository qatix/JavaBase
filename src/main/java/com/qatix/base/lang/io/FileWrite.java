package com.qatix.base.lang.io;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWrite {
    public static void main(String[] args) throws IOException {
        String fileName = "logs/test-user.json";
        File configFile = new File(fileName);
        FileWriter fw = new FileWriter(configFile);
        BufferedWriter bw = new BufferedWriter(fw);
        for (int i = 0; i < 100; i++) {
            JSONObject jo = new JSONObject();
            jo.put("name", "test-" + i);
            jo.put("age", RandomUtils.nextInt(10, 30));
            jo.put("sex", RandomUtils.nextInt(0, 2));
            jo.put("timestamp", System.currentTimeMillis());
            bw.write(JSON.toJSONString(jo));
            bw.newLine();
        }
        bw.close();
        fw.close();
        System.out.println("done");
    }
}
