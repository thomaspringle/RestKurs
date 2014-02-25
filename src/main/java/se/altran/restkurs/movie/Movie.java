package se.altran.restkurs.movie;

import java.util.UUID;

public class Movie {

	private final String id;
	private String title;
	private int year;
	
	public Movie(String title, int year) {
		this.title = title;
		this.year = year;
		id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public int getYear() {
		return year;
	}
}
