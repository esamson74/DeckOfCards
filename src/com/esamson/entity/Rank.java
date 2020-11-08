package com.esamson.entity;

/**
 * Rank of a Card.
 * 
 * @author esamson74@gmail.com
 */
public enum Rank {
	ACE(1, "Ace"), 
	TWO(2, "2"), 
	THREE(3,"3"), 
	FOUR(4, "4"), 
	FIVE(5, "5"), 
	SIX(6, "6"), 
	SEVEN(7, "7"), 
	EIGHT(8, "8"), 
	NINE(9, "9"), 
	TEN(10, "10"), 
	JACK(11, "Jack"), 
	QUEEN(12, "Queen"),
	KING(13, "King");

	private final int score;
	private final String name;

	/**
	 * Constructor.
	 * 
	 * @param score
	 */
	private Rank(int score, String name) {
		this.score = score;
		this.name = name;
	}

	/**
	 * Gets the corresponding Score.
	 * 
	 * @return int
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Gets the corresponding Name.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}
}
