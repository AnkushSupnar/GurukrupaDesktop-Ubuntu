package hibernate.entities;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="modetransaction")
public class ModeTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	String metal;
	double weight;
	double ghat;
	double netweight;
	double rate;
	@ManyToOne
	@JoinColumn(name="modeid",nullable = false)
	Mode mode;
	public ModeTransaction() {
		super();
	}
	public ModeTransaction(String metal, double weight, double ghat, double netweight, double rate, Mode mode) {
		super();
		this.metal = metal;
		this.weight = weight;
		this.ghat = ghat;
		this.netweight = netweight;
		this.rate = rate;
		this.mode = mode;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMetal() {
		return metal;
	}
	public void setMetal(String metal) {
		this.metal = metal;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getGhat() {
		return ghat;
	}
	public void setGhat(double ghat) {
		this.ghat = ghat;
	}
	public double getNetweight() {
		return netweight;
	}
	public void setNetweight(double netweight) {
		this.netweight = netweight;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public Mode getMode() {
		return mode;
	}
	public void setMode(Mode mode) {
		this.mode = mode;
	}
	@Override
	public String toString() {
		return "ModeTransaction [id=" + id + ", metal=" + metal + ", weight=" + weight + ", ghat=" + ghat
				+ ", netweight=" + netweight + ", rate=" + rate + ", mode=" + mode + "]";
	}
	
}