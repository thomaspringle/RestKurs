package se.altran.restkurs.webapi.actor;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Server;
import org.junit.After;
import org.junit.Before;

import se.altran.restkurs.actor.Actor;
import se.altran.restkurs.actor.IActorService;
import se.altran.restkurs.main.AltranREST;
import se.altran.restkurs.main.DomainModule;
import se.altran.restkurs.movie.Movie;

import com.google.inject.AbstractModule;

public class ActorResourceTest {

	private Server server;
	private List<Actor> actors;
	
	@Before
	public void setUp() throws Exception {
	
		// Mock the ActorService with some test data
		actors = new ArrayList<>();
		ArrayList<Movie> peterHaberMovies = new ArrayList<Movie>();
		peterHaberMovies.add(new Movie("Sunes Sommar", 1993));
		peterHaberMovies.add(new Movie("Beck", 1999));
		
		ArrayList<Movie> sandraBullockMovies = new ArrayList<Movie>();
		sandraBullockMovies.add(new Movie("Speed", 1994));
		sandraBullockMovies.add(new Movie("crash", 2004));
		sandraBullockMovies.add(new Movie("28 Days", 2000));
		sandraBullockMovies.add(new Movie("Gravity", 2013));
		
		Actor sandraBullock = new Actor("Sandra", "Bullock", sandraBullockMovies);
		Actor peterHaber = new Actor("Peter", "Haber", peterHaberMovies);

		actors.add(sandraBullock);
		actors.add(peterHaber);
		
		IActorService actorService = mock(IActorService.class);
		when(actorService.getActors()).thenReturn(actors);

		// Start the server
		AbstractModule testModule = new DomainModule(actorService);
		server = AltranREST.startServer(8090, testModule);

	}
	
	
	@After
	public void tearDown() {
		try {
			server.stop();
		} catch (Exception ignore) { }
	}
}
