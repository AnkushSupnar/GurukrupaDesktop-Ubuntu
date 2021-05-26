package entity.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class Transaction {
	long id;
	Item item;
	double rate;
	double quantity;
	@JsonIgnore
	Bill bill;
	public Transaction() {
		super();
	}
	public Transaction(Item item, double rate, double quantity, Bill bill) {
		super();
		this.item = item;
		this.rate = rate;
		this.quantity = quantity;
		this.bill = bill;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", item=" + item + ", rate=" + rate + ", quantity=" + quantity + ", bill="
				+ bill.getBillno() + "]";
	}
	
}
