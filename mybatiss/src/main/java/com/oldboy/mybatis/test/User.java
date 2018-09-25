package com.oldboy.mybatis.test;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Integer id;
    private String name;
    private int age;
    private List<Order> order=new ArrayList<Order>();

    public List<Order> getOrder() {
        return order;
    }

    public void setOrder(List<Order> order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
