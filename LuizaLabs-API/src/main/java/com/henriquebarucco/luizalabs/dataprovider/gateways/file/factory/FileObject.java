package com.henriquebarucco.luizalabs.dataprovider.gateways.file.factory;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@EqualsAndHashCode
@Getter
public class FileObject {
    private Long userId;
    private String name;
    private Long orderId;
    private Long productId;
    private Double productValue;
    private LocalDate purchaseDate;

    public FileObject(Long userId, String name, Long orderId, Long productId, Double productValue, LocalDate purchaseDate) {
        this.userId = userId;
        this.name = name;
        this.orderId = orderId;
        this.productId = productId;
        this.productValue = productValue;
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "DataObject{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", productValue=" + productValue +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}