package se.altran.restkurs.webapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
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
		HttpResponse response = httpClientHelper.GET("/webapi/movies", "application/json");
		
		String resource = HttpClientHelper.responseData(response, "application/json");
		List<Movie> parsedMovies = deserializeMovies(resource);

		assertEquals("All movies were not found.", movies.size(), parsedMovies.size());
	}
	
	@Test
	public void testMovies_POST() throws Exception {
		
		// Read the Movies resource as a JSON String
		HttpHelper httpHelper = new HttpHelper("127.0.0.1", 8090);
		//httpClientHelper.withHeader("Accept", "application/json");
		
		HttpPost httpPost = new HttpPost("/webapi/movies");
		httpPost.setHeader("Accept", "application/json");
		httpPost.setEntity(new HttpEntity);
		
		HttpResponse httpResponse = httpHelper.executeMethod(httpPost);
		
		String data = "{movie: {}}";
		
		HttpResponse response = httpClientHelper.POST("/webapi/movies", "application/json", data);
		
		String resource = HttpClientHelper.responseData(response, "application/json");
//		List<Movie> parsedMovies = deserializeMovies(resource);
		System.out.println(resource);

		//assertEquals("All movies were not found.", movies.size(), parsedMovies.size());
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
