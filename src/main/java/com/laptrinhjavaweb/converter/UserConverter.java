package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDTO toDTO(UserEntity Entity){
        UserDTO result = new UserDTO();
        result.setId(Entity.getId());
        result.setFullName(Entity.getFullName());
        result.setUsername(Entity.getUserName());
        result.setPassword(Entity.getPassword());
        result.setStatus(Entity.getStatus());
        result.setRoles(Entity.getRoles());
        return result;
    }

    public UserEntity toEntity(UserDTO DTO){
        UserEntity result = new UserEntity();
        result.setFullName(DTO.getFullName());
        result.setPassword(DTO.getPassword());
        result.setUserName(DTO.getUsername());
        result.setStatus(DTO.getStatus());
        return result;
    }

    public UserEntity toEntity(UserEntity result, UserDTO DTO){
        result.setUserName(DTO.getUsername());
        result.setPassword(DTO.getPassword());
        result.setFullName(DTO.getFullName());
        result.setStatus(DTO.getStatus());
        return result;
    }
}
