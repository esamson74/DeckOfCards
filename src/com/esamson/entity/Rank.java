package com.esamson.entity;

/**
 * Rank of a Card.
 * 
 * @author esamson74@gmail.com
 */
public enum Rank {
	ACE(1), 
	TWO(2), 
	THREE(3), 
	FOUR(4), 
	FIVE(5), 
	SIX(6), 
	SEVEN(7), 
	EIGHT(8), 
	NINE(9), 
	TEN(10), 
	JACK(11), 
	QUEEN(12),
	KING(13);

	private final int score;

	/**
	 * Constructor.
	 * 
	 * @param score
	 */
	private Rank(int score) {
		this.score = score;
	}

	/**
	 * Gets the corresponding Score.
	 * 
	 * @return int
	 */
	public int getScore() {
		return score;
	}
}
