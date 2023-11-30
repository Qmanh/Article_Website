package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.converter.CategoryConverter;
import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.entity.CategoryEntity;
import com.laptrinhjavaweb.entity.NewEntity;
import com.laptrinhjavaweb.repository.CategoryRepository;
import com.laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryConverter categoryConverter;

    @Override
    public Map<String,String> findAll() {
        Map<String,String> result = new HashMap<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for(CategoryEntity item : entities){
            result.put(item.getCode(),item.getName());
        }
        return result;
    }

    @Override
    public List<CategoryDTO> findAll(int i) {
        List<CategoryDTO> models = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll();
        for(CategoryEntity item : entities){
            CategoryDTO categoryDTO = categoryConverter.toDTO(item);
            models.add(categoryDTO);
        }
        System.out.println(models);
        return models;
    }

    @Override
    public List<CategoryDTO> findAll(Pageable pageable) {
        List<CategoryDTO> models = new ArrayList<>();
        List<CategoryEntity> entities = categoryRepository.findAll(pageable).getContent();
        for(CategoryEntity item : entities){
           CategoryDTO DTO = categoryConverter.toDTO(item);
            models.add(DTO);
        }
        return models;
    }

    @Override
    public int getTotalItem() {
        return (int)categoryRepository.count();
    }

    @Override
    public CategoryDTO findById(Long id) {
        CategoryEntity category = categoryRepository.findOne(id);
        return categoryConverter.toDTO(category);
    }

    @Override
    @Transactional
    public CategoryDTO save(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity = new CategoryEntity();
        if(categoryDTO.getId() != null){
            CategoryEntity oldCategory = categoryRepository.findOne(categoryDTO.getId());
            categoryEntity = categoryConverter.toEntity(oldCategory,categoryDTO);
        }else{
            categoryEntity = categoryConverter.toEntity(categoryDTO);
        }
        return categoryConverter.toDTO(categoryRepository.save(categoryEntity));
    }

    @Override
    @Transactional
    public void delete(Long[] ids) {
        for(long id: ids){
            categoryRepository.delete(id);
        }
    }
}
