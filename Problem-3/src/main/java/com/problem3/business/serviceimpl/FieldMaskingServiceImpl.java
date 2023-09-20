package com.problem3.business.serviceimpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.problem3.business.service.FieldMaskingService;
import com.problem3.util.FieldUtil;

@Service
public class FieldMaskingServiceImpl implements FieldMaskingService {

	@Autowired
	private FieldUtil fieldUtil;

	@Override
	public Map<String, Object> mask(List<String> parentFieldNames, String jsonString) throws IOException {
		Map<String, Object> map = new HashMap<>();
		String response = fieldUtil.maskNonViewableFieldsInDTOV2(parentFieldNames, jsonString);
		map.put("response", response);
		return map;
	}

}
