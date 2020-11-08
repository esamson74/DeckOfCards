package com.esamson.entity;

/**
 * Suit of a Card.
 * 
 * @author esamson74@gmail.com
 */
public enum Suit {
	CLUB("Club", 3), 
	DIAMOND("Diamond", 4), 
	HEART("Heart", 1), 
	SPADE("Spade", 2);

	private final String name;
	private final int sequence;

	/**
	 * Constructor.
	 * 
	 * @param name
	 * @param sequence
	 */
	Suit(String name, int sequence) {
		this.name = name;
		this.sequence = sequence;
	}

	/**
	 * Gets the Name of the Suit.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the Sequence order of the Suit.
	 * 
	 * @return int
	 */
	public int getSequence() {
		return sequence;
	}
}