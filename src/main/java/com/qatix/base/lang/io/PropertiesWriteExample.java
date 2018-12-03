package com.qatix.base.lang.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/3 9:33 AM
 */
public class PropertiesWriteExample {
    public static void main(String[] args) {
        File file = new File("config_mail.properties");

        Properties prop = null;
        FileOutputStream fileOutputStream = null;
        try {
            prop = new Properties();
            fileOutputStream = new FileOutputStream(file);

            // Add key-value elements to properties list
            prop.setProperty("mail.from", "abcd@boraji.com");
            prop.setProperty("mail.to", "xyz@boraji.com");
            prop.setProperty("mail.smtp", "10.10.10.10");
            prop.setProperty("mail.smtp.port", "250");

            // Write properties list to output stream
            prop.store(fileOutputStream, "This is an sample properties file.");
            System.out.println("write done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
