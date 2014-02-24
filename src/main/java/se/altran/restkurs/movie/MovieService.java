package se.altran.restkurs.movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService implements IMovieService {

	List<Movie> movies = new ArrayList<>();
	
	MovieService() {
		movies.add(new Movie("Gravity", 2013));
	}
	
	public List<Movie> getMovies() {
		return movies;
	}
}
