package com.indusland;

public class Solution {
	
	class C implements A, B 

	{ 

		@Override
		public void show(){ 

			System.out.println("In C");

		} 

	}
	
	public static void main(String[] args) {
		Solution s = new Solution();
		C c = s.new C();
		c.show();
	}

}

interface A { void show();}

interface B { void show();}
 
class C implements A, B 

{ 

	@Override
	public void show(){ 

		System.out.println("In C");

	} 

}
