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
	public List<MovieBean> movies() {
		// Anti corruption layer
		List<MovieBean> movieBeans = MovieBeanHelper.asMovieBeans(movieService.getMovies());
		return movieBeans;
	}


	@GET
	@Path("/{movieId}")
	@Produces(MediaType.APPLICATION_JSON)
	public MovieBean movie(@PathParam("movieId") String movieId) {
		Movie movie = movieService.getMovie(movieId);
		
		return new MovieBean(movie);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response movieObj(@Context UriInfo uriInfo, MovieBean movieBean) {

		String id = movieService.createMovie(movieBean);
		
		// Build URI to the created movie, and return it
		URI movieUri = uriInfo.getBaseUriBuilder().path(MovieResource.class).path(id).build();
		return Response.created(movieUri).build();
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
