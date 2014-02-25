package se.altran.restkurs.movie;

import java.util.ArrayList;
import java.util.List;

import se.altran.restkurs.webapi.MovieBean;

public class MovieService implements IMovieService {

	List<Movie> movies = new ArrayList<>();
	
	MovieService() {
		movies.add(new Movie("Gravity", 2013));
	}
	
	public List<Movie> getMovies() {
		return movies;
	}
	
	@Override
	public Movie getMovie(String movieId) {
		for (Movie movie : movies) {
			if (movie.getId().equals(movieId)) {
				return movie;
			}
		}
		throw new RuntimeException("A movie with the UUID was not found.");
	}

	@Override
	public String createMovie(MovieBean movieBean) {
		Movie movie = new Movie(movieBean.getTitle(), movieBean.getYear());
		movies.add(movie);
		return movie.getId();
	}
}
