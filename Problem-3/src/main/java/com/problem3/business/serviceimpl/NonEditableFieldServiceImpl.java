package com.problem3.business.serviceimpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.problem3.business.service.NonEditableFieldService;
import com.problem3.util.FieldUtil;

@Service
public class NonEditableFieldServiceImpl implements NonEditableFieldService{
	
	@Autowired
	private FieldUtil fieldUtil;

	@Override
	public Map<String, Object> validateNonEditable(List<String> parentFieldNames, String jsonString) throws IOException {
		Map<String, Object> map = new HashMap<>();
		Set<String> response = fieldUtil.getNonEditableFieldsInDtoV2(parentFieldNames, jsonString);
		map.put("response", response);
		return map;
	}

}
