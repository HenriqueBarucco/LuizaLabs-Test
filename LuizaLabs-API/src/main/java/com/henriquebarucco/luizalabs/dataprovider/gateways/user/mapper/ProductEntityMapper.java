package com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper;

import com.henriquebarucco.luizalabs.core.entity.Product;
import com.henriquebarucco.luizalabs.dataprovider.persistence.product.ProductEntity;

public class ProductEntityMapper {

    public ProductEntity toEntity(Product productDomainObj) {
        return new ProductEntity(
                productDomainObj.getId(),
                productDomainObj.getValue()
        );
    }

    public Product toDomain(ProductEntity productEntity) {
        return new Product(
                productEntity.getProductId(),
                productEntity.getProductValue()
        );
    }
}
