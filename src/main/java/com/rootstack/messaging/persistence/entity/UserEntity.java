package com.rootstack.messaging.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    private String Name;
    @Column(unique = true)
    private String Email;
    private String Password;
    private Boolean Locked;
    private Boolean Active;
    @NotNull
    @Column(unique = true)
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;
    @OneToMany(mappedBy = "SenderId")
    private List<MessageEntity> Sent;
    @OneToMany(mappedBy = "ReceiverId")
    private List<MessageEntity> Received;

    public Boolean getActive() {
        return Active;
    }

    public void setActive(Boolean active) {
        Active = active;
    }

    public List<MessageEntity> getSent() {
        return Sent;
    }

    public void setSent(List<MessageEntity> sent) {
        Sent = sent;
    }

    public List<MessageEntity> getReceived() {
        return Received;
    }

    public void setReceived(List<MessageEntity> received) {
        Received = received;
    }

    public UserEntity() {
    }

    public UserEntity(String name, String email, String password) {
        Name = name;
        Email = email;
        Password = password;
    }

    public UserEntity(UUID id, String name, String email, String password) {
        Id = id;
        Name = name;
        Email = email;
        Password = password;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Boolean getLocked() {
        return Locked;
    }

    public void setLocked(Boolean locked) {
        Locked = locked;
    }

    public LocalDateTime getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        CreatedAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        UpdatedAt = updatedAt;
    }
}
