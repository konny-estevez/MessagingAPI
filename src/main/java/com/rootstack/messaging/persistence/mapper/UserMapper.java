package com.rootstack.messaging.persistence.mapper;

import com.rootstack.messaging.persistence.entity.UserEntity;
import com.rootstack.messaging.web.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
    })
    User toUser(UserEntity entity);
    List<User> toUserList(List<UserEntity> entitylist);
    UserEntity toUserEntity(User user);
}
