package com.patterns;

public class X {
	
	public static void main(String []args){
        int row = 18;
        int column = 18;
        int k=row;
        for(int i=1; i<=row; i++) {
            for(int j=1; j<=column; j++) {
                if(j==i || j==((k+1)-i)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
     }

}
