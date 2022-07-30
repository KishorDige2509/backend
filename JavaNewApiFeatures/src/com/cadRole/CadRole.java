package com.cadRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CadRole {

	public static void main(String[] args) {
		String cad1 = "ACM & CPU & (RPM/NCM)";
		if (cad1.contains("&")) {
			System.out.println("& found");

			String[] split = cad1.split("&");
			List<String> andAuthoritiesList = Arrays.asList(split);
			System.out.println("andAuthoritiesList:" + andAuthoritiesList);

			List<String> orAuthoritiesList = new ArrayList<>();
			for (int i = 0; i < split.length; i++) {

				if (split[i].contains("&")) {
					System.out.println("/ found");
					String[] split_or = split[1].split("/");
					System.out.println("split:" + Arrays.asList(split_or));
				}

				orAuthoritiesList.add(split[i].trim());
			}
			System.out.println("orAuthoritiesList:" + orAuthoritiesList);

		}
	}

}
