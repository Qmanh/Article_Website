package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class NewConverter {
    public NewDTO toDTO(NewEntity newEntity){
        NewDTO result = new NewDTO();
        result.setId(newEntity.getId());
        result.setTitle(newEntity.getTitle());
        result.setShortDescription(newEntity.getShortDescription());
        result.setContent(newEntity.getContent());
        result.setThumbnail(newEntity.getThumbnail());
        result.setCategoryCode(newEntity.getCategory().getCode());
        result.setCreatedBy(newEntity.getCreatedBy());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy  HH:mm");
        String formattedDate = sdf.format(newEntity.getModifiedDate());
        result.setModifiedDate(formattedDate);
        return result;
    }

    public NewEntity toEntity(NewDTO newDTO){
        NewEntity result = new NewEntity();
        result.setTitle(newDTO.getTitle());
        result.setShortDescription(newDTO.getShortDescription());
        result.setContent(newDTO.getContent());
        result.setThumbnail(newDTO.getThumbnail());
        return result;
    }

    public NewEntity toEntity(NewEntity result, NewDTO DTO){
        result.setTitle(DTO.getTitle());
        result.setShortDescription(DTO.getShortDescription());
        result.setContent(DTO.getContent());
        result.setThumbnail(DTO.getThumbnail());
        return result;
    }
}
