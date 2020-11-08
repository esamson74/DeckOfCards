package com.esamson.service;

import java.text.MessageFormat;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.esamson.entity.Game;
import com.esamson.entity.Player;
import com.esamson.entity.Suit;
import com.google.gson.Gson;

/**
 * Game Service.
 * 
 * @author esamson74@gmail.com
 */
@Path("/")
public class GameService {

	private static final String GAME_STARTED = "New Game has started!";
	private static final String GAME_NOT_STARTED = "Game is not started!";
	private static final String GAME_EXISTS = "A Game is already in progress!";
	private static final String QUIT_GAME = "Thanks for playing DeckOfCards!";
	private static final String PLAYER_NOT_FOUND = "Player not found!";
	private static final String NO_PLAYER = "No Player was found!";
	private static final String PLAYER_ADD = "{0} has joined the game!";
	private static final String PLAYER_QUIT = "{0} has left the game!";

	private static Game game;

	/**
	 * Constructor.
	 */
	public GameService() {
	}

	/**
	 * Creates a New Game.
	 * 
	 * @return Response
	 */
	@GET
	@Path("/newGame")
	@Produces(MediaType.TEXT_PLAIN)
	public Response newGame() {
		Response response = Response.status(Response.Status.FORBIDDEN).entity(GAME_EXISTS).build();
		if (game == null) {
			game = new Game();
			response = Response.ok(GAME_STARTED).build();
		}
		return response;
	}

	/**
	 * Quits the Game if one exists.
	 * 
	 * @return Response
	 */
	@GET
	@Path("/quitGame")
	@Produces(MediaType.TEXT_PLAIN)
	public Response quitGame() {
		Response response = Response.status(Response.Status.FORBIDDEN).entity(GAME_NOT_STARTED).build();
		if (game != null) {
			game = null;
			response = Response.ok(QUIT_GAME).build();
		}
		return response;
	}

	/**
	 * Adds a New Player to the Game with a given amount of Cards to deal.
	 * 
	 * @param cards
	 * @return Response
	 */
	@PUT
	@Path("/addPlayer/{cards}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response addPlayer(@PathParam("cards") int cards) {
		Response response = Response.status(Response.Status.FORBIDDEN).entity(GAME_NOT_STARTED).build();
		if (game != null) {
			String playerName = game.addPlayer(cards);
			response = Response.ok(MessageFormat.format(PLAYER_ADD, playerName)).build();
		}
		return response;
	}

	/**
	 * Removes a Player from the Game.
	 * 
	 * @param id
	 * @return Response
	 */
	@PUT
	@Path("/removePlayer/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response removePlayer(@PathParam("id") int id) {
		Response response = Response.status(Response.Status.FORBIDDEN).entity(GAME_NOT_STARTED).build();
		if (game != null) {
			String playerName = game.removePlayer(id);
			if (playerName != null) {
				response = Response.ok(MessageFormat.format(PLAYER_QUIT, playerName)).build();
			} else {
				response = Response.status(Response.Status.NOT_FOUND).entity(PLAYER_NOT_FOUND).build();
			}
		}
		return response;
	}

	/**
	 * Gets the List of all Players of the Game.
	 * 
	 * @return Response
	 */
	@GET
	@Path("/listPlayers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listPlayers() {
		Response response = Response.status(Response.Status.FORBIDDEN).entity(GAME_NOT_STARTED).build();
		if (game != null) {
			Gson gson = new Gson();
			response = Response.ok(gson.toJson(game.getLeaderBoard())).build();
		}
		return response;
	}

	/**
	 * Gets the List of all Cards of a given Player Id.
	 * 
	 * @param id
	 * @return Response
	 */
	@GET
	@Path("/listCards/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listCards(@PathParam("id") int id) {
		Response response = Response.status(Response.Status.FORBIDDEN).entity(GAME_NOT_STARTED).build();
		if (game != null) {
			Player player = game.getPlayer(id);
			if (player != null) {
				Gson gson = new Gson();
				response = Response.ok(gson.toJson(player.getCards())).build();
			} else {
				response = Response.status(Response.Status.NOT_FOUND).entity(NO_PLAYER).build();
			}
		}
		return response;
	}

	/**
	 * Deals the given amount of Cards to the given Player Id.
	 * 
	 * @param id
	 * @param amount
	 * @return Response
	 */
	@PUT
	@Path("/dealCards/{id}/{amount}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dealCards(@PathParam("id") int id, @PathParam("amount") int amount) {
		Response response = Response.status(Response.Status.FORBIDDEN).entity(GAME_NOT_STARTED).build();
		if (game != null) {
			Player player = game.getPlayer(id);
			if (player != null) {
				player.addCards(game.getCurrentDeck().dealCards(amount));
				Gson gson = new Gson();
				response = Response.ok(gson.toJson(player.getCards())).build();
			} else {
				response = Response.status(Response.Status.NOT_FOUND).entity(NO_PLAYER).build();
			}
		}
		return response;
	}

	@GET
	@Path("/undealtCards")
	@Produces(MediaType.APPLICATION_JSON)
	public Response undealtCards() {
		Response response = Response.status(Response.Status.FORBIDDEN).entity(GAME_NOT_STARTED).build();
		if (game != null) {
			Map<Suit, Long> undealtCards = game.getCurrentDeck().getUndealtCards();
			Gson gson = new Gson();
			response = Response.ok(gson.toJson(undealtCards)).build();
		}
		return response;
	}

	@GET
	@Path("/deckInfo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deckInfo() {
		Response response = Response.status(Response.Status.FORBIDDEN).entity(GAME_NOT_STARTED).build();
		if (game != null) {
			Map<String, String> deckInfo = game.getCurrentDeck().getDeckInfo();
			Gson gson = new Gson();
			response = Response.ok(gson.toJson(deckInfo)).build();
		}
		return response;
	}

	@GET
	@Path("/shuffle")
	@Produces(MediaType.TEXT_PLAIN)
	public Response shuffle() {
		Response response = Response.status(Response.Status.FORBIDDEN).entity(GAME_NOT_STARTED).build();
		if (game != null) {
			game.getCurrentDeck().shuffle();
			response = Response.ok("").build();
		}
		return response;
	}
}
