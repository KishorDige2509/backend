package com.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Company;
import com.example.domain.User;
import com.example.dto.UserCompanyDTO;
import com.example.repository.CompanyRepo;
import com.example.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CompanyRepo companyRepo;
	
	public Map<String, Object> saveUser(UserCompanyDTO userCompanyDTO) {
		Map<String, Object> map = new HashMap<>();
		
		if(userCompanyDTO != null) {
			if(userCompanyDTO.getUserId() == null) {
				User user = new User();
				BeanUtils.copyProperties(userCompanyDTO, user);
				
				Set<Long> companyIds = new HashSet<>();
				for(Company company: userCompanyDTO.getCompanies()) {
					companyIds.add(company.getCompanyId());
				}
				user.setCompanyId(companyIds);
				userRepo.save(user);
				map.put("message", "user saved successfully");
				map.put("User", user);
			} else {
				User user = userRepo.findById(userCompanyDTO.getUserId()).orElse(null);
				if(user != null) {
					BeanUtils.copyProperties(userCompanyDTO, user);
					userRepo.save(user);
					map.put("message", "user updated successfully");
					map.put("User", user);
				}
				
			}
		}
		
		return map;
	}

	public Map<String, Object> listUsers() {
		Map<String, Object> map = new HashMap<>();
		List<User> users = userRepo.findAll();
		List<UserCompanyDTO> userCompanyDTOList = new ArrayList<>();
		
		for(User user: users) {
			UserCompanyDTO userCompanyDTO = new UserCompanyDTO();
			BeanUtils.copyProperties(user, userCompanyDTO);
			Set<Company> companies = new HashSet<>();
			Set<Long> companyIds = user.getCompanyId();
			for(Long id:companyIds) {
				Company company = companyRepo.findById(id).orElse(null);
				companies.add(company);
			}
			userCompanyDTO.setCompanies(companies);
			userCompanyDTOList.add(userCompanyDTO);
		}
		
		map.put("User List:", userCompanyDTOList);
		return map;
	}

}
