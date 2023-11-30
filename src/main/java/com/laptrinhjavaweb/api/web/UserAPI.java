package com.laptrinhjavaweb.api.web;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "userAPIOfWeb")
public class UserAPI {
    @Autowired
    private IUserService userService;


    @PostMapping(value = "/api/user", consumes = {"application/json"})
    public UserDTO createUser(@RequestBody UserDTO userDTO){

        return userService.save(userDTO);
    }

    @PutMapping(value = "/api/user",consumes = {"application/json"})
    public UserDTO updateUser(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }

    @DeleteMapping(value = "/api/user")
    public void deleteUser(@RequestBody Long [] ids){
        userService.delete(ids);
    }
}
