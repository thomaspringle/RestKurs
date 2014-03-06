package se.altran.restkurs.webapi.actor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.altran.restkurs.actor.Actor;
import se.altran.restkurs.actor.IActorService;
import se.altran.restkurs.main.AltranREST;
import se.altran.restkurs.main.DomainModule;
import se.altran.restkurs.webapi.HttpHelper;

import com.google.inject.AbstractModule;

public class ActorResourceDeleteTest {

	private static final String ACTOR_JSON = "actor.json";
	private Server server;
	private String uuid = "actor_uuid";
	private IActorService actorService;
	private String userToken = "user_token_value";
	private List<Actor> actors = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
	
		// Mock the ActorService with some test data
		actorService = mock(IActorService.class);
		when(actorService.deleteActors(userToken)).thenReturn(actors);

		// Start the server
		AbstractModule testModule = new DomainModule(actorService);
		server = AltranREST.startServer(8090, testModule);
	}
	
	@Test
	public void testActors_POST_ReturnCreatedStatus() throws Exception {
		
		// Create POST command to /webapi/actors
		HttpHelper httpHelper = new HttpHelper("127.0.0.1", 8090);
		HttpDelete httpDelete = new HttpDelete("/webapi/actors");
		httpDelete.setHeader("Accept", "application/json");
		httpDelete.setHeader("Authorization", userToken);

//		// Define data to be posted
//		String data = getActorJsonFromFile();
//		httpPost.setEntity(new StringEntity(data, ContentType.create("application/json")));

		// Execute method and receive response
		HttpResponse response = httpHelper.executeMethod(httpDelete);
		
		// Verify that the correct status code has been set
		int statusCode = response.getStatusLine().getStatusCode();
		assertEquals("Expected Status Code 200 - OK", 200, statusCode);	
		
		verify(actorService).deleteActors(userToken);
	}

	
	@After
	public void tearDown() {
		try {
			server.stop();
		} catch (Exception ignore) { }
	}
}
