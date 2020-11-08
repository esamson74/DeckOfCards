package com.esamson.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Deck of Cards.
 * 
 * @author esamson74@gmail.com
 */
public class Deck {

	private List<Card> cards = new ArrayList<Card>();

	/**
	 * Constructor.
	 */
	public Deck() {
		initialize();
	}

	/**
	 * Initializes the Deck.
	 */
	private void initialize() {
		for (Suit suit : Suit.values()) {
			for (Rank rank : Rank.values()) {
				cards.add(new Card(suit, rank));
			}
		}
		Collections.shuffle(cards);
	}

	/**
	 * Gets all Cards of the Deck.
	 * 
	 * @return List
	 */
	public List<Card> getCards() {
		return cards;
	}

	/**
	 * Gets the given amount of dealt Cards and removes them from the Deck.
	 * 
	 * @param amount
	 * @return List
	 */
	public List<Card> dealCards(int amount) {
		Random r = new Random();
		List<Card> dealtCards = new ArrayList<Card>();

		while ((dealtCards.size() < amount) && (cards.size() > 0)) {
			int index = r.nextInt(cards.size());
			dealtCards.add(cards.get(index));
			cards.remove(index);
		}
		return dealtCards;
	}

	/**
	 * Gets the undealt Cards of the Deck.
	 * 
	 * @return Map
	 */
	public Map<Suit, Integer> getUndealtCards() {
		// TODO: Needs improvement :(
		
		int clubCounter = 0;
		int diamondCounter = 0;
		int heartCounter = 0;
		int spadeCounter = 0;
		
		for (Card card : cards) {
			Suit suit = card.getSuit();
			if (Suit.CLUB.equals(suit)) {
				++clubCounter;
			}
			if (Suit.DIAMOND.equals(suit)) {
				++diamondCounter;
			}
			if (Suit.HEART.equals(suit)) {
				++heartCounter;
			}
			if (Suit.SPADE.equals(suit)) {
				++spadeCounter;
			}
		}
		
		Map<Suit, Integer> suits = new HashMap<Suit, Integer>();
		suits.put(Suit.CLUB, clubCounter);
		suits.put(Suit.DIAMOND, diamondCounter);
		suits.put(Suit.HEART, heartCounter);
		suits.put(Suit.SPADE, spadeCounter);
				
		return suits;
	}
	
	public Map<String, String> getDeckInfo() {
		// TODO: Needs improvement :(
		
		List<Card> clubs = new ArrayList<Card>();
		List<Card> diamonds = new ArrayList<Card>();
		List<Card> hearts = new ArrayList<Card>();
		List<Card> spades = new ArrayList<Card>();
		
		for (Card card : cards) {
			Suit suit = card.getSuit();
			if (Suit.CLUB.equals(suit)) {
				clubs.add(card);
			}
			if (Suit.DIAMOND.equals(suit)) {
				diamonds.add(card);
			}
			if (Suit.HEART.equals(suit)) {
				hearts.add(card);
			}
			if (Suit.SPADE.equals(suit)) {
				spades.add(card);
			}
		}
		Collections.sort(clubs, new Comparator<Card>() {

	        public int compare(Card card1, Card card2) {
	            return Integer.valueOf(card2.getScore()).compareTo(Integer.valueOf(card1.getScore()));
	        }
	    });
		Collections.sort(diamonds, new Comparator<Card>() {

	        public int compare(Card card1, Card card2) {
	            return Integer.valueOf(card2.getScore()).compareTo(Integer.valueOf(card1.getScore()));
	        }
	    });
		Collections.sort(hearts, new Comparator<Card>() {

	        public int compare(Card card1, Card card2) {
	            return Integer.valueOf(card2.getScore()).compareTo(Integer.valueOf(card1.getScore()));
	        }
	    });
		Collections.sort(spades, new Comparator<Card>() {

	        public int compare(Card card1, Card card2) {
	            return Integer.valueOf(card2.getScore()).compareTo(Integer.valueOf(card1.getScore()));
	        }
	    });
		
		
		
		Map<String, String> deckInfo = new HashMap<String, String>();
		deckInfo.put(Suit.HEART.getName(), getCardNames(hearts));
		deckInfo.put(Suit.SPADE.getName(), getCardNames(spades));
		deckInfo.put(Suit.CLUB.getName(), getCardNames(clubs));
		deckInfo.put(Suit.DIAMOND.getName(), getCardNames(diamonds));
		return deckInfo;
	}
	
	private String getCardNames(List<Card> cards) {
		StringBuilder names = new StringBuilder();
		for (Card card : cards) {
			names.append(card.getRank().getScore());
			names.append(",");
		}
		names.delete(names.length() - 1, names.length());
		
		return names.toString();
	}
}
