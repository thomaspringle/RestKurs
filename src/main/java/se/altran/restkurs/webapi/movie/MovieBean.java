package se.altran.restkurs.webapi.movie;

import org.codehaus.jackson.annotate.JsonProperty;

import se.altran.restkurs.movie.Movie;


public class MovieBean {
	
	private String id;
	private String title;
	private int year;
	
	MovieBean() {}
	
	public MovieBean(String id, String title, int year) {
		this.title = title;
		this.year = year;
		this.id = id;
	}
	
	public MovieBean(Movie movie) {
		this.title = movie.getTitle();
		this.year = movie.getYear();
		this.id = movie.getId();
	}

	@JsonProperty
	public String getId() {
		return id;
	}
	
	@JsonProperty
	public String getTitle() {
		return title;
	}

	@JsonProperty
	public int getYear() {
		return year;
	}
}
