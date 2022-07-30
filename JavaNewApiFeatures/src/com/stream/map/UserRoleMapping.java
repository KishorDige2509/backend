package com.stream.map;

public class UserRoleMapping {
	
	public UserRoleMapping(Long id, RoleType roleType) {
		super();
		this.id = id;
		this.roleType = roleType;
	}

	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleType getRoleType() {
		return roleType;
	}

	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}

	private RoleType roleType;

}
