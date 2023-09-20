package com.problem3.business.dto;

import java.util.List;

import lombok.Data;

@Data
public class FieldUtilRequestDTO {

	private List<String> parentFieldNames;
	private String jsonString;

}
