package com.annotations;

@VeryImportant
public class Cat extends Animal{
	
	@ImportantFeature
	private String fur;

	public Cat(String fur) {
		super();
		this.fur = fur;
	}

	@Override
	@RunImmediately(times = 2)
	public void makeSound(String sound) {
		// TODO Auto-generated method stub
		super.makeSound(sound);
	}
	
	

}
