package com.rootstack.messaging.persistence.mapper;

import com.rootstack.messaging.persistence.entity.UserEntity;
import com.rootstack.messaging.web.model.User;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-06-13T23:42:05-0500",
    comments = "version: 1.5.1.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 1.8.0_202 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toUser(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        String name = null;
        String email = null;
        UUID id = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;
        boolean active = false;

        name = entity.getName();
        email = entity.getEmail();
        id = entity.getId();
        createdAt = entity.getCreatedAt();
        updatedAt = entity.getUpdatedAt();
        if ( entity.getActive() != null ) {
            active = entity.getActive();
        }

        User user = new User( id, name, email, createdAt, updatedAt, active );

        return user;
    }

    @Override
    public List<User> toUserList(List<UserEntity> entitylist) {
        if ( entitylist == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( entitylist.size() );
        for ( UserEntity userEntity : entitylist ) {
            list.add( toUser( userEntity ) );
        }

        return list;
    }

    @Override
    public UserEntity toUserEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setName( user.getName() );
        userEntity.setEmail( user.getEmail() );
        userEntity.setCreatedAt( user.getCreatedAt() );
        userEntity.setUpdatedAt( user.getUpdatedAt() );

        return userEntity;
    }
}
