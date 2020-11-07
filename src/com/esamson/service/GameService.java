package com.esamson.service;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.esamson.entity.Game;
import com.esamson.entity.Player;
import com.google.gson.Gson;

@Path("/")
public class GameService {

	private static Game game;
		
	@GET
	@Path("/newGame")
	@Produces("text/plain")
	public Response newGame() {
		Response response = Response.status(Response.Status.FORBIDDEN).entity("A Game is already in progress!").build(); 
		if (game == null) {
			game = new Game();
			response = Response.ok("New Game has started!").build();
		}
		return response;
	}
	
	@GET
	@Path("/quitGame")
	@Produces("text/plain")
	public Response quitGame() {
		Response response = Response.status(Response.Status.FORBIDDEN).entity("You cannot quit a game that is not yet started :)").build();
		if (game != null) {
			game = null;
			response = Response.ok("Thanks for playing DeckOfCards!").build();
		}
		return response;
	}
	
	@GET
	@Path("/addPlayer")
	@Produces("text/plain")
	public Response addPlayer() {
		Response response = Response.status(Response.Status.FORBIDDEN).entity("You cannot add a player to a game that is not yet started :)").build();
		if (game != null) {
			String playerName = game.addPlayer();
			response = Response.ok(playerName + " has joined the game!").build();
		}
		return response;
	}
		
	@PUT
	@Path("/removePlayer")
	@Produces("text/plain")
	public Response removePlayer(@QueryParam("id") int id) {
		Response response = Response.status(Response.Status.FORBIDDEN).entity("You cannot remove a player to a game that is not yet started :)").build();
		if (game != null) {
			String playerName = game.removePlayer(id);
			response = Response.ok(playerName + " has left the game!").build();
		}
		return response;
	}
	
	@GET
	@Path("/listPlayers")
	@Produces("application/json")
	public Response listPlayers() {
		Response response = Response.status(Response.Status.FORBIDDEN).entity("You cannot list players to a game that is not yet started :)").build();
		if (game != null) {
			Gson gson = new Gson();
			response = Response.ok(gson.toJson(game.getPlayers())).build();
		}
		return response;
	}
	
	@PUT
	@Path("/listCards")
	@Produces("application/json")
	public Response listCards(@QueryParam("id") int id) {
		Response response = Response.status(Response.Status.FORBIDDEN).entity("You cannot list cards to a game that is not yet started :)").build();
		if (game != null) {
			Player player = game.getPlayer(id);
			if (player != null) {
				Gson gson = new Gson();
				response = Response.ok(gson.toJson(player.getCards())).build();
			}
			else {
				response = Response.status(Response.Status.FORBIDDEN).entity("No player was found!").build();
			}
		}
		return response;
	}
}
