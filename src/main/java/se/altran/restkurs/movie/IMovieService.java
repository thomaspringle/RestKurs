package se.altran.restkurs.movie;

import java.util.List;

import se.altran.restkurs.webapi.MovieBean;

public interface IMovieService {

	public List<Movie> getMovies();

	public Movie getMovie(String movieId);

	public String createMovie(MovieBean movieBean);

}