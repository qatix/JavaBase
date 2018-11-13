package com.qatix.base.spring.scope;

/**
 * @Author: Logan.Tang
 * @Date: 2018/11/13 9:36 PM
 */
public class ProductService {

    public ProductService() {
        System.out.println("ProductService Constructing...");
    }

    private String productHolder;

    public String getProductHolder() {
        return productHolder;
    }

    public void setProductHolder(String productHolder) {
        this.productHolder = productHolder;
    }
}
