package com.henriquebarucco.luizalabs.dataprovider.gateways.user;

import com.henriquebarucco.luizalabs.core.exceptions.ResourceNotFoundException;
import com.henriquebarucco.luizalabs.core.gateways.UserGateway;
import com.henriquebarucco.luizalabs.core.entity.User;
import com.henriquebarucco.luizalabs.dataprovider.gateways.user.mapper.UserEntityMapper;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserEntity;
import com.henriquebarucco.luizalabs.dataprovider.persistence.user.UserRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class UserRepositoryGateway implements UserGateway {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;

    public UserRepositoryGateway(UserRepository userRepository, UserEntityMapper userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User createUser(User userDomainObj) {
        UserEntity userEntity = userEntityMapper.toEntity(userDomainObj);
        UserEntity savedObj = userRepository.save(userEntity);
        return userEntityMapper.toDomain(savedObj);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public User getUser(Long userId, String name) {
        Optional<UserEntity> userEntity = userRepository.findById(userId);

        if (userEntity.isEmpty()) {
            User user = new User(userId, name);
            return this.createUser(user);
        }

        return userEntityMapper.toDomain(userEntity.get());
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
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return userEntityMapper.toDomain(userEntity);
    }
}
