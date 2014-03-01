package se.altran.restkurs.webapi.movie;

import org.codehaus.jackson.annotate.JsonProperty;


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
