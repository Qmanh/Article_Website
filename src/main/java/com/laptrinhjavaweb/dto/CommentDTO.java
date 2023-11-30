package com.laptrinhjavaweb.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO extends AbstractDTO<CommentDTO>{
    private String content;
    private Long userId;
    private Long newId ;
}
