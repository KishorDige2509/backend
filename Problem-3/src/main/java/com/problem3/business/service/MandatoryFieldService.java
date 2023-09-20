package com.problem3.business.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MandatoryFieldService {

	Map<String, Object> validateMandatory(List<String> parentFieldNames, String jsonString) throws IOException;

}
