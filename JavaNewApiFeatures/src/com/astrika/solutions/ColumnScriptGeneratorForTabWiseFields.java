package com.astrika.solutions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColumnScriptGeneratorForTabWiseFields {

	public static void main(String[] args) {
		List<String> roles = Arrays.asList("PARTNER");
		List<String> tabs = Arrays.asList("NEW_LEADS", "LEAD_CONTACTED", "APP_IN_PROGRESS", "CREDIT_SANCTIONING",
				"SANCTIONED", "AWAITING_CUSTOMER", "PARTIAL_DISBURSED", "FULLY_DISBURSED", "LEAD_CLOSED",
				"APP_REJECTED");
		Integer startId = 7;
		Integer stopId = 26;
		String listingMaster = "PARTNER_LEAD_LISTING";

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

		Map<String, List<String>> fieldssByTab = new HashMap<>();

		fieldssByTab.put("APP_REJECTED",
				Arrays.asList("createdOn", "leadPincodeId", "leadEmail", "leadMobile", "leadCrmId", "countryOfStudyId",
						"assignedAvanseBranchId", "currentSmUserId", "currentSeUserId", "applicationCode",
						"studentFullName", "appRejectReason"));

		fieldssByTab.put("APP_IN_PROGRESS",
				Arrays.asList("createdOn", "leadPincodeId", "leadEmail", "leadMobile", "leadCrmId", "countryOfStudyId",
						"assignedAvanseBranchId", "currentSmUserId", "currentSeUserId", "applicationCode",
						"studentFullName"));

		fieldssByTab.put("PARTIAL_DISBURSED",
				Arrays.asList("createdOn", "leadPincodeId", "leadEmail", "leadMobile", "leadCrmId", "countryOfStudyId",
						"assignedAvanseBranchId", "currentSmUserId", "currentSeUserId", "applicationCode",
						"studentFullName", "roi", "tenure", "amountSanctioned", "disbursedAmount",
						"lastDateOfDisbursement"));

		fieldssByTab.put("LEAD_CONTACTED",
				Arrays.asList("createdOn", "leadFullName", "leadPincodeId", "leadEmail", "leadMobile", "leadCrmId",
						"countryOfStudyId", "assignedAvanseBranchId", "currentSmUserId", "currentSeUserId"));

		fieldssByTab.put("AWAITING_CUSTOMER",
				Arrays.asList("createdOn", "leadPincodeId", "leadEmail", "leadMobile", "leadCrmId", "countryOfStudyId",
						"assignedAvanseBranchId", "currentSmUserId", "currentSeUserId", "applicationCode",
						"studentFullName", "roi", "tenure", "amountSanctioned"));

		fieldssByTab.put("LEAD_CLOSED",
				Arrays.asList("createdOn", "leadFullName", "leadPincodeId", "leadEmail", "leadMobile", "leadCrmId",
						"countryOfStudyId", "assignedAvanseBranchId", "currentSmUserId", "currentSeUserId",
						"closeLeadReason"));

		fieldssByTab.put("FULLY_DISBURSED",
				Arrays.asList("createdOn", "leadPincodeId", "leadEmail", "leadMobile", "leadCrmId", "countryOfStudyId",
						"assignedAvanseBranchId", "currentSmUserId", "currentSeUserId", "applicationCode",
						"studentFullName", "roi", "tenure", "amountSanctioned", "disbursedAmount",
						"lastDateOfDisbursement"));

		fieldssByTab.put("SANCTIONED",
				Arrays.asList("createdOn", "leadPincodeId", "leadEmail", "leadMobile", "leadCrmId", "countryOfStudyId",
						"assignedAvanseBranchId", "currentSmUserId", "currentSeUserId", "applicationCode",
						"studentFullName", "roi", "tenure", "amountSanctioned"));

		fieldssByTab.put("NEW_LEADS",
				Arrays.asList("createdOn", "leadFullName", "leadPincodeId", "leadEmail", "leadMobile", "leadCrmId",
						"countryOfStudyId", "assignedAvanseBranchId", "currentSmUserId", "currentSeUserId"));

		fieldssByTab.put("CREDIT_SANCTIONING",
				Arrays.asList("createdOn", "leadPincodeId", "leadEmail", "leadMobile", "leadCrmId", "countryOfStudyId",
						"assignedAvanseBranchId", "currentSmUserId", "currentSeUserId", "applicationCode",
						"studentFullName"));

//		Map<String, List<Integer>> fieldsByTab = new HashMap<>();
//		fieldsByTab.put("NEW_LEADS", Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
//		fieldsByTab.put("LEAD_CONTACTED", Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16));
//		fieldsByTab.put("APP_IN_PROGRESS", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19));
//		fieldsByTab.put("CREDIT_SANCTIONING", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19));
//		fieldsByTab.put("SANCTIONED", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 22, 23, 24));
//		fieldsByTab.put("AWAITING_CUSTOMER", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 22, 23, 24));
//		fieldsByTab.put("PARTIAL_DISBURSED",
//				Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 22, 23, 24, 25, 26));
//		fieldsByTab.put("FULLY_DISBURSED", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 22, 23, 24, 25, 26));
//		fieldsByTab.put("LEAD_CLOSED", Arrays.asList(7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17));
//		fieldsByTab.put("APP_REJECTED", Arrays.asList(7, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 21));

		for (String role : roles) {
			for (String tab : tabs) {
				int seq = 1;
				for (Integer id = startId; id <= stopId; id++) {					
					String field = fieldById.get(id);
					if (fieldssByTab.get(tab).contains(field)) {
						System.out.println("(" + id + ", true, false, \"" + listingMaster + "\", \"" + role + "\", "
								+ seq + ", \"" + tab + "\"),");
						seq++;
					}
				}
				System.out.println();
			}
		}
	}

}
