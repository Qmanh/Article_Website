package com.laptrinhjavaweb.dto;

import com.laptrinhjavaweb.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter@Setter
public class RoleDTO extends AbstractDTO<RoleDTO>{
    private String code;
    private String name;
    private Collection<UserDTO> users = new ArrayList<>();
}
