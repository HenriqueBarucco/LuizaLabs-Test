package com.henriquebarucco.luizalabs.dataprovider.persistence.user;

import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity(name = "users")
public class UserEntity {

    @Id
    private Long id;
    private String name;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderEntity> orders;

    public UserEntity(Long id, String name, List<OrderEntity> orders) {
        this.id = id;
        this.name = name;
        this.orders = orders;
    }

    public UserEntity(Long id, String name) {
        this.id = id;
        this.name = name;
        this.orders = new ArrayList<>();
    }
}
