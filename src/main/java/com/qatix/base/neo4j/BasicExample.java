package com.qatix.base.neo4j;


import org.neo4j.driver.*;

/**
 * @see https://blog.csdn.net/hxg117/article/details/79929579
 */
public class BasicExample {

    public static void main(String[] args) {
        Driver driver = GraphDatabase.driver("bolt://127.0.0.1:7687",
                AuthTokens.basic("neo4j", "123456"));
        Session session = driver.session();

        session.run("CREATE (:Message {title: 'Hello', text: 'hello Neo4j'})");
        session.run("CREATE (:Language {name: 'Java8', version: '8'})");

        //row2
//        session.run("CREATE (:Message {title: 'Qatix', text: 'hello Qatix'})");

        String createRelationship = "MATCH (m:Message),(l:Language) WHERE m.title = 'Hello' AND l.name='Java8'"
                + " CREATE (m)-[:HelloJava8]->(l);";
        session.run(createRelationship);

        String queryRelationship = "MATCH (m:Message)-[:HelloJava8]->" + "(l:Language {name:{language}})"
                + "RETURN m.title,l.name;";
        Result resultSet = session.run(queryRelationship, Values.parameters("language", "Java8"));

        while (resultSet.hasNext()) {
            Record result = resultSet.next();
            System.out.println(result.get("m.title") + " from " + result.get("l.name"));
        }

        session.close();
    }
}
