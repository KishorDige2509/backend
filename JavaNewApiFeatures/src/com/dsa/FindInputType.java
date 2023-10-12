package com.dsa;

public class FindInputType {
	
	public static void main(String[] args) {
		char ch = '9';
		System.out.println("Input is:" + getType(ch));
	}

	private static String getType(char ch) {
		if(ch>='a' && ch<='z' ) {
			return "lowercase letter";
		} else if(ch>='A' && ch<='Z') {
			return "uppercase letter";
		} else {
			return "Numeric Value";
		}
	} 

}
