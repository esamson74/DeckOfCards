package com.esamson.entity;

import java.util.ArrayList;
import java.util.List;

public class Game {

	private static List<Deck> decks = new ArrayList<Deck>();
	private static List<Player> players = new ArrayList<Player>();
	private static int currentDeckIndex = 0;
	
	public Game() {
		addDeck();
	}
	
	public List<Deck> getDecks() {
		return decks;
	}
	
	public Deck getCurrentDeck() {
		return decks.get(currentDeckIndex);
	}
	
	public void addDeck() {
		decks.add(new Deck());
	}
	
	public String addPlayer() {
		Player player = new Player(players.size() + 1, getCurrentDeck().dealCards(5)); 
		players.add(player);
		return player.getName();
	}
	
	public String removePlayer(int id) {
		Player player = players.get(id - 1);
		if (player != null) {
			players.remove(id);	
		}
		return player.getName();
	}
	
	public List<Player> getPlayers() {
		return players;
	}
	
	public Player getPlayer(int id) {
		Player player = null;
		if (players.size() > 0 && players.size() > id) {
			player = players.get(id);
		}
		return player;
	}
}
