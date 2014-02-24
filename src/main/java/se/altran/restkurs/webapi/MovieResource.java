package se.altran.restkurs.webapi;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import se.altran.restkurs.movie.IMovieService;
import se.altran.restkurs.movie.Movie;

import com.google.inject.Inject;

@Path("/movies")
public class MovieResource {

	
	@Inject
	private IMovieService movieService;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> movies() {
		return movieService.getMovies();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response movie(@Context UriInfo uriInfo, String jsonMovie) {
		String id = createMovie(jsonMovie);
		URI movieUri = uriInfo.getBaseUriBuilder().path(MovieResource.class).path(id).build();
		System.out.println(movieUri);
		return Response.created(movieUri).build();
	}
	
	private String createMovie(String jsonMovie) {
		// TODO Auto-generated method stub
		return "1234";
	}

	@PUT
	@Path("/users/{userId}")
	public String getUserById(@PathParam("userId") String userId, @QueryParam("name") String data) { 
		if (userId.isEmpty()) {
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		return "";
	}
}
