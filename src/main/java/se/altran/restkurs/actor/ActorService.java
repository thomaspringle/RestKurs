package se.altran.restkurs.actor;

import java.util.ArrayList;
import java.util.List;

import se.altran.restkurs.movie.Movie;

import com.google.inject.Singleton;

@Singleton
public class ActorService implements IActorService {

	private List<Actor> actors = new ArrayList<>();
	
	public ActorService() {
		actors = new ArrayList<>();
		ArrayList<Movie> peterHaberMovies = new ArrayList<Movie>();
		peterHaberMovies.add(new Movie("Sunes Sommar", 1993));
		peterHaberMovies.add(new Movie("Beck", 1999));
		
		ArrayList<Movie> sandraBullockMovies = new ArrayList<Movie>();
		sandraBullockMovies.add(new Movie("Speed", 1994));
		sandraBullockMovies.add(new Movie("28 Days", 2000));
		sandraBullockMovies.add(new Movie("crash", 2004));
		sandraBullockMovies.add(new Movie("Gravity", 2013));
		
		Actor sandraBullock = new Actor("Sandra", "Bullock", sandraBullockMovies);
		Actor peterHaber = new Actor("Peter", "Haber", peterHaberMovies);

		actors.add(sandraBullock);
		actors.add(peterHaber);
	}

	public List<Actor> getActors() {
		return new ArrayList<>(actors);
	}

	@Override
	public Actor getActor(String actorId) {

		for (Actor actor : actors) {
			if (actor.hasId(actorId)) {
				return actor;
			}
		}
		throw new RuntimeException("Actor not found!");
	}
	
}
