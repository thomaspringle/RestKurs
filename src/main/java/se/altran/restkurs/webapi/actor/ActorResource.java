package se.altran.restkurs.webapi.actor;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
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

import com.google.inject.Inject;

@Path("/actors")
public class ActorResource {

	@Inject
	private IActorService actorService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActors(	@QueryParam("firstName") String firstName, 
								@DefaultValue("-1") @QueryParam("offset") int offset, 
								@DefaultValue("-1") @QueryParam("limit") int limit) {
		
		List<Actor> actors;
		
		if (firstName != null && !firstName.isEmpty()) {
			actors = actorService.getActorsWithFirstName(firstName);
		} else if (offset != -1 && limit != -1) {
			actors = actorService.getActorsWithPagination(offset, limit);
		} else {
			actors = actorService.getActors();
		}
		
		List<ActorBean> actorBeans = ActorBeanHelper.asMovieBeans(actors);
		return Response.ok(actorBeans).build();
	}
	
	
	@GET
	@Path("/{actorId}")
	@Produces(MediaType.APPLICATION_JSON)
	public ActorBean getActor(@PathParam("actorId") String actorId) {
		Actor actor = actorService.getActor(actorId);
		return actor.asActorBean();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMovie(@Context UriInfo uriInfo, ActorBean actorBean) {
		String id = actorService.createActor(actorBean);
		
		// Build URI to the created movie, and return it
		URI actorUri = uriInfo.getBaseUriBuilder().path(ActorResource.class).path(id).build();
		return Response.created(actorUri).build();
	}
}
