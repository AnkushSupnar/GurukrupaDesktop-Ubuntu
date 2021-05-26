package service;

import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import entity.entities.Counter;

import static service.ServiceUtil.*;

public class CounterService {

	HttpResponse<String>response;
	String url="";
	public CounterService()
	{
		
	}
	public List<Counter>getAllCounters()
	{
		try {
			url = "http://localhost:8080/api/counters";
			response = callRestApiGet(url);
			if (response.statusCode() != 200) {
				return null;
			} else {
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Counter>>() {});
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Counter getCounterById(int id)
	{
		try {
			url = "http://localhost:8080/api/counters/byid/"+id;
			response = callRestApiGet(url);
			Counter counter = JsonUtil.convertFromJsonToObject(response.body(), Counter.class);
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Counter getCounterByName(String name)
	{
		try {
			url = "http://localhost:8080/api/counters/byname/"+name;
			//String path = url.replaceAll(" ", "%20");
			//System.out.println(path);
			response = callRestApiGet(url);
			if(response.statusCode()==200)
			return JsonUtil.convertFromJsonToObject(response.body(),Counter.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Counter getCounterByPerson(String name)
	{
		try {
			url = "http://localhost:8080/api/counters/byperson/"+name;
			response = callRestApiGet(url);
			if(response.statusCode()==200)
			return JsonUtil.convertFromJsonToObject(response.body(),Counter.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<String>getAllCounterNames()
	{
		try {
			url="http://localhost:8080/api/counters/allnames";
			response = callRestApiGet(url);
			if(response.statusCode()==200) {
				return JsonUtil.convertFromJsonToList(response.body(),
						new TypeReference<List<String>>() {});
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public int saveCounter(Counter counter)
	{
		try {
			String json = JsonUtil.convertFromObjectToJson(counter);
			String url="http://localhost:8080/api/counters/save";
			HttpResponse<String> res = callRestApiPost(url, json);
			if(res.statusCode()==200)
			{
				return 1;
			}
			else 
				return 0;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
