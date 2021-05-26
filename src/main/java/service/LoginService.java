package service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import entity.entities.Counter;
import entity.entities.Login;


public class LoginService {
	HttpResponse<String> response;

	public String checkConnrction()
	{
		String url = "http://localhost:8080/api";
		response = ServiceUtil.callRestApiGet(url);
		if(response.statusCode()==200)
		{
			return response.body();
		}
		else
		{
			return "not connected";
		}
	}
	public Login getLoginById(int id) {
		try {
			String url = "http://localhost:8080/api/logins/byid/" + id;
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Login.class);
			else
				return null;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Login getLoginByName(String name) {
		try {
			String url = "http://localhost:8080/api/logins/byname/" + name;
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Login.class);
			else
				return null;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Login getLoginByPerson(String person) {
		try {
			
			String url = "http://localhost:8080/api/logins/byperson/?person=".concat(person);
			System.out.println(url);
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Login.class);
			else
				return null;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Counter getLoginCounter(int id) {
		try {
			String url = "http://localhost:8080/api/logins/getcounter/" + id;
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Counter.class);
			else
				return null;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Login> getAllLogins() {
		try {
			String url = "http://localhost:8080/api/logins";
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Login>>() {
				});
			else
				return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public String validateLogin(String username,String password)
	{
		return ServiceUtil.callRestApiGet("http://localhost:8080/api/logins/validate/"+username+"/"+password).body();
	}
	public Login saveLogin(Login login)
	{
		try {
		String url="http://localhost:8080/api/logins/save";
		
			return JsonUtil.convertFromJsonToObject(
					ServiceUtil.callRestApiPost(url, 
							JsonUtil.convertFromObjectToJson(login)).body(),
					Login.class);
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public int updateLogin(Login login)
	{
		try {
			String url="http://localhost:8080/api/logins/update";
			String jsonInput = JsonUtil.convertFromObjectToJson(login);
			response = ServiceUtil.callRestApiPut(url, jsonInput);
			if(response.statusCode()==200)
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
	public List<String>getAllUserNames()
	{
		try {
		String url="http://localhost:8080/api/logins/allusernames";
		response = ServiceUtil.callRestApiGet(url);		
			return JsonUtil.convertFromJsonToList(ServiceUtil.callRestApiGet(url).body(),new TypeReference<List<String>>() {});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
