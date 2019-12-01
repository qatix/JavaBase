package com.qatix.base.hibernate;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/2 9:53 AM
 */
@Entity
@Table(name = "product")
//Product must UpperCase in first Letter,product is wrong
@NamedQuery(name = "get_total_product", query = "select count(1) from Product")
//using @NameQueris for multiple JPQL or HQL
@NamedQueries({
        @NamedQuery(name = "get_product_name_by_id", query = "select name from Product where id=:id"),
        @NamedQuery(name = "get_all_products", query = "from Product")
})
@Data
public class Product {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @Column(name = "no")
    private String no;
    /**
     * 必须是BigDecimal，否则会报错
     */
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "status", columnDefinition = "tinyint")
    private int status;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
