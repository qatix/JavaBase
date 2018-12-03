package com.qatix.base.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

/**
 * P6Spy is a framework that enables us to log all sql statements and parameters for java application.
 * <p>
 * By using P6Spy with Hibernate, you can log current execution time, total elapsed time, sql statement with bind variable, sql statement executed etc.
 *
 * @Author: Logan.Tang
 * @Date: 2018/12/2 10:03 AM
 * @see https://www.boraji.com/hibernate-5-p6spy-configuration-example
 */
public class SpyExample {
    public static void main(String[] args) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtilSpy.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Category categoryObj = new Category(88883, "Test-Spy", new Date(), new Date());
            session.save(categoryObj);

//            List<Long> totalCategory = session.createNamedQuery("get_total_category", Long.class).getResultList();
//            System.out.println("Total category:" + totalCategory.get(0));
//
//            List<String> categoryName = session.createNamedQuery("get_category_name_by_id", String.class)
//                    .setParameter("id", 1)
//                    .getResultList();
//            for (String name : categoryName) {
//                System.out.println("category-name:" + name);
//            }
//
//            List<Category> categories = session.createNamedQuery("get_all_categories", Category.class).getResultList();
//            for (Category category : categories) {
//                System.out.println(category.toString());
//            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        HibernateUtilSpy.shutdown();
    }
}
