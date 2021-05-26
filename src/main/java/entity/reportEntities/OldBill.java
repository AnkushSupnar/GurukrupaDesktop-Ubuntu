package entity.reportEntities;

import java.time.LocalDate;

public class OldBill {
    long billno;
    LocalDate date;
    String customername;
    double amount;
    double remaining;

    public OldBill() {
        super();
    }

    public OldBill(long billno, LocalDate date, String customername, double amount, double remaining) {
        this.billno = billno;
        this.date = date;
        this.customername = customername;
        this.amount = amount;
        this.remaining = remaining;
    }

    public long getBillno() {
        return billno;
    }

    public void setBillno(long billno) {
        this.billno = billno;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRemaining() {
        return remaining;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }

    @Override
    public String toString() {
        return "OldBill{" +
                "billno=" + billno +
                ", date=" + date +
                ", customername='" + customername + '\'' +
                ", amount=" + amount +
                ", remaining=" + remaining +
                '}';
    }
}
