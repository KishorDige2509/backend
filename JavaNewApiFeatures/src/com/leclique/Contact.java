package com.leclique;

import java.util.Objects;

public class Contact {
	
	@Override
	public String toString() {
		return "Contact [name=" + name + ", number=" + number + "]";
	}
	private String name;
	private String number;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Contact(String name, String number) {
		super();
		this.name = name;
		this.number = number;
	}
	@Override
	public int hashCode() {
		return Objects.hash(name, number);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contact other = (Contact) obj;
		return Objects.equals(name, other.name) && Objects.equals(number, other.number);
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	

}
