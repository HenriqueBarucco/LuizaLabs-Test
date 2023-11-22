package com.henriquebarucco.luizalabs.core.usecases.impl;

import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.core.gateways.UserGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class UserInteractorImplTest {

    @InjectMocks
    private UserInteractorImpl userInteractorImpl;

    @Mock
    private UserGateway userGateway;

    @Mock
    private User user;

    @Test
    public void testShouldGetUser() {
        userInteractorImpl.getUser(1L, "Henrique");

        then(userGateway).should().getUser(1L, "Henrique");
    }

    @Test
    public void testShouldGetUserById() {
        userInteractorImpl.getUserById(1L);

        then(userGateway).should().getUserById(1L);
    }

    @Test
    public void testShouldGetUserByDate() {
        userInteractorImpl.listAllUsersByDate(LocalDate.now(), LocalDate.now());

        then(userGateway).should().listAllUsersByDate(LocalDate.now(), LocalDate.now());
    }

    @Test
    public void testShouldListAllUsers() {
        userInteractorImpl.listAllUsers();

        then(userGateway).should().listAllUsers();
    }

    @Test
    public void testShouldCreateUser() {
        userInteractorImpl.createUser(user);

        then(userGateway).should().createUser(user);
    }
}