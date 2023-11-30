package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comment")
@Setter
@Getter
public class CommentEntity extends BaseEntity{

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @JoinColumn(name ="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userId;

    @JoinColumn(name="new_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private NewEntity newId;

}
