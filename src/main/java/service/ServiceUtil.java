package service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ServiceUtil {

	public static HttpResponse<String> response;
	public static HttpClient client = HttpClient.newBuilder().build();
	public static HttpRequest request;

	public static HttpResponse<String> callRestApiGet(String url){
		try {
			url = url.replace(" ", "%20");
	    System.out.println("Got in Service util chages "+url);
//		request =HttpRequest.newBuilder(URI.create(url))
//				.header("Content-Type", "application/json")
//				.GET().build();
	    request = HttpRequest.newBuilder(new URI(url)).
	    		header("Content-Type", "application/json").
	    		GET().build();
		response = null;
		
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response;
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			return null;
		} catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public static HttpResponse<String> callRestApiPost(String url,String inputJson)
			throws IOException, InterruptedException
	{
		request = HttpRequest.newBuilder(URI.create(url))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(inputJson)).build();
		  response = 
				client.send(request,HttpResponse.BodyHandlers.ofString());
		return response;
	}
	public static HttpResponse<String>callRestApiPut(String url,String inputJson) throws IOException, InterruptedException
	{
		request = HttpRequest.newBuilder(URI.create(url))
				.header("Content-Type", "application/json")
				.PUT(HttpRequest.BodyPublishers.ofString(inputJson)).build();
		response = 
				client.send(request, HttpResponse.BodyHandlers.ofString());
		return response;
	}
	
}
