package com.rootstack.messaging.domain.service;

import com.rootstack.messaging.persistence.entity.UserEntity;
import com.rootstack.messaging.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.getByEmailComplete(username);
        if (entity != null) {
            return new User(entity.getEmail(), entity.getPassword(), new ArrayList<>());
        }
        throw new UsernameNotFoundException("The username is not found.");
    }
}
