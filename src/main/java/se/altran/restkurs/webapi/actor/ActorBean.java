package se.altran.restkurs.webapi.actor;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class ActorBean {

	private String id;
	private String firstName;
	private String lastName;
	private List<String> movies;
	
	ActorBean() {}

	public ActorBean(String id, String firstName, String lastName, List<String> movies) {
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
	public List<String> getMovies() {
		return movies;
	}
}
