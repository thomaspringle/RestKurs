package se.altran.restkurs.movie;

import org.codehaus.jackson.annotate.JsonProperty;

public class Movie {

	private String title;
	private int year;
	
	Movie() {} // Used for deserialization
	
	public Movie(String title, int year) {
		this.title = title;
		this.year = year;
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
