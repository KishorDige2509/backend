package com.capgemini;

public class Sol {
	
	public static void main(String[] args) {
		String str = "abcdefghijklm";
		System.out.println(reverseString(str));
		
		
		
	}

	private static String reverseString(String str) {
		int length = str.length();
		StringBuilder reverse = new StringBuilder();
		for(int i=length-1; i>=0; i--) {
			char charAt = str.charAt(i);
			reverse.append(charAt);
		}
		return reverse.toString();
	}

}


//public class TestMePlease
//{
//    public static void main(String[] args)
//    {
//        int value = 567;
//        String var = (String)value;  
//        String temp = "1243";
//        int data = (int)temp; 
//        System.out.println(data + var);
//    }
//}
//
//class Main {
//    public static void main(String args[]) {  
//        System.out.println(fun());
//    }
//    int fun() {
//        return 20;
//    }
//}
