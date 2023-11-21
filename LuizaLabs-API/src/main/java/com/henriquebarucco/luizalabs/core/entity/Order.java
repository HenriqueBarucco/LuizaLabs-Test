package com.henriquebarucco.luizalabs.core.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Order implements Serializable {
    private Long id;
    private Double total;
    private LocalDate date;
    private List<Product> products;

    public Order() {
    }

    public Order(Long id, LocalDate date, List<Product> products) {
        this.id = id;
        this.date = date;
        this.products = products;

        this.calculateTotal();
    }

    public Order(Long id, LocalDate date) {
        this.id = id;
        this.date = date;
    }

    private void calculateTotal() {
        this.total = products.stream().mapToDouble(Product::getValue).sum();
    }

    public Long getId() {
        return id;
    }

    public Double getTotal() {
        return total;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Product> getProducts() {
        return products;
    }
}
