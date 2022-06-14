package com.rootstack.messaging.web.controller;

import com.rootstack.messaging.domain.service.MessagesService;
import com.rootstack.messaging.persistence.entity.MessageEntity;
import com.rootstack.messaging.persistence.entity.UserEntity;
import com.rootstack.messaging.persistence.mapper.MessageMapper;
import com.rootstack.messaging.web.model.Message;
import com.rootstack.messaging.web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

@RestController
@RequestMapping("/messages")
public class MessagesController {
    @Autowired
    MessagesService messagesService;
    @Autowired
    MessageMapper messageMapper;

    @GetMapping("sended")
    public ResponseEntity<List<Message>> getSendedMessages(){
        StringBuilder error = new StringBuilder();
        String username = getAuthenticatedUsername();
        List<MessageEntity> messages = messagesService.getSendedMessages(username, error);
        if (error.length() > 0) {
            return new ResponseEntity("Unable to get messages. " + error.toString(), HttpStatus.BAD_REQUEST);
        }
        if (messages == null || messages.size() == 0) {
            return new ResponseEntity("No messages sended.", HttpStatus.NOT_FOUND);
        }
        else  {
            return ResponseEntity.ok(messageMapper.toMessageList(messages));
        }
    }

    @GetMapping("received")
    public ResponseEntity<List<Message>> getReceivedMessages(){
        StringBuilder error = new StringBuilder();
        String username = getAuthenticatedUsername();
        List<MessageEntity> messages = messagesService.getReceivedMessages(username, error);
        if (error.length() > 0) {
            return new ResponseEntity("Unable to get messages. " + error.toString(), HttpStatus.BAD_REQUEST);
        }
        if (messages == null || messages.size() == 0) {
            return new ResponseEntity("No messages received.", HttpStatus.NOT_FOUND);
        }
        else  {
            return ResponseEntity.ok(messageMapper.toMessageList(messages));
        }
    }

    @GetMapping("sended/readed")
    public ResponseEntity<List<Message>> getSendedReadedMessages() {
        StringBuilder error = new StringBuilder();
        String username = getAuthenticatedUsername();
        List<MessageEntity> messages = messagesService.getSendedReadedMessages(username, error);
        if (error.length() > 0) {
            return new ResponseEntity("Unable to get messages. " + error.toString(), HttpStatus.BAD_REQUEST);
        }
        if (messages == null || messages.size() == 0) {
            return new ResponseEntity("No messages sended and readed.", HttpStatus.NOT_FOUND);
        }
        else  {
            return ResponseEntity.ok(messageMapper.toMessageList(messages));
        }
    }

    @GetMapping("received/readed")
    public ResponseEntity<List<Message>> getReceivedReadedMessages(){
        StringBuilder error = new StringBuilder();
        String username = getAuthenticatedUsername();
        List<MessageEntity> messages = messagesService.getReceivedReadedMessages(username, error);
        if (error.length() > 0) {
            return new ResponseEntity("Unable to get messages. " + error.toString(), HttpStatus.BAD_REQUEST);
        }
        if (messages == null || messages.size() == 0) {
            return new ResponseEntity("No messages received and readed.", HttpStatus.NOT_FOUND);
        }
        else  {
            return ResponseEntity.ok(messageMapper.toMessageList(messages));
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Message> getMessage(@PathVariable UUID id) {
        StringBuilder error = new StringBuilder();
        String username = getAuthenticatedUsername();
        MessageEntity entity = messagesService.getById(id, username, error);
        if (entity != null) {
            return ResponseEntity.created(null).body(messageMapper.toMessage(entity));
        }
        else {
            return new ResponseEntity("Unable to get the message. " + error.toString(), HttpStatus.BAD_REQUEST );
        }
    }

    @PostMapping()
    public ResponseEntity<Message> createMessage(@Validated @RequestBody Message message) {
        StringBuilder error = new StringBuilder();
        String username = getAuthenticatedUsername();
        MessageEntity newMessage = new MessageEntity(message.getContent(), message.getSenderId(), message.getReceiverId());
        MessageEntity entity = messagesService.save(newMessage, username, error);
        if (entity != null) {
            return ResponseEntity.created(null).body(messageMapper.toMessage(entity));
        }
        else {
            return new ResponseEntity("Unable to create the message. " + error.toString(), HttpStatus.BAD_REQUEST );
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Boolean> updateMessage(@Validated @PathVariable UUID id, @Validated @RequestBody String content) {
        StringBuilder error = new StringBuilder();
        MessageEntity entity = new MessageEntity();
        entity.setId(id);
        entity.setContent(content);
        String username = getAuthenticatedUsername();
        if (messagesService.update(entity, username, error)) {
            return ResponseEntity.ok(true);
        }
        else {
            return new ResponseEntity("Unable to update the message. " + error.toString(), HttpStatus.BAD_REQUEST );
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<Boolean> markReadMessage(@Validated @PathVariable UUID id) {
        StringBuilder error = new StringBuilder();
        String username = getAuthenticatedUsername();
        if (messagesService.markRead(id, username, error)) {
            return ResponseEntity.ok(true);
        }
        else {
            return new ResponseEntity("Unable to mark readed the message. " + error.toString(), HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteMessage(@Validated @PathVariable UUID id) {
        StringBuilder error = new StringBuilder();
        String username = getAuthenticatedUsername();
        if (messagesService.delete(id, username, error)) {
            return ResponseEntity.ok(true);
        }
        else {
            return new ResponseEntity("Unable to delete the message. " + error.toString(), HttpStatus.BAD_REQUEST );
        }
    }

    private String getAuthenticatedUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
