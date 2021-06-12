package hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name="counter")
public class Counter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name="countername")
	String countername;
	String shopno;
	String road;	
	String city;
	String taluka;
	String district;
	int pin;
	String person;
	String contact;
	char billinitial;
	public Counter() {
		super();
	}
	
	public Counter(String countername,
			String shopno,
			String road,
			String city, 
			String taluka,
			String district, 
			int pin, 
			String person, 
			String contact,
			char billinitial) {
		super();
		this.countername = countername;
		this.shopno = shopno;
		this.road = road;		
		this.city = city;
		this.taluka = taluka;
		this.district = district;
		this.pin = pin;
		this.person = person;
		this.contact = contact;
		this.billinitial = billinitial;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getShopno() {
		return shopno;
	}
	public void setShopno(String shopno) {
		this.shopno = shopno;
	}
	public String getRoad() {
		return road;
	}
	public void setRoad(String road) {
		this.road = road;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getTaluka() {
		return taluka;
	}
	public void setTaluka(String taluka) {
		this.taluka = taluka;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getPerson() {
		return person;
	}
	public void setPerson(String person) {
		this.person = person;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public char getBillinitial() {
		return billinitial;
	}
	public void setBillinitial(char billinitial) {
		this.billinitial = billinitial;
	}
	public String getCountername() {
		return countername;
	}

	public void setCountername(String countername) {
		this.countername = countername;
	}

	@Override
	public String toString() {
		return "Counter [id=" + id + ", counterName=" + countername + ", shopNo=" + shopno + ", road=" + road
				+ ", city=" + city + ", taluka=" + taluka + ", district=" + district + ", pin="
				+ pin + ", person=" + person + ", contact=" + contact + ", billinitial=" + billinitial + "]";
	}

	
	
}