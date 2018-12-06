package com.qatix.base.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/2 10:03 AM
 * @see https://www.boraji.com/hibernate-5-interceptor-example
 */
public class InterceptorExample {
    public static void main(String[] args) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory()
                    .withOptions()
                    .interceptor(new LoggingInterceptor())//add interceptor to session
                    .openSession();
            transaction = session.beginTransaction();

            Category categoryObj = new Category(99999, "Test-Spy", new Date(), new Date());
            session.delete(categoryObj);

            System.out.println("delete the save:");
            session.save(categoryObj);

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        HibernateUtil.shutdown();
    }
}
