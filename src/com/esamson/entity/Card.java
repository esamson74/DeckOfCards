package com.esamson.entity;

/**
 * Card of a Deck.
 * 
 * @author esamson74@gmail.com
 */
public class Card {

	private final Suit suit;
	private final Rank rank;
	private final int score;
	private final String name;

	/**
	 * Constructor.
	 * 
	 * @param suit
	 * @param rank
	 */
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
		score = rank.getScore();
		name = suit.getName();
	}

	/**
	 * Gets the Suite of the Card.
	 * 
	 * @return Suit
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * Gets the Rank of the Card.
	 * 
	 * @return Rank
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Gets the Score of a Card.
	 * 
	 * @return int
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Gets the Name of a Card.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}
}
