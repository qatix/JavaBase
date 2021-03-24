package com.qatix.solr.pojo;

import org.apache.solr.client.solrj.beans.Field;

public class Foo {

    @Field("id")
    private String id;

    @Field("name")
    private String name;

    @Field("manu")
    private String manu;

    @Field("price")
    private Float price;

    @Field("inStock")
    private Boolean inStock;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManu() {
        return manu;
    }

    public void setManu(String manu) {
        this.manu = manu;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Boolean getInStock() {
        return inStock;
    }

    public void setInStock(Boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "Foo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", manu='" + manu + '\'' +
                ", price=" + price +
                ", inStock=" + inStock +
                '}';
    }
}