package com.qatix.base.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/2 7:48 PM
 * @see https://www.boraji.com/hibernate-5-batch-processing-example
 */
public class BatchInsertExample {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        int batchSize = 50;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            for (int i = 0; i < 10000; i++) {
                Category category = new Category(i + 10, "Cate-" + i, new Date(), new Date());
                session.save(category);
                if (i > 0 && i % batchSize == 0) {
                    session.flush();
                    session.clear();
                    System.out.println("insert " + i);
                }
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
