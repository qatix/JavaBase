package com.qatix.base.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/2 10:47 PM
 * @see https://www.boraji.com/hibernate-5-native-sql-query-example
 */
public class NativeQueryExample {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            //way 1
            List<Object[]> categories = session.createNativeQuery("select id,name from Category").list();
            for (Object[] objects : categories) {
                Integer id = (Integer) objects[0];
                String name = (String) objects[1];
                System.out.println("[" + id + "," + name + "]");
            }

            List<Object[]> products = session.createNativeQuery("select p.id,p.name as pName,c.name as cName from product p left join category c ON(p.category_id=c.id)").list();
            for (Object[] objects : products) {
                Integer id = (Integer) objects[0];
                String name = (String) objects[1];
                String categoryName = (String) objects[2];
                System.out.println("[" + id + "," + name + "," + categoryName + "]");
            }

            //way 2
            List<Product> products1 = session.createNativeQuery("select * from product", Product.class).list();
            for (Product product : products1) {
                System.out.println(product.toString());
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        HibernateUtil.shutdown();
    }
}
