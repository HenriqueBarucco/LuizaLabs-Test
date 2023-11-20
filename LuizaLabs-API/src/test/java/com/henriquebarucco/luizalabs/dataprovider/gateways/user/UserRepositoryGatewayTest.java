package com.henriquebarucco.luizalabs.dataprovider.gateways.user;

import com.henriquebarucco.luizalabs.core.gateways.UserGateway;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper.UserEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderRepository;
import com.henriquebarucco.luizalabs.dataprovider.persistence.product.ProductRepository;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryGatewayTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Test
    public void testCreateUser() {
        UserGateway userGateway = new UserRepositoryGateway(userRepository, orderRepository, productRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        User createdUser = userGateway.createUser(user);

        assertEquals(user.getId(), createdUser.getId());
    }

    @Test
    public void testGetUserIfExist() {
        UserGateway userGateway = new UserRepositoryGateway(userRepository, orderRepository, productRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        User createdUser = userGateway.createUser(user);

        User foundUser = userGateway.getUser(createdUser.getId(), createdUser.getName());

        assertEquals(user.getId(), foundUser.getId());
    }

    @Test
    public void testGetUserIfNotExist() {
        UserGateway userGateway = new UserRepositoryGateway(userRepository, orderRepository, productRepository, userEntityMapper);

        User user = userGateway.getUser(1L, "Henrique");

        assertEquals(user.getId(), 1L);
    }
}