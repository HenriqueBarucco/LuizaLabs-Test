package com.henriquebarucco.luizalabs.core.entity;

import java.io.Serializable;

public class Product implements Serializable {
    private Long id;
    private Double value;

    public Product() {
    }

    public Product(Long id, Double value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public Double getValue() {
        return value;
    }
}
