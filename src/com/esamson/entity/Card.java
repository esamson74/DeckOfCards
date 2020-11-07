package com.esamson.entity;

public class Card {

	private final Suit suit;
	private final Rank rank;
	private final int score;
	private final String name;
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
		score = rank.getScore();
		name = suit.getName();
	}
	
	public Suit getSuite() {
		return suit;
	}
	
	public Rank getRank() {
		return rank;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getName() {
		return name;
	}
}
