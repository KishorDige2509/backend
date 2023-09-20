package com.problem3.business.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FieldMaskingService {

	Map<String, Object> mask(List<String> parentFieldNames, String jsonString) throws IOException;

}
