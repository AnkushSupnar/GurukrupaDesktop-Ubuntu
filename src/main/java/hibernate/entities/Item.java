package hibernate.entities;

import javax.persistence.*;

@Entity
@Table(name="item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;	
	String itemname;
	String metal;
	double metalweight;
	double otherweight;
	String weightunit;
	double labouruchareges;
	double othercharges;
	double purity;
	int hsn;
	public Item() {
		super();
	}
	public Item(String itemname, String metal, double metalweight, double otherweight, String weightunit,
			double labouruchareges, double othercharges, double purity, int hsn) {
		super();
		this.itemname = itemname;
		this.metal = metal;
		this.metalweight = metalweight;
		this.otherweight = otherweight;
		this.weightunit = weightunit;
		this.labouruchareges = labouruchareges;
		this.othercharges = othercharges;
		this.purity = purity;
		this.hsn = hsn;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public String getMetal() {
		return metal;
	}
	public void setMetal(String metal) {
		this.metal = metal;
	}
	public double getMetalweight() {
		return metalweight;
	}
	public void setMetalweight(double metalweight) {
		this.metalweight = metalweight;
	}
	public double getOtherweight() {
		return otherweight;
	}
	public void setOtherweight(double otherweight) {
		this.otherweight = otherweight;
	}
	public String getWeightunit() {
		return weightunit;
	}
	public void setWeightunit(String weightunit) {
		this.weightunit = weightunit;
	}
	public double getLabouruchareges() {
		return labouruchareges;
	}
	public void setLabouruhareges(double labouruchareges) {
		this.labouruchareges = labouruchareges;
	}
	public double getOthercharges() {
		return othercharges;
	}
	public void setOthercharges(double othercharges) {
		this.othercharges = othercharges;
	}
	public double getPurity() {
		return purity;
	}
	public void setPurity(double purity) {
		this.purity = purity;
	}
	public int getHsn() {
		return hsn;
	}
	public void setHsn(int hsn) {
		this.hsn = hsn;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", itemname=" + itemname + ", metal=" + metal + ", metalweight=" + metalweight
				+ ", otherweight=" + otherweight + ", weightunit=" + weightunit + ", labouruchareges=" + labouruchareges
				+ ", othercharges=" + othercharges + ", purity=" + purity + ", hsn=" + hsn + "]";
	}
	
}