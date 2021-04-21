package com.qatix.github;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class GitConfig {
    public static final String ACCESS_TOKEN = "accessToken";
    private static Properties properties = new Properties();

    static {
        String fileName = ".secure/github.properties";
        try {
            properties.load(new FileInputStream(new File(fileName)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getToken() {
        if (properties != null) {
            return properties.getProperty(ACCESS_TOKEN);
        }
        return null;
    }
}
