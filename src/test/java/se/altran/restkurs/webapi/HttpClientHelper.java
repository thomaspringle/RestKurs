package se.altran.restkurs.webapi;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

public class HttpClientHelper {

	private BasicHttpContext basicHttpContext;
	private HttpHost target;
	private DefaultHttpClient httpClient;
	
	public HttpClientHelper(String site, int port) {
		httpClient = new DefaultHttpClient();
		
		httpClient.getParams().setParameter("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)");
		
		basicHttpContext = new BasicHttpContext();
		target = new HttpHost(site, port, "http");
	}
	
	public String getResource(String uri, String accept) throws Exception {
		HttpGet httpget = new HttpGet(uri);
		httpget.setHeader("Accept", accept);
		HttpResponse response = httpClient.execute(target, httpget, basicHttpContext);
		String responseContentType = accept + "; charset=UTF-8";
		response.setHeader("Content-Type", responseContentType);
		
		HttpEntity entity = response.getEntity();
		String resource = new String(EntityUtils.toString(entity).getBytes("ISO-8859-1"), "UTF-8");
		
		EntityUtils.consume(entity);
		
		return resource;
	}
	
	public static void patch(String url, String sid) throws IOException {
//		HttpPatch
	}
}
