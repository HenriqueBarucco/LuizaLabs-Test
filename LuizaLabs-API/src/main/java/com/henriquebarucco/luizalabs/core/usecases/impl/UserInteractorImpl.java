package com.henriquebarucco.luizalabs.core.usecases.impl;

import com.henriquebarucco.luizalabs.core.gateways.UserGateway;
import com.henriquebarucco.luizalabs.core.usecases.UserInteractor;
import com.henriquebarucco.luizalabs.core.entity.User;

import java.time.LocalDate;
import java.util.List;

public class UserInteractorImpl implements UserInteractor {
    private final UserGateway userGateway;

    public UserInteractorImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public User createUser(User user) {
        return  userGateway.createUser(user);
    }

    @Override
    public User getUser(Long userId, String name) {
        return userGateway.getUser(userId, name);
    }

    @Override
    public List<User> listAllUsers() {
        return userGateway.listAllUsers();
    }

    @Override
    public List<User> listAllUsersByDate(LocalDate startDate, LocalDate endDate) {
        return userGateway.listAllUsersByDate(startDate, endDate);
    }

    @Override
    public User getUserById(Long userId) {
        return userGateway.getUserById(userId);
    }
}
