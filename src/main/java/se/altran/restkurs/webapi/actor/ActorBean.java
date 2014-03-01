package se.altran.restkurs.webapi.actor;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import se.altran.restkurs.webapi.movie.MovieBean;

public class ActorBean {

	String id;
	String firstName;
	String lastName;
	List<MovieBean> movies;
	
	ActorBean() {}

	public ActorBean(String id, String firstName, String lastName, List<MovieBean> movies) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.movies = new ArrayList<>(movies);
	}
	

	@JsonProperty
	public String getId() {
		return id;
	}
	
	@JsonProperty
	public String getFirstName() {
		return firstName;
	}
	
	@JsonProperty
	public String getLastName() {
		return lastName;
	}
	
	@JsonProperty
	public List<MovieBean> getMovies() {
		return movies;
	}
}
