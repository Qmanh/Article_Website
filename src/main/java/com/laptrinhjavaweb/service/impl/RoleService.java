package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.RoleConverter;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleConverter roleConverter;
    @Override
    public RoleEntity findById(Long id) {
        return roleRepository.findOneById(id);
    }

    @Override
    public List<RoleDTO> findAll(Pageable pageable) {
        List<RoleDTO> models = new ArrayList<>();
        List<RoleEntity> entities = roleRepository.findAll(pageable).getContent();
        for(RoleEntity item : entities){
            RoleDTO roleDTO = roleConverter.toDTO(item);
            models.add(roleDTO);
        }
        return models;
    }

    @Override
    public int getTotalItem() {
        return (int)roleRepository.count();
    }

    @Override
    @Transactional
    public RoleDTO save(RoleDTO roleDTO) {
        RoleEntity roleEntity = new RoleEntity();
        if(roleDTO.getId() != null){
            RoleEntity oldRole = roleRepository.findOne(roleDTO.getId());
            roleEntity = roleConverter.toEntity(oldRole,roleDTO);
        }else{
           roleEntity = roleConverter.toEntity(roleDTO);
        }
        return roleConverter.toDTO(roleRepository.save(roleEntity));
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id : ids){
            roleRepository.delete(id);
        }
    }
}
