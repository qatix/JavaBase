package com.qatix.base.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/2 10:03 AM
 */
public class NamedQueryExample {
    public static void main(String[] args) {

        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            List<Long> totalProduct = session.createNamedQuery("get_total_product", Long.class).getResultList();
            System.out.println("Total product:" + totalProduct.get(0));

            List<String> productName = session.createNamedQuery("get_product_name_by_id", String.class)
                    .setParameter("id", 1)
                    .getResultList();
            for (String name : productName) {
                System.out.println("product-name:" + name);
            }

            List<Product> products = session.createNamedQuery("get_all_products", Product.class).getResultList();
            for (Product product : products) {
                System.out.println(product.toString());
            }

            List<Long> totalCategory = session.createNamedQuery("get_total_category", Long.class).getResultList();
            System.out.println("Total category:" + totalCategory.get(0));

            List<String> categoryName = session.createNamedQuery("get_category_name_by_id", String.class)
                    .setParameter("id", 1)
                    .getResultList();
            for (String name : categoryName) {
                System.out.println("category-name:" + name);
            }

            List<Category> categories = session.createNamedQuery("get_all_categories", Category.class).getResultList();
            for (Category category : categories) {
                System.out.println(category.toString());
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
