package com.henriquebarucco.luizalabs.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private Long id;
    private String name;
    private List<Order> orders;

    public User() {
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
        this.orders = new ArrayList<>();
    }

    public User(Long id, String name, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
