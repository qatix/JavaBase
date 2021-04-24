package com.qatix.groovy.jva;

import com.google.gson.Gson;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.representer.Representer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoadYamlIngoreMissingProperties {
    public static void main(String[] args) throws FileNotFoundException {
        //skip missing properties
        Representer representer = new Representer();
        representer.getPropertyUtils().setSkipMissingProperties(true);
        Yaml yaml = new Yaml(representer);
        User gUser = yaml.loadAs(new FileInputStream(
                        new File("src/main/java/com/qatix/groovy/jva/user.yaml")),
                User.class
        );

        System.out.println(gUser);
        System.out.println(gUser.toString());
        System.out.println(new Gson().toJson(gUser));
        System.out.println(gUser.getName());
    }
}
