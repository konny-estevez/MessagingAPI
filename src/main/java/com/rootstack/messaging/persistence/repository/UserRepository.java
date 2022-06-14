package com.rootstack.messaging.persistence.repository;

import com.rootstack.messaging.persistence.entity.UserEntity;
import com.rootstack.messaging.persistence.mapper.UserMapper;
import com.rootstack.messaging.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserRepository {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    public List<User> getUsersExcept(UUID id) {
        return userMapper.toUserList(userRepository.getExceptUserListAndActive(id));
    }

    public List<User> getList() {
        return userMapper.toUserList(userRepository.findAll());
    }

    public boolean notExistsById(UUID id) {
        return !userRepository.existsById(id);
    }

    public UserEntity getByEmailComplete(String email) {
        return userRepository.findByEmail(email);
    }

    public User getByEmail(String email) {
        return userMapper.toUser(getByEmailComplete(email));
    }

    public UserEntity getById(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.orElse(null);
    }

    public User save(UserEntity user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new DuplicateKeyException("The user is already registered.");
        }
        user.setCreatedAt(LocalDateTime.now());
        user.setActive(true);
        user.setLocked(false);
        return userMapper.toUser(userRepository.save(user));
    }

    public boolean update(UserEntity user) {
        Optional<UserEntity> temp = userRepository.findById(user.getId());
        UserEntity existing = userRepository.findByEmail(user.getEmail());
        if (existing != null && !user.getId().equals(existing.getId())) {
            throw new DuplicateKeyException("The user already exists.");
        }
        if (temp.isPresent()) {
            temp.get().setUpdatedAt(LocalDateTime.now());
            temp.get().setEmail(user.getEmail());
            temp.get().setName(user.getName());
            userRepository.save(temp.get());
            return true;
        }
        return false;
    }

    public boolean unlock(UUID id) {
        Optional<UserEntity> temp = userRepository.findById(id);
        if (temp.isPresent()) {
            temp.get().setLocked(false);
            temp.get().setUpdatedAt(LocalDateTime.now());
            userRepository.save(temp.get());
            return true;
        }
        return false;
    }

    public boolean delete(UUID id) {
        Optional<UserEntity> temp = userRepository.findById(id);
        if (temp.isPresent() && temp.get().getActive()) {
            temp.get().setActive(false);
            temp.get().setUpdatedAt(LocalDateTime.now());
            userRepository.save(temp.get());
            return true;
        }
        return false;
    }
}
