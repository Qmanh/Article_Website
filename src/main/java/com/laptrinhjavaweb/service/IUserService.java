package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface IUserService {
    UserDTO save(UserDTO userDTO);
    boolean existsByUserName(String username);
    int getTotalItem();
    List<UserDTO> findAll(Pageable pageable);
    UserDTO findById(Long id);
    void delete(Long []ids);
    Set<RoleEntity> getUserRoles(Long userId);
    UserDTO findByUserName(String username);
    UserDTO resetPassword(UserDTO userDTO);
    UserDTO changePassword(UserDTO userDTO,String newPassword);
}
