package se.altran.restkurs.webapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.altran.restkurs.main.AltranREST;
import se.altran.restkurs.movie.IMovieService;
import se.altran.restkurs.movie.Movie;

import com.google.inject.AbstractModule;

public class MovieResourceGetNotEmptyTest {
    
	
	private ArrayList<Movie> movies;
	private Server server;
	private String uuidSunesSommar;
	private String uuidGravity;
	
	
	@Before
	public void setUp() throws Exception {
	
		// Mock the MovieService with some test data
		IMovieService movieService = mock(IMovieService.class);
		
		Movie sunesSommar = new Movie("Sunes Sommar", 2012);
		Movie gravity = new Movie("Gravity", 2013);

		movies = new ArrayList<>();
		movies.add(sunesSommar);
		movies.add(gravity);
		
		uuidSunesSommar = sunesSommar.getId();
		uuidGravity = gravity.getId();
		
		when(movieService.getMovies()).thenReturn(movies);

		// Start the server
		AbstractModule testModule = new MovieTestModule(movieService);
		server = AltranREST.startServer(8090, testModule);

	}
	
	@Test
	public void testMovies_GET_moviesExist() throws Exception {
		
		// Read the Movies resource as a JSON String
		HttpClientHelper httpClientHelper = new HttpClientHelper("127.0.0.1", 8090);
		HttpResponse response = httpClientHelper.GET("/webapi/movies", "application/json");
		
		String resource = HttpClientHelper.responseData(response, "application/json");
		List<MovieBean> parsedMovies = deserializeMovies(resource);

		assertFalse("Movies must exist.", movies.isEmpty());
		assertEquals("All movies were not found.", movies.size(), parsedMovies.size());
	}

	@Test
	public void testMovies_GET_correctMovies() throws Exception {
		
		// Read the Movies resource as a JSON String
		HttpClientHelper httpClientHelper = new HttpClientHelper("127.0.0.1", 8090);
		HttpResponse response = httpClientHelper.GET("/webapi/movies", "application/json");
		
		String resource = HttpClientHelper.responseData(response, "application/json");
		List<MovieBean> parsedMovies = deserializeMovies(resource);


		// Check that the movies are returned with correct values
		MovieBean sunesSommar = getMovieWithId(parsedMovies, uuidSunesSommar);
		MovieBean gravity = getMovieWithId(parsedMovies, uuidGravity);
		
		assertEquals("SunesSommar must have correct title", "Sunes Sommar", sunesSommar.getTitle());
		assertEquals("SunesSommar must have correct year", 2012, sunesSommar.getYear());
		
		assertEquals("Gravity must have correct title", "Gravity", gravity.getTitle());
		assertEquals("Gravity must have correct year", 2013, gravity.getYear());
	}

	private MovieBean getMovieWithId(List<MovieBean> parsedMovies, String uuid) {
		for (MovieBean movie : parsedMovies) {
			if (movie.getId().equals(uuid)) {
				return movie;
			}
		}
		throw new RuntimeException("A movie with the UUID was not found.");
	}
	
	protected List<MovieBean> deserializeMovies(String jsonMovies) throws Exception {
		List<MovieBean> movies = new ObjectMapper().readValue(jsonMovies, new TypeReference<List<MovieBean>>(){});
		return movies;
	}
	
	@After
	public void tearDown() {
		try {
			server.stop();
		} catch (Exception ignore) { }
	}
}
