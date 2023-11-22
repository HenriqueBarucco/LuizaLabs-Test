package com.henriquebarucco.luizalabs.dataprovider.gateways.user;

import com.henriquebarucco.luizalabs.core.exceptions.ResourceNotFoundException;
import com.henriquebarucco.luizalabs.core.gateways.UserGateway;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper.UserEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryGatewayTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Test
    public void testCreateUser() {
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        User createdUser = userGateway.createUser(user);

        assertEquals(user.getId(), createdUser.getId());
    }

    @Test
    public void testGetUserIfExist() {
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        User createdUser = userGateway.createUser(user);

        User foundUser = userGateway.getUser(createdUser.getId(), createdUser.getName());

        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
    public void testGetUserIfNotExist() {
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = userGateway.getUser(1L, "Henrique");

        assertEquals(user.getId(), 1L);
    }

    @Test
    public void shouldReturnUserById() {
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        userGateway.createUser(user);

        User userResponse = userGateway.getUserById(1L);

        assertEquals(userResponse.getId(), user.getId());
    }

    @Test
    public void shouldThrowExceptionWhenUserByIdItsNotFound() {
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        assertThrows(ResourceNotFoundException.class, () -> userGateway.getUserById(1L));
    }

    @Test
    public void testListAllUsers() {
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        userGateway.createUser(user);

        assertEquals(1, userGateway.listAllUsers().size());
    }

    @Test
    public void testListAllOrdersByDate() {
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");

        userGateway.createUser(user);

        List<User> allUsers = userGateway.listAllUsersByDate(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 1));

        assertEquals(0, allUsers.size());
    }
}