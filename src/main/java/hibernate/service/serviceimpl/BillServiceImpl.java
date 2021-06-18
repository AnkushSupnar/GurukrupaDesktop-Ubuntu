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
    public long saveBill(Bill bill) {
        //add in customer passbook
        CustomerPassbook book = new CustomerPassbook();
        book.setBank(bill.getBank());
        book.setCustomer(bill.getCustomer());
        book.setDate(bill.getDate());
        book.setCredit(bill.getPaidamount());
        book.setDebit(bill.getAmount()-bill.getPaidamount());
        long billno =dao.saveBill(bill);
        if(billno!=0)
        {
            book.setTrid(billno);
            book.setParticulars("Bill no "+bill.getBillno());
            bookService.saveCustomerPassbook(book);

            return 1;
        }
        else
            return 0;
    }

    @Override
    public long updateBill(Bill bill) {
        CustomerPassbook book = bookService.getCustomerPassbookByBillNo(bill.getBillno());
        book.setBank(bill.getBank());
        book.setCustomer(bill.getCustomer());
        book.setDate(bill.getDate());
        book.setCredit(bill.getPaidamount());
        book.setDebit(bill.getAmount()-bill.getPaidamount());
        book.setParticulars("Bill no "+bill.getBillno());
        book.setTrid(bill.getBillno());
        long billno =dao.updateBill(bill);
        book.setTrid(billno);
        bookService.saveCustomerPassbook(book);
        return 2;
    }

    @Override
    public int deleteTransactionByBillno(long billno) {
        return dao.deleteTransactionByBillno(billno);
    }
}
