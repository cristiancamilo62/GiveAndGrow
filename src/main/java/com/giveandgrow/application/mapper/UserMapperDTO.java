package com.giveandgrow.application.mapper;

import com.giveandgrow.application.dto.UserDTO;
import com.giveandgrow.domain.model.user.UserDomain;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapperDTO {
   
	UserDomain toDomain(UserDTO dto);
	
    UserDTO toDTO(UserDomain domain);

    List<UserDTO> toDTOList(List<UserDomain> domains);
}
