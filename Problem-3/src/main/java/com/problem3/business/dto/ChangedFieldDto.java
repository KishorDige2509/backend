package com.problem3.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChangedFieldDto {
private Long historyFeedId;
	
	private String fieldName;
	private String oldValue;
	private String newValue;
}
