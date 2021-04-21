package com.qatix.github;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    public static void main(String[] args) throws IOException {
        System.out.println(GitConfig.getToken());
    }
}
