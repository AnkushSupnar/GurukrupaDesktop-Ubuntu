package hibernate.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Mode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	LocalDate date;
	double amount;
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="customerid",referencedColumnName = "id")	
	Customer customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="loginid",referencedColumnName = "id")
	Login login;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="bankid",referencedColumnName = "id")
	Bank bank;
	
	@OneToMany(mappedBy = "mode",fetch = FetchType.EAGER)
	List<ModeTransaction>transaction = new ArrayList<>();

	public Mode() {
		super();
	}

	public Mode(LocalDate date, double amount, Customer customer, Login login, Bank bank,
			List<ModeTransaction> transaction) {
		super();
		this.date = date;
		this.amount = amount;
		this.customer = customer;
		this.login = login;
		this.bank = bank;
		this.transaction = transaction;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public Bank getBank() {
		return bank;
	}

	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public List<ModeTransaction> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<ModeTransaction> transaction) {
		this.transaction = transaction;
	}

	@Override
	public String toString() {
		return "Mode [id=" + id + ", date=" + date + ", amount=" + amount + ", customer=" + customer + ", login="
				+ login + ", bank=" + bank + ", transaction=" + transaction + "]";
	}
	
}