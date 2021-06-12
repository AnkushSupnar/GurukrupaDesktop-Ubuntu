package hibernate.dao.dao;

import hibernate.entities.CustomerPassbook;

import java.time.LocalDate;
import java.util.List;

public interface CustomerPassbookDao {
    List<CustomerPassbook> getAllCustomerPassbook();

    CustomerPassbook getCustomerPassbookById(long id);

    List<CustomerPassbook> getCustomerPassbookbyCustomer(long customerId);

    List<CustomerPassbook> getCustomerPassbookbyByDatePeriod(long customerId, LocalDate start, LocalDate end);

    List<CustomerPassbook> getCustomerPassbookbyByDate(long customerId, LocalDate date);

    int saveCustomerPassbook(CustomerPassbook book);

}
