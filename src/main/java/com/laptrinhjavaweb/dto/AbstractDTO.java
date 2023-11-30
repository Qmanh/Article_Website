package com.laptrinhjavaweb.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter@Setter
public abstract class AbstractDTO<T> {
    private Long id;
    private String createdDate;
    private String modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private long[] ids;
    private List<T> listResult = new ArrayList<>();
    private Integer page;
    private Integer limit =5;
    private Integer totalPage;
    private Integer totalItem;
    private String sortName;
    private String sortBy;
    private String alert;
    private String message;
    private String type;

}
