package com.laptrinhjavaweb.api.admin;

import com.laptrinhjavaweb.dto.CategoryDTO;
import com.laptrinhjavaweb.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController(value = "categoryAPIOfAdmin")
public class CategoryAPI {
    @Autowired
    private ICategoryService categoryService;

    @PostMapping(value = "/api/category", consumes = {"application/json"})
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.save(categoryDTO);
    }

    @PutMapping(value = "/api/category", consumes = {"application/json"})
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.save(categoryDTO);
    }

    @DeleteMapping(value = "/api/category")
    public void deleteCategory(@RequestBody Long [] ids){
        categoryService.delete(ids);
    }
}
