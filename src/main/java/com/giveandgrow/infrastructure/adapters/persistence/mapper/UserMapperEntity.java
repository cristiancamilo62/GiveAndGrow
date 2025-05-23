package com.giveandgrow.infrastructure.adapters.persistence.mapper;

import com.giveandgrow.domain.model.user.UserDomain;
import com.giveandgrow.infrastructure.entities.UserEntity;
import org.mapstruct.Mapper;
import java.util.List;


@Mapper(componentModel = "spring")
public interface UserMapperEntity {
		
    UserDomain toDomain(UserEntity entity);
    
    UserEntity toEntity(UserDomain domain);

    List<UserDomain> toListDomain(List<UserEntity> entities);

    

}
