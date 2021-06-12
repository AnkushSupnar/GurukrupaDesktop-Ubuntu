package hibernate.service.serviceimpl;

import hibernate.dao.dao.BillDao;
import hibernate.dao.daoimpl.BillDaoImpl;
import hibernate.entities.Bill;
import hibernate.entities.CustomerPassbook;
import hibernate.service.service.BillService;
import hibernate.service.service.CustomerPassbookService;

import java.time.LocalDate;
import java.util.List;

public class BillServiceImpl implements BillService {
    BillDao dao;
    private final CustomerPassbookService bookService;

    public BillServiceImpl() {
        this.dao = new BillDaoImpl();
        this.bookService = new CustomerPassbookServiceImpl();
    }

    @Override
    public List<Bill> getAllBills() {
        return dao.getAllBills();
    }

    @Override
    public Bill getBillByBillno(Long billno) {
        return dao.getBillByBillno(billno);
    }

    @Override
    public List<Bill> getBillByDate(LocalDate date) {
        return dao.getBillByDate(date);
    }

    @Override
    public List<Bill> getBillByCustomer(long customerid) {
        return dao.getBillByCustomer(customerid);
    }

    @Override
    public List<Bill> getBillByLogin(int loginid) {
        return dao.getBillByLogin(loginid);
    }

    @Override
    public List<Bill> getDatePeriodBill(LocalDate start, LocalDate end) {
        return dao.getDatePeriodBill(start, end);
    }

    @Override
    public int saveBill(Bill bill) {
        //add in customer passbook
        CustomerPassbook book = new CustomerPassbook();
        book.setBank(bill.getBank());
        book.setCustomer(bill.getCustomer());
        book.setDate(bill.getDate());
        book.setCredit(bill.getPaidamount());
        book.setDebit(bill.getAmount()-bill.getPaidamount());
        book.setParticulars("Bill no "+bill.getBillno());
        book.setTrid(bill.getBillno());
        bookService.saveCustomerPassbook(book);
        return dao.saveBill(bill);
    }

    @Override
    public int updateBill(Bill bill) {
        return dao.updateBill(bill);
    }

    @Override
    public int deleteTransactionByBillno(long billno) {
        return dao.deleteTransactionByBillno(billno);
    }
}
