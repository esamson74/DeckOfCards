package com.esamson.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.junit.Test;

import junit.framework.TestCase;

public class GameServiceTest extends TestCase {

	private static final String BASE_URL = "http://localhost:8080/DeckOfCards/";
	private static final String URL_NEW_GAME = BASE_URL + "newGame";
	
	@Test
	public void testNewGameWhenGameNotStarted() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(URL_NEW_GAME);
	      Response response = target.request().get();
	      try
	      {
	         assertEquals("Http StatusCode should be:", Status.OK.getStatusCode(), response.getStatus());
	         assertEquals("Http Content-Type should be: ", MediaType.TEXT_PLAIN, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
	         String content = response.readEntity(String.class);
	         assertEquals("Content of ressponse is: ", "New Game has started!", content);
	      }
	      finally
	      {
	         response.close();
	         client.close();
	      }
	}

}
