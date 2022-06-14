package com.rootstack.messaging.web.controller;

import com.rootstack.messaging.domain.service.UsersService;
import com.rootstack.messaging.persistence.entity.UserEntity;
import com.rootstack.messaging.persistence.mapper.UserMapper;
import com.rootstack.messaging.web.model.NewUser;
import com.rootstack.messaging.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UsersService usersService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserMapper userMapper;

    @GetMapping("available")
    public ResponseEntity<List<User>> getAvailableUsers() {
        User user = usersService.getByEmail(getAuthenticatedUsername());
        List<User> list = usersService.getUsersExcept(user.getId());
        if (list.isEmpty()) {
            return new ResponseEntity("No available users to send messages", HttpStatus.NOT_FOUND);
        }
        else {
            return ResponseEntity.ok(list);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@Validated @PathVariable UUID id) {
        User user = usersService.getUser(id);
        if (user == null) {
            return new ResponseEntity("No user found with that Id", HttpStatus.NOT_FOUND);
        }
        else {
            return ResponseEntity.ok(user);
        }
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> createUser(@Validated @RequestBody NewUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            User entity = usersService.save(user.fromModel());
            if (entity != null) {
                return ResponseEntity.created(null).body(entity);
            } else {
                return new ResponseEntity("Unable to create the user.", HttpStatus.BAD_REQUEST);
            }
        }
        catch (DuplicateKeyException dkex) {
            return new ResponseEntity("Unable to create the user. " + dkex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Boolean> updateUser(@Validated @PathVariable UUID id, @Validated @RequestBody User user) {
        UserEntity entity = userMapper.toUserEntity(user);
        entity.setId(id);
        try {
            if (usersService.update(entity)) {
                return ResponseEntity.ok(true);
            } else {
                return new ResponseEntity("Unable to update the user.", HttpStatus.BAD_REQUEST);
            }
        }
        catch (DuplicateKeyException dkex) {
            return new ResponseEntity("Unable to update the user. " + dkex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Boolean> unlockUser(@Validated @PathVariable UUID id) {
        if (usersService.unlock(id)) {
            return ResponseEntity.ok(true);
        }
        else {
            return new ResponseEntity("Unable to unlock the user.", HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@Validated @PathVariable UUID id) {
        if (usersService.delete(id)) {
            return ResponseEntity.ok(true);
        }
        else {
            return new ResponseEntity("Unable to delete the user.", HttpStatus.BAD_REQUEST );
        }
    }

    private String getAuthenticatedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
