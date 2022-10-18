package com.testSpace;

public class NullTests {
	
	public static void main(String[] args) {
		Long count = null;
		System.out.println("count " + count);
		
		System.out.println(isPalindrome(121));
		    
		
		
	}
	
	public static boolean isPalindrome(int x) {
        
        if(x<0) {
            return false;
        }
        int original = x;
        int reverse = 0;
        while(original > 0) {
            reverse = (reverse * 10) + original%10;     
            original = original/10;
        }
        
        return reverse==x?true:false;
    }

}
