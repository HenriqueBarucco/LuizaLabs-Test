package com.henriquebarucco.luizalabs.dataprovider.gateways.order.mapper;

import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper.ProductEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderEntity;

public class OrderEntityMapper {
    private final ProductEntityMapper productEntityMapper;

    public OrderEntityMapper(ProductEntityMapper productEntityMapper) {
        this.productEntityMapper = productEntityMapper;
    }

    public OrderEntity toEntity(Order orderDomainObj) {
        return new OrderEntity(
                orderDomainObj.getId(),
                orderDomainObj.getDate(),
                orderDomainObj.getProducts().stream().map(productEntityMapper::toEntity).toList()
        );
    }

    public Order toDomain(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                orderEntity.getDate(),
                orderEntity.getProducts().stream().map(productEntityMapper::toDomain).toList()
        );
    }
}
