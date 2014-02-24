package se.altran.restkurs.webapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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

public class MovieResourceTest {
    
	
	private ArrayList<Movie> movies;
	private Server server;
	
	@Before
	public void setUp() throws Exception {
		
		// Mock the MovieService with some test data
		IMovieService movieService = mock(IMovieService.class);
		
		movies = new ArrayList<>();
		movies.add(new Movie("Sunes Sommar", 2013));
		
		when(movieService.getMovies()).thenReturn(movies);
		
		// Start the server
		AbstractModule testModule = new MovieTestModule(movieService);
		server = AltranREST.startServer(8090, testModule);

	}
	
	@Test
	public void testMovies_GET() throws Exception {
		
		// Read the Movies resource as a JSON String
		HttpClientHelper httpClientHelper = new HttpClientHelper("127.0.0.1", 8090);
		String resource = httpClientHelper.getResource("/webapi/movies", "application/json");
		
		List<Movie> parsedMovies = deserializeMovies(resource);

		assertEquals("All movies were not found.", movies.size(), parsedMovies.size());
	}
	
	@Test
	public void testMovies_POST() throws Exception {
		
		// Read the Movies resource as a JSON String
		HttpClientHelper httpClientHelper = new HttpClientHelper("127.0.0.1", 8090);
		String resource = httpClientHelper.getResource("/webapi/movies", "application/json");
		
		List<Movie> parsedMovies = deserializeMovies(resource);

		assertEquals("All movies were not found.", movies.size(), parsedMovies.size());
	}
	
	protected List<Movie> deserializeMovies(String jsonMovies) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<Movie> movies = mapper.readValue(jsonMovies, new TypeReference<List<Movie>>(){});
		return movies;
	}
	
	@After
	public void tearDown() {

		try {
			server.stop();
		} catch (Exception ignore) { }
	}
}
