package hibernate.service.serviceimpl;

import hibernate.dao.dao.CustomerDao;
import hibernate.dao.daoimpl.CustomerDaoImpl;
import hibernate.entities.Customer;
import hibernate.service.service.CustomerService;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDao dao;
    public CustomerServiceImpl()
    {
        this.dao=new CustomerDaoImpl();
    }

    @Override
    public List<Customer> getAllCustomer() {
        return dao.getAllCustomer();
    }

    @Override
    public List<String> getAllCustomerNames() {
        return dao.getAllCustomerNames();
    }

    @Override
    public Customer getCustomerById(long id) {
        return dao.getCustomerById(id);
    }

    @Override
    public Customer findByCode(String code) {
        return dao.findByCode(code);
    }

    @Override
    public Customer findByContact(String contact) {
        return dao.findByContact(contact);
    }

    @Override
    public Customer findByFullName(String fname, String mname, String lname) {
        return dao.findByFullName(fname,mname,lname);
    }

    @Override
    public int saveCustomer(Customer customer) {
        return dao.saveCustomer(customer);
    }

    @Override
    public int updateCustomer(Customer customer) {
        return dao.updateCustomer(customer);
    }

    @Override
    public Customer getCustomerByName(String name) {
        String[] n = name.split(" ");
        if(n.length!=3)
            return null;
        else
        return findByFullName(n[0],n[1],n[2]);
    }
}
