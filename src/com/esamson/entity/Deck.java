package com.esamson.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {

	private List<Card> cards = new ArrayList<Card>();
	
	public Deck() {
		initialize();
	}
	
	private void initialize() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
		Collections.shuffle(cards);
	}
	
	public List<Card> getCards() {
		return cards;
	}
	
	public List<Card> dealCards(int amount) {
		Random r = new Random();
		List<Card> dealtCards = new ArrayList<Card>();
		
		while (dealtCards.size() < amount) {
			if (cards.size() > 0) {
				int index = r.nextInt(cards.size());
				dealtCards.add(cards.get(index));	
			}
		}
		return dealtCards;				
	}
	
}
