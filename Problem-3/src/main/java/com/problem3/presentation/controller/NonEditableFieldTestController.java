package com.problem3.presentation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.problem3.business.dto.FieldUtilRequestDTO;
import com.problem3.business.service.NonEditableFieldService;

@RestController
public class NonEditableFieldTestController {

	@Autowired
	private NonEditableFieldService service;

	@PostMapping("validate/nonEditable")
	public Map<String, Object> validateNonEditable(@RequestBody FieldUtilRequestDTO dto) throws Exception {
		return service.validateNonEditable(dto.getParentFieldNames(), dto.getJsonString());
	}

}
