package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.UserConverter;
import com.laptrinhjavaweb.dto.CommentDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.dto.RoleDTO;
import com.laptrinhjavaweb.dto.UserDTO;

import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.CommentEntity;
import com.laptrinhjavaweb.entity.RoleEntity;
import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.RoleRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.IRoleService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.Email;
import com.laptrinhjavaweb.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.laptrinhjavaweb.constant.SystemConstant.ACTIVE_STATUS;


@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        Set<RoleEntity> roles = null;

        if(userDTO.getId() != null){
            UserEntity oldUser = userRepository.findOne(userDTO.getId());
           System.out.println();
            if(oldUser.getRoles() != null){
                oldUser.getRoles().clear();
            }
            roles = oldUser.getRoles();
            roles.add(roleRepository.findOneByName(userDTO.getRoleName()));
            oldUser.setRoles(roles);
            userEntity =userConverter.toEntity(oldUser,userDTO);
        }else{
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
            userDTO.setPassword(encodedPassword);
            userDTO.setStatus(1);
            userEntity = userConverter.toEntity(userDTO);
            RoleEntity roleEntity = roleRepository.findOneById(2l);
            roles = userEntity.getRoles();
            roles.add(roleEntity);
            userEntity.setRoles(roles);
        }

        return userConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public boolean existsByUserName(String username) {
        if(userRepository.existsByUserName(username)){
            return true;
        }
        return false;
    }

    @Override
    public int getTotalItem() {
        return (int)userRepository.count();
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {

        List<UserDTO> models = new ArrayList<>();
        List<UserEntity> entities = userRepository.findAll(pageable).getContent();
        for(UserEntity item : entities){
            UserDTO userDTO = userConverter.toDTO(item);
            models.add(userDTO);
        }
        return models;
    }

    @Override
    public UserDTO findById(Long id) {
        return userConverter.toDTO(userRepository.findOne(id));
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id : ids){
            userRepository.delete(id);
        }

    }

    @Override
        public Set<RoleEntity> getUserRoles(Long userId) {
            UserEntity user = userRepository.findOne(userId);
            if (user != null) {
                return user.getRoles();
            }
            return null;
        }

    @Override
    public UserDTO findByUserName(String username) {
        if(userRepository.findOneByUserNameAndStatus(username,ACTIVE_STATUS)!= null){
            return userConverter.toDTO(userRepository.findOneByUserNameAndStatus(username,ACTIVE_STATUS));
        }
       return null;
    }

    @Override
    public UserDTO resetPassword(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        String newPassword = RandomStringGenerator.generateRandomString();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        userDTO.setPassword(encodedPassword);

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(userDTO.getUsername());
        email.setSubject(Email.writeSubject(userDTO.getUsername()));
        email.setText(Email.writeEmailSendPassword(newPassword));
        // sends the e-mail
        mailSender.send(email);

        UserEntity oldUser = userRepository.findOne(userDTO.getId());

        userEntity =userConverter.toEntity(oldUser,userDTO);
        return userConverter.toDTO(userRepository.save(userEntity));
    }

    @Override
    public UserDTO changePassword(UserDTO userDTO,String newPassword) {
        UserEntity userEntity = new UserEntity();
        UserEntity oldUser = userRepository.findOne(userDTO.getId());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        userDTO.setPassword(encodedPassword);
        userEntity =userConverter.toEntity(oldUser,userDTO);
        return userConverter.toDTO(userRepository.save(userEntity));
    }
}
