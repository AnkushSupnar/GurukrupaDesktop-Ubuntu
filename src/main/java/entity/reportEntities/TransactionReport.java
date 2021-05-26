package entity.reportEntities;

public class TransactionReport {

	int    id;
	String name;
	String metal;
	double purity;
	double weight;
	double qty;
	double labour;
	double rate;
	double amount;
	public TransactionReport() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransactionReport(int id, String name, String metal, double purity, double weight,double qty, double labour,
			double rate, double amount) {
		super();
		this.id = id;
		this.name = name;
		this.metal = metal;
		this.purity = purity;
		this.weight = weight;
		this.qty = qty;
		this.labour = labour;
		this.rate = rate;
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMetal() {
		return metal;
	}
	public void setMetal(String metal) {
		this.metal = metal;
	}
	public double getPurity() {
		return purity;
	}
	public void setPurity(double purity) {
		this.purity = purity;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getLabour() {
		return labour;
	}
	public void setLabour(double labour) {
		this.labour = labour;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "TransactionReport [id=" + id + ", name=" + name + ", metal=" + metal + ", purity=" + purity
				+ ", weight=" + weight + ", qty=" + qty + ", labour=" + labour + ", rate=" + rate + ", amount=" + amount
				+ "]";
	}
	
	
}
