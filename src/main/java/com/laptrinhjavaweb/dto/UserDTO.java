package com.laptrinhjavaweb.dto;

import com.laptrinhjavaweb.entity.RoleEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;


@Getter@Setter
public class UserDTO extends AbstractDTO<UserDTO>{
    private String username;
    private String password;
    private String confirmPassword;
    private Integer status;
    private String fullName;
    private Integer roleCode;
    private String roleName;
    private Set<RoleEntity> roles;

}
