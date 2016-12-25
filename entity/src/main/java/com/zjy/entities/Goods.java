package com.zjy.entities;

import org.apache.solr.client.solrj.beans.Field;

import java.util.List;

/**
 * @author chahongjing
 * @create 2016-12-25 12:37
 */
public class Goods {
    @Field("id")
    private String id;
    @Field("name")
    private String name;
    @Field("title")
    private List<String> title;
    @Field("weight")
    private float weight;
    @Field("price")
    private float price;

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

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
