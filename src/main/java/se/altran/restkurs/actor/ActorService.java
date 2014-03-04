package se.altran.restkurs.actor;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Singleton;

@Singleton
public class ActorService implements IActorService {

	private List<Actor> actors = new ArrayList<>();
	
	public ActorService() {
		
	}

	public List<Actor> getActors() {
		return new ArrayList<>(actors);
	}
}
