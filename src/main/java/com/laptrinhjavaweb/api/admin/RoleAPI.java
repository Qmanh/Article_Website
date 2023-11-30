package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "roleAPIOfAdmin")
public class RoleAPI {
    @Autowired
    private IRoleService roleService;

    @PostMapping(value = "/api/role", consumes = {"application/json"})
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO){
        return roleService.save(roleDTO);
    }

    @PutMapping(value = "/api/role", consumes = {"application/json"})
    public RoleDTO updateRole(@RequestBody RoleDTO roleDTO){
        return roleService.save(roleDTO);
    }

    @DeleteMapping(value = "/api/role")
    public void deleteRole(@RequestBody Long [] ids){
        roleService.delete(ids);
    }
}
