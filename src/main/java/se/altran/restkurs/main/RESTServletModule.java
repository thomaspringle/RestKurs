package se.altran.restkurs.main;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import se.altran.restkurs.webapi.movie.MovieResource;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;


public class RESTServletModule extends ServletModule {
	
    @Override
    protected void configureServlets() {
    	
//    	bind(ConnectedDevicesService.class).in(Scopes.SINGLETON);
        bind(MovieResource.class);

        
        // hook Jersey into Guice Servlet
        bind(GuiceContainer.class);

        // hook Jackson into Jersey as the POJO <-> JSON mapper
        bind(JacksonJsonProvider.class).in(Scopes.SINGLETON);
        
        serve("/*").with(GuiceContainer.class);
    }   
    
    
}