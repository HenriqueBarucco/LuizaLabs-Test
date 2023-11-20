package com.henriquebarucco.luizalabs.dataprovider.persistence.order;

import com.henriquebarucco.luizalabs.dataprovider.persistence.product.ProductEntity;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity(name = "orders")
public class OrderEntity {

    @Id
    private Long id;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ProductEntity> products;

    public OrderEntity(Long id, LocalDate date, List<ProductEntity> products) {
        this.id = id;
        this.date = date;
        this.products = products;
    }

    public OrderEntity(Long id, LocalDate date, UserEntity user) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.products = new ArrayList<>();
    }

    public void addProduct(ProductEntity product) {
        this.products.add(product);
    }
}
