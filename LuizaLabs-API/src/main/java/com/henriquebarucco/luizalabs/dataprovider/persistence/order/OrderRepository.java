package com.henriquebarucco.luizalabs.dataprovider.persistence.order;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends CrudRepository<OrderEntity, Long> {

    @Query("SELECT o FROM orders o WHERE o.date BETWEEN :startDate AND :endDate")
    List<OrderEntity> findAllInRange(LocalDate startDate, LocalDate endDate);
}
