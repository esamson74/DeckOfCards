package com.esamson.entity;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Board Tests.
 * 
 * @author esamson74@gmail.com
 *
 */
public class BoardTest extends TestCase {

	@Test
	public void testAttributes() {
		Board board = new Board("TEST", 99);
		assertEquals("TEST", board.getName());
		assertEquals(99, board.getScore());
	}
}
