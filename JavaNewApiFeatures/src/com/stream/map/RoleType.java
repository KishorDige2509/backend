package com.stream.map;

import java.util.ArrayList;
import java.util.List;

public enum RoleType {
	
	SUPERADMIN(1L,"SUPERADMIN"),
	SALES_MANAGER(2L,"SALES_MANAGER"),
	CPA(3L,"CPA"),
	CREDIT_MANAGER(4L,"CREDIT_MANAGER"),
	UNDER_WRITER(5L,"UNDER_WRITER"),
	RCU_AGENT(6L,"RCU_AGENT"),
	PSV(7L,"PSV"),
	OPERATIONS(8L,"OPERATIONS"),
	FI_AGENCY(9L,"FI_AGENCY");
	
	
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	private RoleType(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static RoleType getRoleType(Long roleId) {
		for(RoleType roleType:RoleType.values()) {
			if(roleType.getId().equals(roleId)) {
				return roleType;
			}
		}
		return null;
	}

	public static List<Long> getIdsByNames(List<String> names) {
		List<Long> ids = new ArrayList<>();
		for(String name: names) {
			ids.add(RoleType.valueOf(name.toUpperCase()).id);
		}
		return ids;
	}
	
	

}
