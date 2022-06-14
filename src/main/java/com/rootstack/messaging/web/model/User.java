package com.rootstack.messaging.web.model;

import com.rootstack.messaging.persistence.entity.UserEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private UUID Id;
    @NotBlank
    private String Name;
    @NotBlank
    @Email
    private String Email;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;
    private boolean Active;

    public User(UUID id, String name, String email, LocalDateTime createdAt, LocalDateTime updatedAt, boolean active) {
        Id = id;
        Name = name;
        Email = email;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        Active = active;
    }

    public  UUID getId() { return  Id; }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public LocalDateTime getCreatedAt() {return CreatedAt;}

    public LocalDateTime getUpdatedAt() {return UpdatedAt; }
}
