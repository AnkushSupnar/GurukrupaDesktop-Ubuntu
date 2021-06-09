package hibernate.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String bankname;
	String accountno;
	String ifsc;
	String accounttype;
	String person;
	double balance;
	public Bank() {
		super();

	}
	public Bank(String bankname, String accountno, String ifsc, String accounttype, String person, double balance) {
		super();
		this.bankname = bankname;
		this.accountno = accountno;
		this.ifsc = ifsc;
		this.accounttype = accounttype;
		this.person = person;
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Bank [id=" + id + ", bankname=" + bankname + ", accountno=" + accountno + ", ifsc=" + ifsc
				+ ", accounttype=" + accounttype + ", person=" + person + ", balance=" + balance + "]";
	}
	
}
