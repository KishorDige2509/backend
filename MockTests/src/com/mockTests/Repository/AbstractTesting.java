package com.mockTests.Repository;

abstract class TestAbs {
	void print(int i) {
		System.out.println("int is:" + i);
	}
	
	abstract void show();
}


public class AbstractTesting extends TestAbs{

	@Override
	void show() {
		// need to provide definition of abstract method here
		
		
	}

}
