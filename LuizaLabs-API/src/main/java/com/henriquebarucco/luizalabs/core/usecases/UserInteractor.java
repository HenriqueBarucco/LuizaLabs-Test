package com.henriquebarucco.luizalabs.core.usecases;

import com.henriquebarucco.luizalabs.core.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface UserInteractor {

    User createUser(User user);

    User getUser(Long userId, String name);

    void addOrderToUser(User user, Long orderId, LocalDate purchaseDate, Long productId, Double productValue);

    List<User> listAllUsers();

    List<User> listAllUsersByDate(LocalDate startDate, LocalDate endDate);

    User getUserById(Long userId);
}
