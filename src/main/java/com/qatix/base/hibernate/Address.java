package com.qatix.base.hibernate;

import com.qatix.base.hibernate.enums.AddressType;
import com.qatix.base.hibernate.enums.PhoneType;
import lombok.Data;

import javax.persistence.*;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/2 10:24 PM
 */
@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    private long id;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type")
    private AddressType addressType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "phoneType")
    private PhoneType phoneType;
}
