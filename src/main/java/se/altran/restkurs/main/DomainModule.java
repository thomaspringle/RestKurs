package se.altran.restkurs.main;

import se.altran.restkurs.movie.IMovieService;
import se.altran.restkurs.movie.MovieService;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;


public class DomainModule extends AbstractModule {
	
	private IMovieService movieService;
	
	public DomainModule(IMovieService movieService) {
		this.movieService = movieService;
	}
	
	public DomainModule() {
		movieService = new MovieService();
	}
	
    @Override
    protected void configure() {
    	
    }    
    
    @Provides @Singleton
    private IMovieService provideIMovieService() {
    	return movieService;
    }
}