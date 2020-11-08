package com.esamson.entity;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Player Tests.
 * 
 * @author esamson74@gmail.com
 */
public class PlayerTest extends TestCase {

	private Player player;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		List<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Suit.CLUB, Rank.ACE));
		cards.add(new Card(Suit.SPADE, Rank.KING));
		cards.add(new Card(Suit.DIAMOND, Rank.QUEEN));
		cards.add(new Card(Suit.HEART, Rank.JACK));
		cards.add(new Card(Suit.CLUB, Rank.TEN));
		player = new Player(1, cards);
	}

	@Test
	public void testAttributes() {
		assertEquals(1, player.getId());
		assertEquals("Player-1", player.getName());
		List<Card> cards = player.getCards();
		assertNotNull(cards);
		assertEquals(5, cards.size());
		assertEquals(47, player.getScore());
	}

	@Test
	public void testAddCards() {
		List<Card> cards = player.getCards();
		assertNotNull(cards);
		assertEquals(5, cards.size());
		List<Card> addedCards = new ArrayList<Card>();
		addedCards.add(new Card(Suit.DIAMOND, Rank.NINE));
		addedCards.add(new Card(Suit.SPADE, Rank.EIGHT));
		player.addCards(addedCards);
		assertEquals(7, cards.size());
		assertEquals(64, player.getScore());
	}
}
