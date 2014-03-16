package se.altran.restkurs.webapi.actor;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import se.altran.restkurs.actor.Actor;
import se.altran.restkurs.actor.IActorService;
import se.altran.restkurs.webapi.movie.MovieResource;

import com.google.inject.Inject;

@Path("/actors")
public class ActorResource {

	@Inject
	private IActorService actorService;
	
	// Implement "GET /actors" here
	public Response getActors() {
		
		List<Actor> actors = actorService.getActors();

		List<ActorBean> actorBeans = ActorBeanHelper.asActorBeans(actors);
		return Response.ok(actorBeans).build();
	}
	
	// Implement "GET /actors/{actorId}" here
	public ActorBean getActor() {
		String actorId = null;
		Actor actor = actorService.getActor(actorId);
		return actor.asActorBean();
	}
	
	// Implement "GET /actors/{actorId}/movies" here
	public List<MovieLinkBean> getMoviesForActor() {
		List<MovieLinkBean> movieLinks = null;
		return movieLinks;
	}

	// Implement "POST /actors" here 
	public Response addMovie() {
		// String id = actorService.createActor(actorBean);
		
		return null;
	}
	
	// Implement "DELETE /actors" here
	public Response deleteMovies() {
		// List<Actor> actors = actorService.deleteActors(userToken);
		// List<ActorBean> actorBeans = ActorBeanHelper.asActorBeans(actors);
		return null;
	}
}
