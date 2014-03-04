package se.altran.restkurs.webapi.actor;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import se.altran.restkurs.actor.Actor;
import se.altran.restkurs.actor.IActorService;

import com.google.inject.Inject;

@Path("/actors")
public class ActorResource {

	@Inject
	private IActorService actorService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getActors() {
		List<Actor> actors = actorService.getActors();
		List<ActorBean> actorBeans = ActorBeanHelper.asMovieBeans(actors);
		return Response.ok(actorBeans).build();
	}
}
