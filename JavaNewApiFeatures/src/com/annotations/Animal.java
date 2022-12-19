package com.annotations;

public class Animal {

	private String name;
	private String colour;
	private String sound;

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public void makeSound(String sound) {
		System.out.println("Animal making sound:" + sound);
	}

}
