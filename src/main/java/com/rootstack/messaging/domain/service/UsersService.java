package com.rootstack.messaging.domain.service;

import com.rootstack.messaging.persistence.mapper.UserMapper;
import com.rootstack.messaging.persistence.entity.UserEntity;
import com.rootstack.messaging.persistence.repository.UserRepository;
import com.rootstack.messaging.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    public List<User> getUsersExcept(UUID userId) {
        return userRepository.getUsersExcept(userId);
    }

    public List<User> getList() {
        return userRepository.getList();
    }

    public UserEntity getUserComplete(UUID id) {
        return userRepository.getById(id);
    }

    public User getUser(UUID id) {
        return userMapper.toUser(getUserComplete(id));
    }

    public UserEntity getByEmailComplete(String email) {
        return userRepository.getByEmailComplete(email);
    }

    public User getByEmail(String email) {
        return userMapper.toUser(getByEmailComplete(email));
    }

    public User save(UserEntity user) {
        return userRepository.save(user);
    }

    public boolean update(UserEntity user) {
        return userRepository.update(user);
    }

    public boolean unlock(UUID id) {
        return userRepository.unlock(id);
    }

    public boolean delete(UUID id) {
        return userRepository.delete(id);
    }
}
