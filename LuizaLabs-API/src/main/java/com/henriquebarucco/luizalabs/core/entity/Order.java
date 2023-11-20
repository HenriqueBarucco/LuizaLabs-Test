package com.henriquebarucco.luizalabs.core.entity;

import java.io.Serializable;
import java.text.DecimalFormat;
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

    private void calculateTotal() {
        Double total = products.stream().mapToDouble(Product::getValue).sum();
        DecimalFormat df = new DecimalFormat("#,##");

        this.total = Double.parseDouble(df.format(total));
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
