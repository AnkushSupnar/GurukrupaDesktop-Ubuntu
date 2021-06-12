package hibernate.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="customerpassbook")
public class CustomerPassbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    LocalDate date;
    String particulars;
    double credit;
    double debit;
    long trid;
    @ManyToOne
    @JoinColumn(name="customerid")
    Customer customer;
    @ManyToOne
    @JoinColumn(name="bankid")
    Bank bank;

    public CustomerPassbook() {
        super();
    }

    public CustomerPassbook(LocalDate date, String particulars, double credit, double debit, long trid, Customer customer, Bank bank) {
        this.date = date;
        this.particulars = particulars;
        this.credit = credit;
        this.debit = debit;
        this.trid = trid;
        this.customer = customer;
        this.bank = bank;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public long getTrid() {
        return trid;
    }

    public void setTrid(long trid) {
        this.trid = trid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "CustomerPassbook{" +
                "id=" + id +
                ", date=" + date +
                ", particulars='" + particulars + '\'' +
                ", credit=" + credit +
                ", debit=" + debit +
                ", trid=" + trid +
                ", customer=" + customer +
                ", bank=" + bank +
                '}';
    }
}
