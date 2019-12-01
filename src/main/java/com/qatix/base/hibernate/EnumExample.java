package com.qatix.base.hibernate;

import com.qatix.base.hibernate.enums.AddressType;
import com.qatix.base.hibernate.enums.PhoneType;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/2 10:28 PM
 */
public class EnumExample {
    public static void main(String[] args) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            Address address = new Address();
            address.setId(1L);
            address.setCity("Huangshan");
            address.setState("Anhui");
            address.setAddressType(AddressType.PERMANENT);
            address.setPhoneType(PhoneType.LANDLINE);

            session.save(address);
            transaction.commit();

            System.out.println("Address is saved");
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Transaction is being rollback");
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
