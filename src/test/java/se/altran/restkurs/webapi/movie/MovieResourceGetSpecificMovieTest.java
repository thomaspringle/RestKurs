package se.altran.restkurs.webapi.movie;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.codehaus.jackson.map.ObjectMapper;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.altran.restkurs.main.AltranREST;
import se.altran.restkurs.movie.IMovieService;
import se.altran.restkurs.movie.Movie;
import se.altran.restkurs.webapi.HttpHelper;
import se.altran.restkurs.webapi.movie.MovieBean;

import com.google.inject.AbstractModule;

public class MovieResourceGetSpecificMovieTest {
    
	
	private Server server;
	private String uuidGravity;
	
	
	@Before
	public void setUp() throws Exception {
	
		// Mock the MovieService with some test data
		IMovieService movieService = mock(IMovieService.class);
		
		Movie gravity = new Movie("Gravity", 2013);
		uuidGravity = gravity.getId();
		
		when(movieService.getMovie(uuidGravity)).thenReturn(gravity);
		
		// Start the server
		AbstractModule testModule = new MovieTestModule(movieService);
		server = AltranREST.startServer(8090, testModule);
	}
	
	@Test
	public void testMovies_GET_specificMovie() throws Exception {
		
		// Read the Movies resource as a JSON String
		HttpHelper httpHelper = new HttpHelper("127.0.0.1", 8090);
		String uri = "/webapi/movies/" + uuidGravity;
		HttpGet httpGet = new HttpGet(uri);
		
		// Execute the request and get a response
		HttpResponse httpResponse = httpHelper.executeMethod(httpGet);

		// Read the Movie response data as a JSON String
		String responseData = HttpHelper.responseData(httpResponse, "application/json");
		MovieBean gravity = deserializeMovie(responseData);

		assertEquals("Gravity must have correct title", "Gravity", gravity.getTitle());
		assertEquals("Gravity must have correct year", 2013, gravity.getYear());
	}
	
	protected MovieBean deserializeMovie(String jsonMovie) throws Exception {
		MovieBean movie = new ObjectMapper().readValue(jsonMovie, MovieBean.class);
		return movie;
	}
	
	@After
	public void tearDown() {
		try {
			server.stop();
		} catch (Exception ignore) { }
	}
}
