package com.rootstack.messaging.persistence.mapper;

import com.rootstack.messaging.persistence.entity.MessageEntity;
import com.rootstack.messaging.web.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    @Mappings({
    })
    Message toMessage(MessageEntity entity);
    List<Message> toMessageList(List<MessageEntity> entityList);
    MessageEntity toMessageEntity(Message message);

}
