package com.rootstack.messaging.domain.service;

import com.rootstack.messaging.persistence.entity.MessageEntity;
import com.rootstack.messaging.persistence.repository.MessageRepository;
import com.rootstack.messaging.persistence.repository.UserRepository;
import com.rootstack.messaging.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MessagesService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UserRepository userRepository;

    public List<MessageEntity> getSendedMessages(String username, StringBuilder error) {
        User user = userRepository.getByEmail(username);
        if (user != null) {
            return messageRepository.getSendedMessages(user.getId());
        }
        error.append("No user can be obtained from session.");
        return null;
    }

    public List<MessageEntity> getReceivedMessages(String username, StringBuilder error) {
        User user = userRepository.getByEmail(username);
        if (user != null) {
            return messageRepository.getReceivedMessages(user.getId());
        }
        error.append("No user can be obtained from session.");
        return null;
    }

    public List<MessageEntity> getSendedReadedMessages(String username, StringBuilder error) {
        User user = userRepository.getByEmail(username);
        if (user != null) {
            return messageRepository.getSendedReadedMessages(user.getId());
        }
        error.append("No user can be obtained from session.");
        return null;
    }

    public List<MessageEntity> getReceivedReadedMessages(String username, StringBuilder error) {
        User user = userRepository.getByEmail(username);
        if (user != null) {
            return messageRepository.getReceivedReadedMessages(user.getId());
        }
        error.append("No user can be obtained from session.");
        return null;
    }

    public MessageEntity getById(UUID id, String username, StringBuilder error) {
        MessageEntity message = messageRepository.getById(id);
        if (message == null) {
            error.append("The message not exists.");
            return null;
        }
        User user = userRepository.getByEmail(username);
        if (user == null || (!message.getSenderId().equals(user.getId()) && !message.getReceiverId().equals(user.getId()))) {
            error.append("You are not the owner or receiver of the message.");
            return null;
        }
        return message;
    }

    public MessageEntity save(MessageEntity message, String username, StringBuilder error) {
        User user = userRepository.getByEmail(username);
        message.setSenderId(user.getId());
        if (message.getSenderId().equals(message.getReceiverId())) {
            error.append("SenderId and ReceiverId are the same.");
            return  null;
        }
        if (userRepository.notExistsById(message.getSenderId())) {
            error.append("SenderId is not a valid user.");
            return  null;
        }
        if (userRepository.notExistsById(message.getReceiverId())) {
            error.append("ReceiverId is not a valid user.");
            return  null;
        }
        return messageRepository.save(message);
    }

    public boolean update(MessageEntity message, String username, StringBuilder error) {
        MessageEntity existing = messageRepository.getById(message.getId());
        if (existing == null) {
            error.append("The message not exists.");
            return false;
        }
        User user = userRepository.getByEmail(username);
        if (user == null || !existing.getSenderId().equals(user.getId())) {
            error.append("You are not the owner of the message.");
            return false;
        }
        existing.setContent(message.getContent());
        return messageRepository.update(existing);
    }

    public boolean markRead(UUID id, String username, StringBuilder error) {
        MessageEntity existing = messageRepository.getById(id);
        if (existing == null) {
            error.append("The message not exists.");
            return false;
        }
        User user = userRepository.getByEmail(username);
        if (user == null || !existing.getReceiverId().equals(user.getId())) {
            error.append("You are not the receiver of the message.");
            return false;
        }
        existing.setReaded(true);
        return messageRepository.update(existing);
    }

    public boolean delete(UUID id, String username, StringBuilder error) {
        MessageEntity temp = messageRepository.getById(id);
        if (temp == null) {
            error.append("The message not exists.");
            return false;
        }
        User user = userRepository.getByEmail(username);
        if (user == null || !temp.getSenderId().equals(user.getId())) {
            error.append("You are not the owner of the message.");
            return false;
        }
        return messageRepository.delete(id);
    }
}
