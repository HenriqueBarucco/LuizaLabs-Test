package com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper;

import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.dataprovider.gateways.order.mapper.OrderEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserEntity;

public class UserEntityMapper {
    private final OrderEntityMapper orderEntityMapper;

    public UserEntityMapper(OrderEntityMapper orderEntityMapper) {
        this.orderEntityMapper = orderEntityMapper;
    }

    public UserEntity toEntity(User userDomainObj) {
        return new UserEntity(
                userDomainObj.getId(),
                userDomainObj.getName(),
                userDomainObj.getOrders().stream().map(orderEntityMapper::toEntity).toList()
        );
    }

    public User toDomain(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getOrders().stream().map(orderEntityMapper::toDomain).toList()
        );
    }
}
