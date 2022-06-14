package com.rootstack.messaging.domain.service;

import com.rootstack.messaging.persistence.entity.UserEntity;
import com.rootstack.messaging.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class DatabaseUserDetailPasswordService implements UserDetailsPasswordService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        UserEntity userCredentials = userRepository.getByEmailComplete(user.getUsername());
        userCredentials.setPassword(newPassword);
        return new User(userCredentials.getEmail(), userCredentials.getPassword(), new ArrayList<>());
    }
}
