package se.altran.restkurs.main;

import se.altran.restkurs.movie.IMovieService;
import se.altran.restkurs.movie.MovieService;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;


public class DomainModule extends AbstractModule {
	
    @Override
    protected void configure() {
    	
//    	bind(ConnectedDevicesService.class).in(Scopes.SINGLETON);
        bind(IMovieService.class).to(MovieService.class).in(Scopes.SINGLETON);
    }    
}