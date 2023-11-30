package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.CommentDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.CommentEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class CommentConverter {

    public CommentDTO toDTO(CommentEntity commentEntity){
        CommentDTO result = new CommentDTO();
        result.setId(commentEntity.getId());
        result.setContent(commentEntity.getContent());
        result.setNewId(commentEntity.getNewId().getId());
        result.setUserId(commentEntity.getUserId().getId());
        result.setCreatedBy(commentEntity.getCreatedBy());
        result.setCreatedDate(commentEntity.getModifiedBy());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy  HH:mm");
        String formattedDate = sdf.format(commentEntity.getModifiedDate());
        result.setModifiedDate(formattedDate);
        return result;
    }

    public CommentEntity toEntity(CommentDTO commentDTO){
        CommentEntity result = new CommentEntity();
        result.setContent(commentDTO.getContent());
        result.getNewId().setId(commentDTO.getNewId());
        result.getUserId().setId(commentDTO.getUserId());
        return result;
    }
}
