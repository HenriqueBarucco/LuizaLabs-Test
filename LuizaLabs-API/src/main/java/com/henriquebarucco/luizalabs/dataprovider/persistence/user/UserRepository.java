package com.henriquebarucco.luizalabs.dataprovider.persistence.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT DISTINCT u FROM users u JOIN FETCH u.orders o WHERE o.date BETWEEN :startDate AND :endDate")
    List<UserEntity> findAllInRange(LocalDate startDate, LocalDate endDate);
}
