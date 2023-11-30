package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    public RoleDTO toDTO(RoleEntity roleEntity){
        RoleDTO result = new RoleDTO();
        result.setId(roleEntity.getId());
        result.setCode(roleEntity.getCode());
        result.setName(roleEntity.getName());
        return result;
    }

    public RoleEntity toEntity(RoleDTO roleDTO){
        RoleEntity result = new RoleEntity();
        result.setCode(roleDTO.getCode());
        result.setName(roleDTO.getName());
        return result;
    }

    public RoleEntity toEntity(RoleEntity result, RoleDTO DTO){
        result.setCode(DTO.getCode());
        result.setName(DTO.getName());
        return result;
    }
}
