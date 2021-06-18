package hibernate.service.serviceimpl;

import hibernate.dao.dao.CustomerPassbookDao;
import hibernate.dao.daoimpl.CustomerPassbookDaoImpl;
import hibernate.entities.Customer;
import hibernate.entities.CustomerPassbook;
import hibernate.service.service.CustomerPassbookService;

import java.time.LocalDate;
import java.util.List;

public class CustomerPassbookServiceImpl implements CustomerPassbookService {
    private final CustomerPassbookDao dao;
    public CustomerPassbookServiceImpl()
    {
        this.dao = new CustomerPassbookDaoImpl();
    }

    @Override
    public List<CustomerPassbook> getAllCustomerPassbook() {
        return dao.getAllCustomerPassbook();
    }

    @Override
    public CustomerPassbook getCustomerPassbookById(long id) {
        return dao.getCustomerPassbookById(id);
    }

    @Override
    public CustomerPassbook getCustomerPassbookByBillNo(long billno) {
        return dao.getCustomerPassbookByBillNo(billno);
    }

    @Override
    public List<CustomerPassbook> getCustomerPassbookbyCustomer(long customerId) {
        return dao.getCustomerPassbookbyCustomer(customerId);
    }

    @Override
    public List<CustomerPassbook> getCustomerPassbookbyByDatePeriod(long customerId, LocalDate start, LocalDate end) {
        return dao.getCustomerPassbookbyByDatePeriod(customerId,start,end);
    }

    @Override
    public List<CustomerPassbook> getCustomerPassbookbyByDate(long customerId, LocalDate date) {
        return dao.getCustomerPassbookbyByDate(customerId,date);
    }

    @Override
    public int saveCustomerPassbook(CustomerPassbook book) {
        return dao.saveCustomerPassbook(book);
    }
}
