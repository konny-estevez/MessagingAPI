package com.rootstack.messaging.persistence.mapper;

import com.rootstack.messaging.persistence.entity.MessageEntity;
import com.rootstack.messaging.web.model.Message;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-14T02:07:27-0500",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public Message toMessage(MessageEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UUID id = null;
        String content = null;
        LocalDateTime createdAt = null;
        UUID senderId = null;
        UUID receiverId = null;

        id = entity.getId();
        content = entity.getContent();
        createdAt = entity.getCreatedAt();
        senderId = entity.getSenderId();
        receiverId = entity.getReceiverId();

        boolean isReaded = false;

        Message message = new Message( id, content, isReaded, createdAt, senderId, receiverId );

        message.setReaded( entity.isReaded() );
        message.setUpdatedAt( entity.getUpdatedAt() );

        return message;
    }

    @Override
    public List<Message> toMessageList(List<MessageEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Message> list = new ArrayList<Message>( entityList.size() );
        for ( MessageEntity messageEntity : entityList ) {
            list.add( toMessage( messageEntity ) );
        }

        return list;
    }

    @Override
    public MessageEntity toMessageEntity(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageEntity messageEntity = new MessageEntity();

        messageEntity.setId( message.getId() );
        messageEntity.setContent( message.getContent() );
        messageEntity.setReaded( message.isReaded() );
        messageEntity.setCreatedAt( message.getCreatedAt() );
        messageEntity.setUpdatedAt( message.getUpdatedAt() );
        messageEntity.setSenderId( message.getSenderId() );
        messageEntity.setReceiverId( message.getReceiverId() );

        return messageEntity;
    }
}
