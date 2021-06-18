package hibernate.dao.dao;

import hibernate.entities.Bill;

import java.time.LocalDate;
import java.util.List;

public interface BillDao {
    public List<Bill> getAllBills();

    public Bill getBillByBillno(Long billno);

    public List<Bill> getBillByDate(LocalDate date);

    public List<Bill> getBillByCustomer(long customerid);

    public List<Bill> getBillByLogin(int loginid);

    public List<Bill> getDatePeriodBill(LocalDate start, LocalDate end);

    public long saveBill(Bill bill);

    public long updateBill(Bill bill);

    public int deleteTransactionByBillno(long billno);
}
