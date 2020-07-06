package com.qatix.base.neo4j;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.neo4j.driver.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Neo4jDriver {
    private Driver driver;
    private static String NEO4J_CONFIG_PATH = "src/main/resources/neo4j.properties";

    public Neo4jDriver() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(NEO4J_CONFIG_PATH));
        } catch (IOException e) {
            System.err.println("can not find config file for neo4j.");
        }
        driver = GraphDatabase.driver(properties.getProperty("uri"), AuthTokens.basic(properties.getProperty("user"), properties.getProperty("passwd")));
    }

    /**
     * run create statements
     *
     * @param txt
     * @return
     */
    public Result exeStatement(String txt) {
        Session session = driver.session();
        Transaction transaction = session.beginTransaction();
        Result result = transaction.run(txt);
//        transaction.commit();
//        session.close();
        return result;

    }

    /**
     * explicitly call this method to stop the driver instance
     */
    public void stop() {
        driver.closeAsync();
    }

    public static void main(String[] args) {
        Neo4jDriver driver = new Neo4jDriver();
        Result result = driver.exeStatement("MATCH (m:Message)-[:HelloJava8]-> (l:Language {name:\"Java8\"}) RETURN m.title,l.name");
        while (result.hasNext()) {
            System.out.println(result.next());
        }
        driver.stop();
    }
}
