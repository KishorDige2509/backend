package com.stream.map;

import java.util.List;

public class UserMaster {
	
	private Long id;
	
	private List<UserRoleMapping> userRoleMappings;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UserRoleMapping> getUserRoleMappings() {
		return userRoleMappings;
	}

	public void setUserRoleMappings(List<UserRoleMapping> userRoleMappings) {
		this.userRoleMappings = userRoleMappings;
	}

}
