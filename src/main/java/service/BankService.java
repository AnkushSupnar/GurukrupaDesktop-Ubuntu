package service;

import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import entity.entities.Bank;


public class BankService {

	HttpResponse<String> response;
	String url = "http://localhost:8080/api/banks";

	public BankService() {
		// TODO Auto-generated constructor stub
	}

	public Bank getBankById(int id) {
		try {
			response = ServiceUtil.callRestApiGet(url+"/byid/"+id);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Bank.class);
			else
				return null;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Bank getBankByBankName(String name)
	{
		try {
			response = ServiceUtil.callRestApiGet(url+"/bybankname/"+name);
			if(response.statusCode()==200)
				return JsonUtil.convertFromJsonToObject(response.body(),Bank.class);
			else
				return null;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public List<Bank> getAllBanks() {
		try {
			response = ServiceUtil.callRestApiGet(url);
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Bank>>() {
				});
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> getAllBankNames() {
		try {
			response = ServiceUtil.callRestApiGet(url+"/allbanknames");
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<String>>() {
				});
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public double getBankBalance(int id) {
		try {
			response = ServiceUtil.callRestApiGet(url+"/getbalance/"+id);
			if (response.statusCode() == 200)
				return Double.parseDouble(response.body());
			else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int addBankBalance(int id, double balance) {
		try {
			//url = "http://localhost:8080/api/banks/addbalance/" + id + "/" + balance;
			response = ServiceUtil.callRestApiPut(url+"/addbalance/" + id + "/" + balance, "");
			if (response.statusCode() == 200)
				return 1;
			else
				return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int reduceBankBalance(int id, double balance) {
		try {
			//url = "http://localhost:8080/api/banks/reducebalance/" + id + "/" + balance;
			response = ServiceUtil.callRestApiPut(url+"/reducebalance/" + id + "/" + balance, "");
			if (response.statusCode() == 200)
				return 1;
			else
				return 0;

		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public Bank updateBank(Bank bank) {
		try {
			//url = "http://localhost:8080/api/banks/update";
			response = ServiceUtil.callRestApiPut(url+"/update", JsonUtil.convertFromObjectToJson(bank));
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Bank.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Bank saveBank(Bank bank) {
		try {
			//url = "http://localhost:8080/api/banks/save";
			response = ServiceUtil.callRestApiPost(url+"/save", JsonUtil.convertFromObjectToJson(bank));
			if (response.statusCode() == 200)
				return JsonUtil.convertFromJsonToObject(response.body(), Bank.class);
			else
				return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
