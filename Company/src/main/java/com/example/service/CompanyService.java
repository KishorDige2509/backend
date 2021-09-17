package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Company;
import com.example.dto.CompanyDTO;
import com.example.repository.CompanyRepo;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepo companyRepo;

	public Map<String, Object> saveCompany(CompanyDTO companyDTO) {
		Map<String, Object> map = new HashMap<>();

		if (companyDTO != null) {
			if (companyDTO.getCompanyId() == null) {
				Company company = new Company();
				BeanUtils.copyProperties(companyDTO, company);
				companyRepo.save(company);
				map.put("message", "company saved successfully");
				map.put("Company", company);
			} else {
				Company company = companyRepo.findById(companyDTO.getCompanyId()).orElse(null);
				if (company != null) {
					BeanUtils.copyProperties(companyDTO, company);
					companyRepo.save(company);
					map.put("message", "company updated successfully");
					map.put("Company", company);
				}

			}
		}

		return map;
	}

}
