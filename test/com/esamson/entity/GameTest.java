package com.esamson.entity;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Game Tests.
 * 
 * @author esamson74@gmail.com
 */
public class GameTest extends TestCase {

	@Test
	public void testAttributes() {
		Game game = new Game();
		Map<Integer, Player> players = game.getPlayers();
		assertNotNull(players);
		game.addPlayer(1);
		Player player = game.getPlayer(1);
		assertNotNull(player);
		List<Board> boards = game.getLeaderBoard();
		assertNotNull(boards);
	}

	@Test
	public void testAddPlayers() {
		Game game = new Game();
		Map<Integer, Player> players = game.getPlayers();
		assertNotNull(players);
		assertEquals(0, players.size());
		game.addPlayer(1);
		assertEquals(1, players.size());
		game.addPlayer(2);
		assertEquals(2, players.size());
	}

	@Test
	public void testRemovePlayers() {
		Game game = new Game();
		Map<Integer, Player> players = game.getPlayers();
		assertNotNull(players);
		assertEquals(0, players.size());
		game.addPlayer(1);
		assertEquals(1, players.size());
		game.removePlayer(1);
		assertNotNull(players);
		assertEquals(0, players.size());
		game.addPlayer(2);
		game.addPlayer(3);
		game.removePlayer(3);
		for (Entry<Integer, Player> entry : players.entrySet()) {
			assertEquals(Integer.valueOf(2), entry.getKey());;
		}
	}

	@Test
	public void testAddDeck() {
		Game game = new Game();
		assertNotNull(game.getCurrentDeck());
		assertNotNull(game.getDecks());
		assertEquals(1, game.getDecks().size());
		game.addDeck();
		assertEquals(2, game.getDecks().size());
	}
}
