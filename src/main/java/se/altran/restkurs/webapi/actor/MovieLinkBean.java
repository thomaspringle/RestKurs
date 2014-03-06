package se.altran.restkurs.webapi.actor;

public class MovieLinkBean {

	private String id;
	private String uri;

	MovieLinkBean(){};
	
	public MovieLinkBean(String id, String uri) {
		this.id = id;
		this.uri = uri;
	}

	public String getId() {
		return id;
	}

	public String getUri() {
		return uri;
	}
	
}
