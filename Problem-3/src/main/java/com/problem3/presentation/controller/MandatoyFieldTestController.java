package com.problem3.presentation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.problem3.business.dto.FieldUtilRequestDTO;
import com.problem3.business.service.MandatoryFieldService;

@RestController
public class MandatoyFieldTestController {

	@Autowired
	private MandatoryFieldService service;

	@PostMapping("validate/mandatory")
	public Map<String, Object> validateMandatory(@RequestBody FieldUtilRequestDTO dto) throws Exception {
		return service.validateMandatory(dto.getParentFieldNames(), dto.getJsonString());
	}

}
