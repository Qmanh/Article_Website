package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "role")
@Setter@Getter
public class RoleEntity extends BaseEntity{

	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "roles",cascade = CascadeType.ALL)
	private Set<UserEntity> users = new HashSet<>();

}
