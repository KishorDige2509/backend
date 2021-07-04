package com.problem3.business.dto;

import java.util.List;

import lombok.Data;

@Data
public class ListingDto {
	private Integer start = 0;
	private Integer length = 0;
	
	private List<SearchDto> search;
	
	private List<SortDto> sort;
	
	private List<SequenceColumnDTO> sequenceColumnDTOs;
	
	private SortDto defaultSort;
	
	private Boolean status = Boolean.TRUE;
	
	private int statusCode;
	
	private Boolean isRegistered = Boolean.TRUE;
	
	private int requestType = 0;
	
	private List<SearchDto> addtionalSearch;

}
