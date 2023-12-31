package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter@Setter
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="createddate")
    @CreatedDate
    private Date createdDate;

    @Column(name ="createdby")
    @CreatedBy
    private String createdBy;

    @Column(name ="modifieddate")
    @LastModifiedDate
    private Date modifiedDate;

    @Column(name ="modifiedby")
    @LastModifiedBy
    private String modifiedBy;


}
