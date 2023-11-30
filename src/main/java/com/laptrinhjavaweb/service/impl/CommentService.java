package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CommentConverter;
import com.laptrinhjavaweb.dto.CommentDTO;
import com.laptrinhjavaweb.dto.MyUser;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CommentEntity;
import com.laptrinhjavaweb.entity.NewEntity;

import com.laptrinhjavaweb.entity.UserEntity;
import com.laptrinhjavaweb.repository.CommentRepository;
import com.laptrinhjavaweb.repository.NewRepository;
import com.laptrinhjavaweb.repository.UserRepository;
import com.laptrinhjavaweb.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentConverter commentConverter;
    @Autowired
    private NewRepository newRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public CommentDTO save(CommentDTO commentDTO, MyUser user) {
        CommentEntity commentEntity = new CommentEntity();
        NewEntity newEntity = newRepository.findOne(commentDTO.getNewId());
        String userName = user.getUsername();
        UserEntity  userEntity =userRepository.findOneByUserNameAndStatus(userName,1);
        commentEntity.setNewId(newEntity);
        commentEntity.setUserId(userEntity);
        commentEntity.setContent(commentDTO.getContent());

        return commentConverter.toDTO(commentRepository.save(commentEntity)) ;
    }

    @Override
    public List<CommentDTO> commentByUserId(Long id, Pageable pageable) {
        Page<CommentEntity> commentPage = commentRepository.findCommentEntitiesByNewIdOrderByCreatedDateDesc(newRepository.findOne(id), pageable);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        List<CommentEntity> commentEntities = commentPage.getContent();

        for (CommentEntity commentEntity : commentEntities) {
            CommentDTO commentDTO = commentConverter.toDTO(commentEntity);
            commentDTOS.add(commentDTO);
        }
        Collections.reverse(commentDTOS);
        return commentDTOS;
    }
    @Override
    public List<CommentDTO> findAll(Pageable pageable) {
        List<CommentDTO> models = new ArrayList<>();
        List<CommentEntity> entities = commentRepository.findByOrderByNewIdAsc(pageable).getContent();
        for(CommentEntity item : entities){
            CommentDTO commentDTO = commentConverter.toDTO(item);
            models.add(commentDTO);
        }
        return models;
    }

    @Override
    public int getTotalItem(Long id) {
        return commentRepository.countCommentEntitiesByNewId(newRepository.findOne(id));
    }

    @Override
    public int getTotalItem() {
        return (int)commentRepository.count();
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id : ids){
            commentRepository.delete(id);
        }
    }


}
