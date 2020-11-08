package com.esamson.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Player of a Game.
 * 
 * @author esamson74@gmail.com
 */
public class Player {

	private static final String DEFAULT_NAME = "Player-";
	private final int id;
	private String name;
	private List<Card> cards = new ArrayList<Card>();

	/**
	 * Constructor.
	 * 
	 * @param id
	 * @param cards
	 */
	public Player(int id, List<Card> cards) {
		this.id = id;
		name = DEFAULT_NAME + id;
		this.cards = cards;
	}

	/**
	 * Gets the Id of the Player.
	 * 
	 * @return int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the Name of the Player.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets all the Cards of the Player.
	 * 
	 * @return List
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * Gets the Score of the Player's Hand.
	 * 
	 * @return int
	 */
	public int getScore() {
		int score = 0;
		for (Card card : getCards()) {
			score += card.getScore();
		}
		return score;
	}

	/**
	 * Adds the given Cards to the Player's Hand.
	 * 
	 * @param cards
	 */
	public void addCards(List<Card> cards) {
		this.cards.addAll(cards);
	}
}
