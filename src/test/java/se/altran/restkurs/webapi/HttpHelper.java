package se.altran.restkurs.webapi;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.util.EntityUtils;

public class HttpHelper {

	private BasicHttpContext basicHttpContext;
	private HttpHost target;
	private DefaultHttpClient httpClient;
	
	
	public HttpHelper(String site, int port) {
		httpClient = new DefaultHttpClient();
		
		httpClient.getParams().setParameter("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0)");
		
		basicHttpContext = new BasicHttpContext();
		target = new HttpHost(site, port, "http");
	}

	public HttpResponse executeMethod(HttpRequest method) throws Exception {
		HttpResponse response = httpClient.execute(target, method, basicHttpContext);
		return response;
	}
	
	public static String responseData(HttpResponse response, String contentType) throws Exception {
		String responseContentType = contentType + "; charset=UTF-8";
		response.setHeader("Content-Type", responseContentType);
		HttpEntity entity = response.getEntity();
		String resource = new String(EntityUtils.toString(entity).getBytes("ISO-8859-1"), "UTF-8");
		
		EntityUtils.consume(entity);
		return resource;
	}

}
