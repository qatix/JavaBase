package com.qatix.base.hibernate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Logan.Tang
 * @Date: 2018/12/2 9:53 AM
 */
@Entity
@Table(name = "category")
@NamedQuery(name = "get_total_category", query = "select count(1) from Category")
//using @NameQueris for multiple JPQL or HQL
@NamedQueries({
        @NamedQuery(name = "get_category_name_by_id", query = "select name from Category where id=:id"),
        @NamedQuery(name = "get_all_categories", query = "from Category")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    //这里不能加，否则出现死循环
//    @OneToMany(targetEntity = Product.class,mappedBy = "category")
//    private List<Product> products = new ArrayList<>();

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
