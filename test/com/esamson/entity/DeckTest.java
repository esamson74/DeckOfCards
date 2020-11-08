package com.esamson.entity;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

public class DeckTest extends TestCase {

	private Deck deck = new Deck();;

	@Test
	public void testAttributes() {
		List<Card> cards = deck.getCards();
		assertNotNull(cards);
		assertEquals(52, cards.size());
	}

	@Test
	public void testAddCards() {
		List<Card> cards = deck.dealCards(3);
		assertEquals(3, cards.size());
		assertEquals(49, deck.getCards().size());
		deck.dealCards(8);
		assertEquals(41, deck.getCards().size());
		deck.dealCards(45);
		assertEquals(0, deck.getCards().size());
	}
}
