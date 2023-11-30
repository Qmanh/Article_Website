package com.laptrinhjavaweb.service;


import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.entity.RoleEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IRoleService {
    RoleEntity findById(Long id);
    List<RoleDTO>findAll(Pageable pageable);
    int getTotalItem();
    RoleDTO save(RoleDTO roleDTO);
    void delete(Long[] ids);
}
