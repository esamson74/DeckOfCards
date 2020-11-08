package com.esamson.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Game.
 * 
 * @author esamson74@gmail.com
 */
public class Game {

	private List<Deck> decks = new ArrayList<Deck>();
	private Map<Integer, Player> players = new HashMap<Integer, Player>();
	private int currentDeckIndex = 0;
	private int nextPlayerId = 0;

	/**
	 * Constructor.
	 */
	public Game() {
		addDeck();
	}

	/**
	 * Gets all the Decks of the Game.
	 * 
	 * @return List
	 */
	public List<Deck> getDecks() {
		return decks;
	}

	/**
	 * Gets the current Deck used in the Game.
	 * 
	 * @return Deck
	 */
	public Deck getCurrentDeck() {
		return decks.get(currentDeckIndex);
	}

	/**
	 * Adds a Deck to the Game.
	 */
	public void addDeck() {
		decks.add(new Deck());
	}

	/**
	 * Adds a New Player to the Game with a given amount of Cards as a starting
	 * hand.
	 * 
	 * @param amount
	 * @return String - the Player Name
	 */
	public String addPlayer(int amount) {
		Player player = new Player(++nextPlayerId, getCurrentDeck().dealCards(amount));
		players.put(nextPlayerId, player);
		return player.getName();
	}

	/**
	 * Removes a Player from the Game by the given Id.
	 * 
	 * @param id
	 * @return String - the Player Name
	 */
	public String removePlayer(int id) {
		Player player = players.get(id);
		String playerName = null;
		if (player != null) {
			players.remove(id);
			playerName = player.getName();
		}
		return playerName;
	}

	/**
	 * Gets all the Players of the Game.
	 * 
	 * @return Map
	 */
	public Map<Integer, Player> getPlayers() {
		return players;
	}

	/**
	 * Gets a Player of the Game for the given Id.
	 * 
	 * @param id
	 * @return Player
	 */
	public Player getPlayer(int id) {
		Player player = null;
		if (!players.isEmpty()) {
			player = players.get(id);
		}
		return player;
	}

	/**
	 * Gets the LeaderBoard. The Player having the highest Score will come first and
	 * so on.
	 * 
	 * @return List
	 */
	public List<Board> getLeaderBoard() {
		List<Board> leaderBoard = new ArrayList<Board>();
		for (Entry<Integer, Player> entry : getPlayers().entrySet()) {
			Player player = entry.getValue();
			leaderBoard.add(new Board(player.getName(), player.getScore()));
		}
		// Sort By Value Descending.
		Collections.sort(leaderBoard, new Comparator<Board>() {

	        public int compare(Board board1, Board board2) {
	            return Integer.valueOf(board2.getScore()).compareTo(Integer.valueOf(board1.getScore()));
	        }
	    });
		
		return leaderBoard;
	}
}
