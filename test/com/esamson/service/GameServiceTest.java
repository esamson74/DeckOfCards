package com.esamson.service;

import java.text.MessageFormat;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * GameService Tests.
 * TODO: Server must be started first.  So add server start automatically.
 * 
 * @author esamson74@gmail.com
 *
 */
public class GameServiceTest extends TestCase {

	private static final String BASE_URL = "http://localhost:8080/DeckOfCards/";
	private static final String URL_QUIT_GAME = BASE_URL + "quitGame";
	private static final String URL_NEW_GAME = BASE_URL + "newGame";
	private static final String URL_ADD_PLAYER = BASE_URL + "addPlayer/{cards}";
	private static final String URL_REMOVE_PLAYER = BASE_URL + "removePlayer/{id}";
	private static final String URL_LIST_PLAYERS = BASE_URL + "listPlayers";
	private static final String URL_LIST_CARDS = BASE_URL + "listCards/{id}";
	private static final String URL_DEAL_CARDS = BASE_URL + "dealCards/{id}/{amount}";
	private static final String URL_UNDEALT_CARDS = BASE_URL + "undealtCards";
	private static final String URL_DECK_INFO = BASE_URL + "deckInfo";
	private static final String URL_SHUFFLE = BASE_URL + "shuffle";
	
	private static final String GAME_STARTED = "New Game has started!";
	private static final String GAME_NOT_STARTED = "Game is not started!";
	private static final String GAME_EXISTS = "A Game is already in progress!";
	private static final String QUIT_GAME = "Thanks for playing DeckOfCards!";
	private static final String PLAYER_NOT_FOUND = "Player not found!";
	private static final String NO_PLAYER = "No Player was found!";
	private static final String PLAYER_ADD = "{0} has joined the game!";
	private static final String PLAYER_QUIT = "{0} has left the game!";

	private Client client;

	private void validateStatusCode(Response response, Status status) {
		assertNotNull("Response should not be null", response);
		assertNotNull("Status should not be null", status);
		assertEquals("Http StatusCode validation", status.getStatusCode(), response.getStatus());
	}

