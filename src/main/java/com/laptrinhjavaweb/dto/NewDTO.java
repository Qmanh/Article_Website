package com.laptrinhjavaweb.dto;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class NewDTO extends AbstractDTO<NewDTO> {
    private String title;
    private String thumbnail;
    private String shortDescription;
    private String content;
    private Long categoryId;
    private String categoryCode;
    private String file;

}
