package com.esamson.entity;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Card Tests.
 * 
 * @author esamson74@gmail.com
 *
 */
public class CardTest extends TestCase {

	@Test
	public void testAttributes() {
		Card card = new Card(Suit.DIAMOND, Rank.FIVE);
		assertEquals(5, card.getScore());
		assertEquals("Diamond", card.getName());
		assertEquals(Rank.FIVE, card.getRank());
		assertEquals(Suit.DIAMOND, card.getSuit());
	}
}
