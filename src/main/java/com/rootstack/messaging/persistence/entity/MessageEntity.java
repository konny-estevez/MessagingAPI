package com.rootstack.messaging.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Messages")
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID Id;
    @NotBlank
    private String Content;
    private Boolean IsReaded;
    @NotNull
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;
    private UUID SenderId;
    private UUID ReceiverId;
    @ManyToOne
    @JoinColumn(name = "SenderId", insertable = false, updatable = false)
    private UserEntity Sender;
    @ManyToOne
    @JoinColumn(name = "ReceiverId", insertable = false, updatable = false)
    private UserEntity Receiver;

    public MessageEntity() {
    }

    public MessageEntity(String content, UUID senderId, UUID recevierId) {
        Content = content;
        SenderId = senderId;
        ReceiverId = recevierId;
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public boolean isReaded() {
        return IsReaded;
    }

    public void setReaded(boolean readed) {
        IsReaded = readed;
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

    public UUID getSenderId() { return SenderId;}

    public void setSenderId(UUID senderId) {
        SenderId = senderId;
    }

    public UUID getReceiverId() { return  ReceiverId;}

    public void setReceiverId(UUID receiverId) {
        ReceiverId = receiverId;
    }
}
