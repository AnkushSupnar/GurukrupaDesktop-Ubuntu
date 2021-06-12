package hibernate.service.service;

import hibernate.dao.dao.CustomerDao;
import hibernate.entities.Customer;

public interface CustomerService extends CustomerDao {
    public Customer getCustomerByName(String name);
}
