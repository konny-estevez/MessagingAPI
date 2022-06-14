package com.rootstack.messaging.persistence.repository;

import com.rootstack.messaging.persistence.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IMessageRepository extends JpaRepository<MessageEntity, UUID> {
    @Query(value = "select * from messages m where m.sender_id = ?1", nativeQuery = true)
    List<MessageEntity> findBySenderId(UUID id);

    @Query(value = "select * from messages m where m.receiver_id = ?1", nativeQuery = true)
    List<MessageEntity> findByReceiverId(UUID id);

    @Query(value = "select * from messages m where m.sender_id = ?1 and is_readed = TRUE", nativeQuery = true)
    List<MessageEntity> findBySenderIdReaded(UUID id);

    @Query(value = "select * from messages m where m.receiver_id = ?1 and is_readed = TRUE", nativeQuery = true)
    List<MessageEntity> findByReceiverIdReaded(UUID id);
}
