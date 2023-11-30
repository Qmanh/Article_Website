package com.laptrinhjavaweb.service;


import com.laptrinhjavaweb.dto.CommentDTO;
import com.laptrinhjavaweb.dto.MyUser;

import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICommentService {
    CommentDTO save(CommentDTO commentDTO, MyUser user);
    List<CommentDTO> commentByUserId (Long id,Pageable pageable);
    List<CommentDTO> findAll(Pageable pageable);
    int getTotalItem(Long id);
    int getTotalItem();
    void delete(Long[]ids);
}
