package com.esamson.entity;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private static final String DEFAULT_NAME = "Player-";
	private final int id;
	private String name;
	private List<Card> cards = new ArrayList<Card>();
	
	public Player(int id, List<Card> cards) {
		this.id = id;
		name = DEFAULT_NAME + id;
		this.cards = cards;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
}
