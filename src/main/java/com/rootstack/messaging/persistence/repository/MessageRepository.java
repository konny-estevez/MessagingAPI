package com.rootstack.messaging.persistence.repository;

import com.rootstack.messaging.persistence.entity.MessageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MessageRepository {
    @Autowired
    IMessageRepository messageRepository;

    public List<MessageEntity> getSendedMessages(UUID id) {
        return messageRepository.findBySenderId(id);
    }

    public List<MessageEntity> getReceivedMessages(UUID id) {
        return messageRepository.findByReceiverId(id);
    }

    public List<MessageEntity> getSendedReadedMessages(UUID id) {
        return messageRepository.findBySenderIdReaded(id);
    }

    public List<MessageEntity> getReceivedReadedMessages(UUID id) {
        return messageRepository.findByReceiverIdReaded(id);
    }

    public MessageEntity getById(UUID id) {
        Optional<MessageEntity> entity = messageRepository.findById(id);
        if (entity.isPresent()) {
            return entity.get();
        }
        return null;
    }

    public MessageEntity save(MessageEntity message) {
        message.setCreatedAt(LocalDateTime.now());
        message.setReaded(false);
        return messageRepository.save(message);
    }

    public boolean update(MessageEntity entity) {
        entity.setUpdatedAt(LocalDateTime.now());
        messageRepository.save(entity);
        return true;
    }

    public boolean delete(UUID id) {
        Optional<MessageEntity> temp = messageRepository.findById(id);
        if (temp.isPresent()) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
