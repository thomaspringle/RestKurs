package se.altran.restkurs.main;

import static org.junit.Assert.*;

import org.eclipse.jetty.server.Server;
import org.junit.Test;

import com.google.inject.AbstractModule;

import se.altran.restkurs.webapi.HttpClientHelper;


public class AltranRESTServerTest {

	@Test
	public void serverTest_startServer() {
		
		try {
			AbstractModule guiceModule = new DomainModule();
			Server server = AltranREST.startServer(8090, guiceModule);

			// Verify that the server responds
			HttpClientHelper httpClientHelper = new HttpClientHelper("127.0.0.1", 8090);
			String resource = httpClientHelper.getResource("/webapi/", "application/json");
			
			assertNotNull("The server must return something to show that it is alive.", resource);
			
			server.stop();
		} catch (Exception e) {
			
			e.printStackTrace();
			assertTrue("An exception must not be thrown.", false);
		}
	}
}
