package com.qatix.groovy.jva;

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoadYamlTest {
    public static void main(String[] args) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        Object object = yaml.load(new FileInputStream(
                new File("src/main/java/com/qatix/groovy/jva/user.yaml")));
        System.out.println(object.toString());

        System.out.println(new Gson().toJson(object));
    }
}
