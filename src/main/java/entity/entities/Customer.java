package entity.entities;

public class Customer {

	long id;
	String code;
	String fname;
	String mname;
	String lname;
	String houseno;
	String street;
	String city;
	String taluka;
	String district;
	int pin;
	String contact;
	public Customer() {
		super();
	}
	public Customer(String code, String fname, String mname, String lname, String houseno, String street, String city,
			String taluka, String district, int pin, String contact) {
		super();
		this.code = code;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.houseno = houseno;
		this.street = street;
		this.city = city;
		this.taluka = taluka;
		this.district = district;
		this.pin = pin;
		this.contact = contact;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getHouseno() {
		return houseno;
	}
	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", code=" + code + ", fname=" + fname + ", mname=" + mname + ", lname=" + lname
				+ ", houseno=" + houseno + ", street=" + street + ", city=" + city + ", taluka=" + taluka
				+ ", district=" + district + ", pin=" + pin + ", contact=" + contact + "]";
	}
	
	
}
