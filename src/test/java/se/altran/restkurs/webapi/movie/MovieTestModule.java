package se.altran.restkurs.webapi.movie;

import se.altran.restkurs.movie.IMovieService;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class MovieTestModule extends AbstractModule {
	
	private IMovieService movieService;
	MovieTestModule(IMovieService movieService) {
		this.movieService = movieService;
		
	}
    @Override
    protected void configure() {

    }
    
    @Provides
    private IMovieService provideIMovieService() {
    	return movieService;
    }
}