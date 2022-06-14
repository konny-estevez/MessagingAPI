package com.rootstack.messaging.web.model;

import com.rootstack.messaging.persistence.entity.MessageEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public class Message {
    private UUID Id;
    @NotBlank
    private String Content;
    private Boolean IsReaded;
    private LocalDateTime CreatedAt;
    private LocalDateTime UpdatedAt;
    @NotNull
    private UUID SenderId;
    @NotNull
    private UUID ReceiverId;

    public Message(UUID id, String content, boolean isReaded, LocalDateTime createdAt, UUID senderId, UUID receiverId) {
        Id = id;
        Content = content;
        IsReaded = isReaded;
        CreatedAt = createdAt;
        SenderId = senderId;
        ReceiverId = receiverId;
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

    public UUID getSenderId() {
        return SenderId;
    }

    public void setSenderId(UUID senderId) {
        SenderId = senderId;
    }

    public UUID getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(UUID receiverId) {
        ReceiverId = receiverId;
    }
}
