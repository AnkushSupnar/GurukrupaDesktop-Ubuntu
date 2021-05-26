package service;

import com.fasterxml.jackson.core.type.TypeReference;
import entity.entities.Bill;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

public class BillService {
    HttpResponse<String> response;
    String url = "http://localhost:8080/api/bills";

    public List<Bill> getAllBills() {
        try {
            response = ServiceUtil.callRestApiGet(url);
            if (response.statusCode() == 200) {
                return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Bill>>() {
                });
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bill getBillByBillNo(long billno) {
        try {

            response = ServiceUtil.callRestApiGet(url + "/bybillno/" + billno);
            if (response.statusCode() == 200)
                return JsonUtil.convertFromJsonToObject(response.body(), Bill.class);
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Bill> getBillByDate(LocalDate date) {
        try {
            response = ServiceUtil.callRestApiGet(url + "/btdate" + date);
            if (response.statusCode() == 200) {
                return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Bill>>() {
                });
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Bill> getBillsByCustomer(long customerId) {
        try {
            response = ServiceUtil.callRestApiGet(url + "/bycustomer/" + customerId);
            if (response.statusCode() == 200) {
                return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Bill>>() {
                });
            } else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Bill> getBillsByLogin(int loginId) {
        try {
            response = ServiceUtil.callRestApiGet(url + "/bylogin/" + loginId);
            if (response.statusCode() == 200)
                return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Bill>>() {
                });
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Bill> getDatePeriodBills(LocalDate start, LocalDate end) {
        try {
            response = ServiceUtil.callRestApiGet(url + "/bydateperiod/" + start + "/" + end);
            if (response.statusCode() == 200)
                return JsonUtil.convertFromJsonToList(response.body(), new TypeReference<List<Bill>>() {
                });
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bill saveBill(Bill bill) {
        try {

            response = ServiceUtil.callRestApiPost(url + "/save", JsonUtil.convertFromObjectToJson(bill));
            if (response.statusCode() == 200)
                return JsonUtil.convertFromJsonToObject(response.body(), Bill.class);
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Bill updateBill(Bill bill) {
        try {
            response = ServiceUtil.callRestApiPut(url + "/update", JsonUtil.convertFromObjectToJson(bill));
            if (response.statusCode() == 200)
                return JsonUtil.convertFromJsonToObject(response.body(), Bill.class);
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
