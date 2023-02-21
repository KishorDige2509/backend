package com.astrika.solutions;

import java.util.Arrays;
import java.util.List;

public class ColumnScriptGenerator {

	public static void main(String[] args) {
		List<String> roles = Arrays.asList("SE", "ASM", "SM", "LSH", "RSH", "ZSH", "NSH", "BH", "CBO", "SUPER_ADMIN", "ADMIN");
		List<String> tabs = Arrays.asList("ACTIVE", "DEACTIVATED");
		Integer startId = 46;
		Integer stopId = 49;
		String listingMaster = "SALES_ROLE_MASTER_LISTING";

		for (String role : roles) {
			for (String tab : tabs) {
				int seq = 1;
				for (int id = startId; id <= stopId; id++) {

					System.out.println("(" + id + ", true, false, \"" + listingMaster + "\", \"" + role + "\", " + seq
							+ ", \"" + tab + "\"),");
					seq++;
				}
				System.out.println();
			}
		}
	}

}