	private void validateContentType(Response response, String mediaType) {
		assertNotNull("Response should not be null", response);
		assertNotNull("MediaType should not be null", mediaType);
		assertEquals("Http Content-Type validation", mediaType, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	}

	private <T> void validateContentEquals(Response response, String expectedContent) {
		assertNotNull("Response should not be null", response);
		assertNotNull("Content should not be null", expectedContent);
		assertEquals("Content validation should be equals", expectedContent, response.readEntity(String.class));
	}
	
	private <T> void validateContentMatches(Response response, String expectedContent) {
		assertNotNull("Response should not be null", response);
		assertNotNull("Content should not be null", expectedContent);
		assertTrue("Content validation should match", response.readEntity(String.class).matches(expectedContent));
	}

	private void quitGame() {
		// Be sure that the Game is not started. No validation needed.
		client = ClientBuilder.newClient();
		WebTarget target = client.target(URL_QUIT_GAME);
		Response response = target.request().get();
		response.close();
	}

	private void startGame() {
		// Be sure that the Game is started. No validation needed.
		client = ClientBuilder.newClient();
		WebTarget target = client.target(URL_NEW_GAME);
		Response response = target.request().get();
		response.close();
	}

	private void addPlayer(int cards) {
		// Be sure that the Game is started. No validation needed.
		client = ClientBuilder.newClient();
		WebTarget target = client.target(URL_ADD_PLAYER);
		Response response = target.resolveTemplate("cards", String.valueOf(cards)).request().put(null);
		response.close();
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		quitGame();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		if (client != null) {
			client.close();
		}
	}

	@Test
	public void testNewGameWhenGameNotStarted() {
		WebTarget target = client.target(URL_NEW_GAME);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, GAME_STARTED);
		} finally {
			response.close();
		}
	}

	@Test
	public void testNewGameWhenGameAlreadyStarted() {
		startGame();
		WebTarget target = client.target(URL_NEW_GAME);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.FORBIDDEN);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, GAME_EXISTS);
		} finally {
			response.close();
		}
	}

	@Test
	public void testQuitGameWhenGameNotStarted() {
		WebTarget target = client.target(URL_QUIT_GAME);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.FORBIDDEN);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, GAME_NOT_STARTED);
		} finally {
			response.close();
		}
	}

	@Test
	public void testQuitGameWhenGameAlreadyStarted() {
		startGame();
		WebTarget target = client.target(URL_QUIT_GAME);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, QUIT_GAME);
		} finally {
			response.close();
		}
	}

	@Test
	public void testAddPlayerWhenGameNotStarted() {
		WebTarget target = client.target(URL_ADD_PLAYER);
		Response response = target.resolveTemplate("cards", "2").request().put(null);
		try {
			validateStatusCode(response, Status.FORBIDDEN);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, GAME_NOT_STARTED);
		} finally {
			response.close();
		}
	}

	@Test
	public void testAddPlayer1WhenGameAlreadyStarted() {
		startGame();
		WebTarget target = client.target(URL_ADD_PLAYER);
		Response response = target.resolveTemplate("cards", "2").request().put(null);
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, MessageFormat.format(PLAYER_ADD, "Player-1"));
		} finally {
			response.close();
		}
	}

	@Test
	public void testAddPlayer2WhenGameAlreadyStarted() {
		startGame();
		addPlayer(2);
		WebTarget target = client.target(URL_ADD_PLAYER);
		Response response = target.resolveTemplate("cards", "2").request().put(null);
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, MessageFormat.format(PLAYER_ADD, "Player-2"));
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testRemovePlayerWhenGameNotStarted() {
		WebTarget target = client.target(URL_REMOVE_PLAYER);
		Response response = target.resolveTemplate("id", "1").request().put(null);
		try {
			validateStatusCode(response, Status.FORBIDDEN);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, GAME_NOT_STARTED);
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testRemovePlayer1WhenGameAlreadyStartedAndPlayerExists() {
		startGame();
		addPlayer(2);
		WebTarget target = client.target(URL_REMOVE_PLAYER);
		Response response = target.resolveTemplate("id", "1").request().put(null);
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, MessageFormat.format(PLAYER_QUIT, "Player-1"));
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testRemovePlayer2WhenGameAlreadyStartedAndPlayerExists() {
		startGame();
		addPlayer(2);
		addPlayer(2);
		WebTarget target = client.target(URL_REMOVE_PLAYER);
		Response response = target.resolveTemplate("id", "2").request().put(null);
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, MessageFormat.format(PLAYER_QUIT, "Player-2"));
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testRemovePlayerWhenGameAlreadyStartedAndPlayerNotExists() {
		startGame();
		addPlayer(2);
		WebTarget target = client.target(URL_REMOVE_PLAYER);
		Response response = target.resolveTemplate("id", "2").request().put(null);
		try {
			validateStatusCode(response, Status.NOT_FOUND);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, PLAYER_NOT_FOUND);
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testListPlayersWhenGameNotStarted() {
		WebTarget target = client.target(URL_LIST_PLAYERS);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.FORBIDDEN);
			validateContentType(response, MediaType.APPLICATION_JSON);
			validateContentEquals(response, GAME_NOT_STARTED);
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testListPlayersWhenGameAlreadyStartedAndNoPlayerExists() {
		startGame();
		WebTarget target = client.target(URL_LIST_PLAYERS);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.APPLICATION_JSON);
			validateContentEquals(response, "[]");
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testListPlayers1WhenGameAlreadyStarted() {
		startGame();
		addPlayer(2);
		WebTarget target = client.target(URL_LIST_PLAYERS);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.APPLICATION_JSON);
			validateContentMatches(response, "\\[\\{\"name\":\"Player-1\",\"score\":\\d+\\}\\]");
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testListPlayers2WhenGameAlreadyStarted() {
		startGame();
		addPlayer(2);
		addPlayer(2);
		WebTarget target = client.target(URL_LIST_PLAYERS);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.APPLICATION_JSON);
			validateContentMatches(response, "\\[\\{\"name\"\\:\"Player-\\d+\",\"score\":\\d+\\},\\{\"name\"\\:\"Player-\\d+\",\"score\":\\d+\\}\\]");
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testListCardsWhenGameNotStarted() {
		WebTarget target = client.target(URL_LIST_PLAYERS);
		Response response = target.resolveTemplate("id", "1").request().get();
		try {
			validateStatusCode(response, Status.FORBIDDEN);
			validateContentType(response, MediaType.APPLICATION_JSON);
			validateContentEquals(response, GAME_NOT_STARTED);
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testListCardsWhenGameAlreadyStartedAndNoPlayerExists() {
		startGame();
		WebTarget target = client.target(URL_LIST_CARDS);
		Response response = target.resolveTemplate("id", "1").request().get();
		try {
			validateStatusCode(response, Status.NOT_FOUND);
			validateContentType(response, MediaType.APPLICATION_JSON);
			validateContentEquals(response, NO_PLAYER);
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testListCardsWhenGameAlreadyStarted() {
		startGame();
		addPlayer(2);
		WebTarget target = client.target(URL_LIST_CARDS);
		Response response = target.resolveTemplate("id", "1").request().get();
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.APPLICATION_JSON);
			// TODO: Add Matches Validation here.
			//validateContentMatches(response, "");
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testDealCardsWhenGameNotStarted() {
		WebTarget target = client.target(URL_DEAL_CARDS);
		Response response = target.resolveTemplate("id", "1").resolveTemplate("amount", "2").request().put(null);
		try {
			validateStatusCode(response, Status.FORBIDDEN);
			validateContentType(response, MediaType.APPLICATION_JSON);
			validateContentEquals(response, GAME_NOT_STARTED);
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testDealCardsWhenGameAlreadyStartedAndNoPlayerExists() {
		startGame();
		WebTarget target = client.target(URL_DEAL_CARDS);
		Response response = target.resolveTemplate("id", "1").resolveTemplate("amount", "2").request().put(null);
		try {
			validateStatusCode(response, Status.NOT_FOUND);
			validateContentType(response, MediaType.APPLICATION_JSON);
			validateContentEquals(response, NO_PLAYER);
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testDealCardsWhenGameAlreadyStarted() {
		startGame();
		addPlayer(2);
		WebTarget target = client.target(URL_DEAL_CARDS);
		Response response = target.resolveTemplate("id", "1").resolveTemplate("amount", "2").request().put(null);
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.APPLICATION_JSON);
			// TODO: Add Matches Validation here.
			//validateContentMatches(response, "");
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testUndealtCardsWhenGameNotStarted() {
		WebTarget target = client.target(URL_UNDEALT_CARDS);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.FORBIDDEN);
			validateContentType(response, MediaType.APPLICATION_JSON);
			validateContentEquals(response, GAME_NOT_STARTED);
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testUndealtCardsWhenGameAlreadyStarted() {
		startGame();
		addPlayer(2);
		WebTarget target = client.target(URL_UNDEALT_CARDS);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.APPLICATION_JSON);
			// TODO: Add Matches Validation here.
			//validateContentMatches(response, "");
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testDeckInfoWhenGameNotStarted() {
		WebTarget target = client.target(URL_DECK_INFO);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.FORBIDDEN);
			validateContentType(response, MediaType.APPLICATION_JSON);
			validateContentEquals(response, GAME_NOT_STARTED);
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testDeckInfoWhenGameAlreadyStarted() {
		startGame();
		addPlayer(2);
		WebTarget target = client.target(URL_DECK_INFO);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.APPLICATION_JSON);
			// TODO: Add Matches Validation here.
			//validateContentMatches(response, "");
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testShuffleWhenGameNotStarted() {
		WebTarget target = client.target(URL_SHUFFLE);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.FORBIDDEN);
			validateContentType(response, MediaType.TEXT_PLAIN);
			validateContentEquals(response, GAME_NOT_STARTED);
		} finally {
			response.close();
		}
	}
	
	@Test
	public void testShuffleWhenGameAlreadyStarted() {
		startGame();
		addPlayer(2);
		WebTarget target = client.target(URL_SHUFFLE);
		Response response = target.request().get();
		try {
			validateStatusCode(response, Status.OK);
			validateContentType(response, MediaType.TEXT_PLAIN);
		} finally {
			response.close();
		}
	}
}
