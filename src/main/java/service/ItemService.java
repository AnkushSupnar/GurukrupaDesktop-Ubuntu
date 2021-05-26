package service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.entities.Item;


public class ItemService {

	private HttpResponse<String>response;
	private ObjectMapper objectMapper;
	String url="";
	public ItemService() {
	this.objectMapper = new ObjectMapper();
	}
	public Item getItemById(long id)
	{
		try {
			url="http://localhost:8080/api/items/byid/"+id;
			response  =ServiceUtil.callRestApiGet(url);
			if(response.statusCode()==200)
			{
				return objectMapper.readValue(response.body(),Item.class);
			}
			else return null;
		} catch (Exception e) {
			return null;
		}
	}
	public List<Item>getAllItems()
	{
		try {
			url="http://localhost:8080/api/items";
			response = ServiceUtil.callRestApiGet(url);
			if(response.statusCode()==200)
			{
				List<Item>list = objectMapper.readValue(response.body(), new TypeReference<List<Item>>() {});
				return list;				
			} else
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<String>getAllItemNames()
	{
		try {
			url="http://localhost:8080/api/items/allnames";
			response = ServiceUtil.callRestApiGet(url);
			if(response.statusCode()==200)
			{
				List<String>list = objectMapper.readValue(response.body(), new TypeReference<List<String>>() {});
				return list;				
			} else
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Item getItemByName(String name)
	{
		try {
		//	name = name.replace(" ", "%20");
			url = "http://localhost:8080/api/items/byname/"+name;
			response = ServiceUtil.callRestApiGet(url);
			if(response.statusCode()==200) 
			return  objectMapper.readValue(response.body(), Item.class);
			else
				return null;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Item saveItem(Item item)
	{
		
			url = "http://localhost:8080/api/items/save";
			try {
				String json = JsonUtil.convertFromObjectToJson(item);
				response = ServiceUtil.callRestApiPost(url, json);
				if(response.statusCode()==200)
				{
			
					return JsonUtil.convertFromJsonToObject(response.body(),Item.class);
				}
				else {
					System.out.println("Json Value=\n"+response.body());
					return null;
				}
				
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
	public Item updateItem(Item item)
	{
		try {
			url = "http://localhost:8080/api/items/update";
			String json = JsonUtil.convertFromObjectToJson(item);
			response = ServiceUtil.callRestApiPut(url, json);
			if(response.statusCode()==200)
				return JsonUtil.convertFromJsonToObject(response.body(),Item.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
