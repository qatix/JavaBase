package com.qatix.base.lang.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/3 9:35 AM
 */
public class PropertiesReadExample {
    public static void main(String[] args) {
        File file = new File("config_mail.properties");

        Properties prop = null;
        FileInputStream fileInputStream = null;
        try {
            prop = new Properties();
            fileInputStream = new FileInputStream(file);

            // Load property list
            prop.load(fileInputStream);

            // Print property list
            System.out.println("mail.from=" + prop.getProperty("mail.from"));
            System.out.println("mail.to=" + prop.getProperty("mail.to"));
            System.out.println("mail.smtp=" + prop.getProperty("mail.smtp"));
            System.out.println("mail.smtp.port=" + prop.getProperty("mail.smtp.port"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
