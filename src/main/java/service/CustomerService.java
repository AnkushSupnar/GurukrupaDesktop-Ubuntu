package service;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import entity.entities.Customer;

public class CustomerService {
	private HttpResponse<String> response;
	String url = "";

	public CustomerService() {

	}
	public List<Customer> getAllCustomer() {
		try {
			url = "http://localhost:8080/api/customers/";
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Customer>>() {
				});
			else
				return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	public List<String> getAllCustomerNames() {
		try {
			url = "http://localhost:8080/api/customers/allnames";
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<String>>() {});
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Customer getCuatomerById(long id) {
		try {
			url = "http://localhost:8080/api/customers/byid/" + id;
			return JsonUtil.convertFromJsonToObject(ServiceUtil.callRestApiGet(url).body(), Customer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public Customer getCustomerByName(String name)
	{
		try {
			name = name.replace(" ","%20");
			url = "http://localhost:8080/api/customers/byname/"+name;
			return JsonUtil.convertFromJsonToObject(ServiceUtil.callRestApiGet(url).body(), Customer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Customer getCuatomerByCode(String code) {
		try {
			url = "http://localhost:8080/api/customers/bycode/" + code;
			response = ServiceUtil.callRestApiGet(url);
			System.out.println(response.body());
			if(response.statusCode()==200)
			return JsonUtil.convertFromJsonToObject(response.body(), Customer.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Customer getCuatomerByFullName(String fname, String mname, String lname) {
		try {
			url = "http://localhost:8080/api/customers/byfullname/" + fname + "/" + mname + "/" + lname;
			return JsonUtil.convertFromJsonToObject(ServiceUtil.callRestApiGet(url).body(), Customer.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Customer saveCustomer(Customer customer) {
		try {
			url = "http://localhost:8080/api/customers/save";
			response = ServiceUtil.callRestApiPost(url, JsonUtil.convertFromObjectToJson(customer));
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Customer.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Customer updateCustomer(Customer customer) {
		try {
			url = "http://localhost:8080/api/customers/update";
			response = ServiceUtil.callRestApiPut(url, JsonUtil.convertFromObjectToJson(customer));
			if (response.statusCode() == 200) {
				return JsonUtil.convertFromJsonToObject(response.body(), Customer.class);
			} else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
