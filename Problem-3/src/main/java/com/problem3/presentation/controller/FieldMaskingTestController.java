package com.problem3.presentation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.problem3.business.dto.FieldUtilRequestDTO;
import com.problem3.business.service.FieldMaskingService;

@RestController
public class FieldMaskingTestController {

	@Autowired
	private FieldMaskingService service;

	@PostMapping("mask")
	public Map<String, Object> mask(@RequestBody FieldUtilRequestDTO dto) throws Exception {
		return service.mask(dto.getParentFieldNames(), dto.getJsonString());
	}

}
