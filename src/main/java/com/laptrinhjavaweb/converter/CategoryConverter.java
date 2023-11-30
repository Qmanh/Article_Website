package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryConverter {
    public CategoryDTO toDTO(CategoryEntity categoryEntity){
        CategoryDTO result = new CategoryDTO();
        result.setId(categoryEntity.getId());
        result.setCode(categoryEntity.getCode());
        result.setName(categoryEntity.getName());
        return result;
    }

    public CategoryEntity toEntity(CategoryDTO categoryDTO){
        CategoryEntity result = new CategoryEntity();
        result.setCode(categoryDTO.getCode());
        result.setName(categoryDTO.getName());
        return result;
    }

    public CategoryEntity toEntity(CategoryEntity result, CategoryDTO DTO){
        result.setCode(DTO.getCode());
        result.setName(DTO.getName());
        return result;
    }

}
