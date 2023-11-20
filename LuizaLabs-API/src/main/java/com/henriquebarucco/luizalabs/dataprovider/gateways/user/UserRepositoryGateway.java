package com.henriquebarucco.luizalabs.dataprovider.gateways.user;

import com.henriquebarucco.luizalabs.core.gateways.UserGateway;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper.UserEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderEntity;
import com.henriquebarucco.luizalabs.dataprovider.persistence.order.OrderRepository;
import com.henriquebarucco.luizalabs.dataprovider.persistence.product.ProductEntity;
import com.henriquebarucco.luizalabs.dataprovider.persistence.product.ProductRepository;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserEntity;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserRepositoryGateway implements UserGateway {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Override
    public User createUser(User userDomainObj) {
        UserEntity userEntity = userEntityMapper.toEntity(userDomainObj);
        UserEntity savedObj = userRepository.save(userEntity);
        return userEntityMapper.toDomain(savedObj);
    }

    @Override
    public User getUser(Long userId, String name) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);
        return userEntityMapper.toDomain(userEntity.orElse(userRepository.save(new UserEntity(userId, name))));
    }

    @CacheEvict(value = {"users", "orders"}, allEntries = true)
    @Override
    public void addOrder(User user, Long orderId, LocalDate purchaseDate, Long productId, Double productValue) {
        UserEntity userEntity = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));

        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(new OrderEntity(orderId, purchaseDate, userEntity));
        orderRepository.save(orderEntity);

        ProductEntity productEntity = productRepository.save(new ProductEntity(productId, productValue, orderEntity));

        orderEntity.addProduct(productEntity);
    }

    @Cacheable("users")
    @Override
    public List<User> listAllUsers() {
        List<UserEntity> userEntities = (List<UserEntity>) userRepository.findAll();

        return userEntities.stream()
                .map(userEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<User> listAllUsersByDate(LocalDate startDate, LocalDate endDate) {
        List<UserEntity> userEntities = userRepository.findAllInRange(startDate, endDate);

        return userEntities.stream()
                .map(userEntityMapper::toDomain)
                .toList();
    }

    @Cacheable(value = "users", key = "#userId")
    @Override
    public User getUserById(Long userId) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found")); // TODO - Create a custom exception

        return userEntityMapper.toDomain(userEntity);
    }
}
