package com.esamson.entity;

public enum Suit {
	CLUB("Club"), 
	DIAMOND("Diamond"), 
	HEART("Heart"), 
	SPADE("Spade");
	
	private final String name;
	
	Suit(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}