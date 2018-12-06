package com.qatix.base.hibernate;

import org.hibernate.*;

import java.util.Date;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/2 7:48 PM
 * @see https://www.boraji.com/hibernate-5-batch-processing-example
 */
public class BatchUpdateExample {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        ScrollableResults scrollableResults = null;
        int batchSize = 50;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();


            scrollableResults = session.createQuery("from Category where id > 9800")
                    .setCacheMode(CacheMode.IGNORE)
                    .scroll(ScrollMode.FORWARD_ONLY);

            int count = 0;
            while (scrollableResults.next()) {
                Category category = (Category) scrollableResults.get(0);
                category.setName("Category-" + category.getId());
                category.setUpdateTime(new Date());
                if (++count % batchSize == 0) {
                    session.flush();
                    session.clear();
                }
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (scrollableResults != null) {
                scrollableResults.close();
            }

            if (session != null) {
                session.close();
            }
        }

        HibernateUtil.shutdown();
    }
}
