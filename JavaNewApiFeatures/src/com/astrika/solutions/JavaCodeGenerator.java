package com.astrika.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JavaCodeGenerator {
	
	public static <V, K> Map<V, K> invertMapUsingForLoop(Map<K, V> map) {
	    Map<V, K> inversedMap = new HashMap<V, K>();
	    for (Entry<K, V> entry : map.entrySet()) {
	        inversedMap.put(entry.getValue(), entry.getKey());
	    }
	    return inversedMap;
	}
	
	public static String getFieldNameForId(List<Integer> ids, Map<Integer, String> fieldMap) {
		List<String> fields = new ArrayList<>();
		for (Integer integer : ids) {
			fields.add("\""+fieldMap.get(integer)+"\"");
		}		
		return "Arrays.asList("+fields.toString().replaceAll("[\\[\\]]", "")+")";
	}
	
	public static void main(String[] args) {
		
		
		
		Map<Integer, String> fieldById = new HashMap<>();
		fieldById.put(7, "createdOn");
		fieldById.put(8, "leadFullName");
		fieldById.put(9, "leadPincodeId");
		fieldById.put(10, "leadEmail");
		fieldById.put(11, "leadMobile");
		fieldById.put(12, "leadCrmId");
		fieldById.put(13, "countryOfStudyId");
		fieldById.put(14, "assignedAvanseBranchId");
		fieldById.put(15, "currentSmUserId");
		fieldById.put(16, "currentSeUserId");
		fieldById.put(17, "closeLeadReason");
		fieldById.put(18, "applicationCode");
		fieldById.put(19, "studentFullName");
		fieldById.put(20, "currAppStage");
		fieldById.put(21, "appRejectReason");
		fieldById.put(22, "roi");
		fieldById.put(23, "tenure");
		fieldById.put(24, "amountSanctioned");
		fieldById.put(25, "disbursedAmount");
		fieldById.put(26, "lastDateOfDisbursement");
		
		Map<String, Integer> idByField = invertMapUsingForLoop(fieldById);	
		
		Map<String, List<Integer>> fieldsByTab1 = new HashMap<>();
		fieldsByTab1.put("NEW_LEADS", Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
		fieldsByTab1.put("LEAD_CONTACTED", Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
		fieldsByTab1.put("APP_IN_PROGRESS", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19));
		fieldsByTab1.put("CREDIT_SANCTIONING", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19));
		fieldsByTab1.put("SANCTIONED", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 22, 23, 24));
		fieldsByTab1.put("AWAITING_CUSTOMER", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 22, 23, 24));
		fieldsByTab1.put("PARTIAL_DISBURSED",
				Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 22, 23, 24, 25, 26));
		fieldsByTab1.put("FULLY_DISBURSED", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 22, 23, 24, 25, 26));
		fieldsByTab1.put("LEAD_CLOSED", Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17));
		fieldsByTab1.put("APP_REJECTED", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 21));
		
		for (Entry<String, List<Integer>> entry : fieldsByTab1.entrySet()) {
			System.out.println("fieldssByTab.put(" + "\""+entry.getKey() + "\"" + "," + getFieldNameForId(entry.getValue(), fieldById) + ");");
		}
	}

}
