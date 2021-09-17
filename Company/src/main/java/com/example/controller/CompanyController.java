package com.example.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CompanyDTO;
import com.example.dto.UserCompanyDTO;
import com.example.service.CompanyService;
import com.example.service.UserService;

@RestController
public class CompanyController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/save/user")
	public Map<String,Object> saveUser(@RequestBody UserCompanyDTO userCompanyDTO){
		return userService.saveUser(userCompanyDTO);
	}
	
	@PostMapping("/save/company")
	public Map<String,Object> saveCompany(@RequestBody CompanyDTO companyDTO){
		return companyService.saveCompany(companyDTO);
	}
	
	@PostMapping("/list/users")
	public Map<String,Object> listUsers(){
		return userService.listUsers();
	}

}
