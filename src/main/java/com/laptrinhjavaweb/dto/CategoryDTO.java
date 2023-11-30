package com.laptrinhjavaweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CategoryDTO extends AbstractDTO<CategoryDTO>{
    private String code;
    private String name;

}
