package com.henriquebarucco.luizalabs.dataprovider.gateways.order;

import com.henriquebarucco.luizalabs.core.entity.Order;
import com.henriquebarucco.luizalabs.core.entity.Product;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.core.exceptions.ResourceNotFoundException;
import com.henriquebarucco.luizalabs.core.gateways.OrderGateway;
import com.henriquebarucco.luizalabs.core.gateways.UserGateway;
import com.henriquebarucco.luizalabs.dataprovider.gateways.order.mapper.OrderEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.gateways.user.UserRepositoryGateway;
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

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class OrderRepositoryGatewayTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderEntityMapper orderEntityMapper;

    @Autowired
    private UserEntityMapper userEntityMapper;

    @Test
    public void testCreateOrderWithUserThatDoesntExist() {
        OrderGateway orderGateway = new OrderRepositoryGateway(orderRepository, userRepository, productRepository, orderEntityMapper);

        User user = new User(1L, "Henrique");
        Order order = new Order(1L, LocalDate.now());



        assertThrows(ResourceNotFoundException.class, () -> orderGateway.createOrder(user, order));
    }

    @Test
    public void testCreateOrder() {
        OrderGateway orderGateway = new OrderRepositoryGateway(orderRepository, userRepository, productRepository, orderEntityMapper);
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        userGateway.createUser(user);

        Order order = new Order(1L, LocalDate.now());
        Order createdOrder = orderGateway.createOrder(user, order);


        assertEquals(order.getId(), createdOrder.getId());
    }

    @Test
    public void testAddProductToOrder() {
        OrderGateway orderGateway = new OrderRepositoryGateway(orderRepository, userRepository, productRepository, orderEntityMapper);
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        userGateway.createUser(user);

        Order order = new Order(1L, LocalDate.now());
        Order createdOrder = orderGateway.createOrder(user, order);

        Product product = new Product(1L, 1.0);
        orderGateway.addProductToOrder(createdOrder, product);

        Order orderResponse = orderGateway.getOrderById(createdOrder.getId());

        assertTrue(orderResponse.getProducts().get(0).getId().equals(product.getId()) && orderResponse.getProducts().get(0).getValue().equals(product.getValue()));
    }

    @Test
    public void testAddProductToOrderThatDoesntExist() {
        OrderGateway orderGateway = new OrderRepositoryGateway(orderRepository, userRepository, productRepository, orderEntityMapper);

        Order order = new Order(1L, LocalDate.now());

        Product product = new Product(1L, 1.0);

        assertThrows(ResourceNotFoundException.class, () -> orderGateway.addProductToOrder(order, product));
    }

    @Test
    public void testListAllOrders() {
        OrderGateway orderGateway = new OrderRepositoryGateway(orderRepository, userRepository, productRepository, orderEntityMapper);
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        userGateway.createUser(user);

        Order order = new Order(1L, LocalDate.now());
        Order order2 = new Order(2L, LocalDate.now());

        orderGateway.createOrder(user, order);
        orderGateway.createOrder(user, order2);

        List<Order> allOrders = orderGateway.listAllOrders();

        assertEquals(2, allOrders.size());
    }

    @Test
    public void testListAllOrdersByDate() {
        OrderGateway orderGateway = new OrderRepositoryGateway(orderRepository, userRepository, productRepository, orderEntityMapper);
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        userGateway.createUser(user);

        Order order = new Order(1L, LocalDate.of(2023, 11, 1));
        Order order2 = new Order(2L, LocalDate.of(2022, 11, 1));

        orderGateway.createOrder(user, order);
        orderGateway.createOrder(user, order2);

        List<Order> allOrders = orderGateway.listAllOrdersByDate(LocalDate.of(2023, 11, 1), LocalDate.of(2023, 11, 1));

        assertEquals(1, allOrders.size());
    }

    @Test
    public void testGetOrderById() {
        OrderGateway orderGateway = new OrderRepositoryGateway(orderRepository, userRepository, productRepository, orderEntityMapper);
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        userGateway.createUser(user);

        Order order = new Order(1L, LocalDate.now());

        orderGateway.createOrder(user, order);

        Order orderResponse = orderGateway.getOrderById(1L);

        assertEquals(orderResponse.getId(), order.getId());
    }

    @Test
    public void testGetOrderByIdThatDoesntExist() {
        OrderGateway orderGateway = new OrderRepositoryGateway(orderRepository, userRepository, productRepository, orderEntityMapper);
        UserGateway userGateway = new UserRepositoryGateway(userRepository, userEntityMapper);

        User user = new User(1L, "Henrique");
        userGateway.createUser(user);

        Order order = new Order(1L, LocalDate.now());

        orderGateway.createOrder(user, order);

        assertThrows(ResourceNotFoundException.class, () -> orderGateway.getOrderById(2L));
    }
}