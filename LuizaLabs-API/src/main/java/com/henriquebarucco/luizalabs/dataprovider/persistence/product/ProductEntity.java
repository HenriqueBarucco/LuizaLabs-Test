package com.henriquebarucco.luizalabs.dataprovider.persistence.product;

import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    private Long productId;

    private Double productValue;

    public ProductEntity(Long productId, Double productValue) {
        this.productId = productId;
        this.productValue = productValue;
    }

    public ProductEntity(Long productId, Double productValue, OrderEntity order) {
        this.productId = productId;
        this.productValue = productValue;
        this.order = order;
    }
}
