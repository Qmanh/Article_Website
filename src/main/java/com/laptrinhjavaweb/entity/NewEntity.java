package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "new")
@Setter@Getter
public class NewEntity extends BaseEntity {

	@Column(name = "title")
	private String title;

	@Lob
	@Column(name = "thumbnail",columnDefinition = "MEDIUMBLOB")
	private String thumbnail;
	
	@Column(name = "shortdescription", columnDefinition = "TEXT")
	private String shortDescription;
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name ="category_id")
	private CategoryEntity category;







}
