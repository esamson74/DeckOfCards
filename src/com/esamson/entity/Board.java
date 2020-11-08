package com.esamson.entity;

public class Board {
	private final String name;
	private final int score;

	public Board(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}
}
