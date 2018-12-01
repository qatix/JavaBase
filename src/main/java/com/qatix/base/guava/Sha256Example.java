package com.qatix.base.guava;

import com.google.common.hash.Hashing;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/30 6:07 PM
 */
public class Sha256Example {

    /**
     * use guava
     * @param fileName
     * @return
     */
    public static String getSha256(String fileName) {
        try {
            File file = new File(fileName);
            System.out.println(file.toPath());
            byte[] fileContent = Files.readAllBytes(file.toPath());

            return Hashing.sha256()
                    .hashBytes(fileContent)
                    .toString();
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * use common codec
     * @param fileName
     * @return
     */
    public static String getSha256_2(String fileName) {
        try {
            File file = new File(fileName);
            System.out.println(file.toPath());
            byte[] fileContent = Files.readAllBytes(file.toPath());

            return DigestUtils.sha256Hex(fileContent);
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String file = "xxx";
        System.out.println(getSha256(file));
    }
}
