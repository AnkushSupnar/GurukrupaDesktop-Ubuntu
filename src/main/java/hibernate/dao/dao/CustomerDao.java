package hibernate.dao.dao;

import hibernate.entities.Customer;

import java.util.List;

public interface CustomerDao {

    public List<Customer> getAllCustomer();

    public List<String > getAllCustomerNames();

    public Customer  getCustomerById( long id);

    public Customer  findByCode(String code);

    public Customer findByContact( String contact);

    public Customer findByFullName(String fname,String mname,String lname);



    public int saveCustomer(Customer customer);

    public int updateCustomer(Customer customer);


}
