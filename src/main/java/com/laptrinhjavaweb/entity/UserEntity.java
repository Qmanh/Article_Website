package com.laptrinhjavaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "user")
@Setter@Getter
public class UserEntity extends BaseEntity {

	@Column(name = "username")
	private String userName;
	
	@Column(name = "password")
	private String password;

	@Column(name = "fullname")
	private String fullName;
	
	@Column(name = "status")
	private Integer status;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="user_role", joinColumns = @JoinColumn(name = "userid"),
							inverseJoinColumns = @JoinColumn(name = "roleid"))
	private Set<RoleEntity> roles = new HashSet<>();


}
