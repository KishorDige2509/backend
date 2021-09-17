package com.example.dto;

import java.util.Set;

import com.example.domain.Company;

import lombok.Data;

@Data
public class UserCompanyDTO {
	private Long userId;
	private String name;
	private String email;
	private String phone;
	private Set<Company> companies;
}
