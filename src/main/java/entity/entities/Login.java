package entity.entities;


public class Login {
	int id;
	String userName;
	String password;
	String person;
	
	public Login() {
		super();
	}

	public Login(String userName, String password, String person) {
		super();
		this.userName = userName;
		this.password = password;
		this.person = person;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPerson() {
		return person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	@Override
	public String toString() {
		return "Login [id=" + id + ", userName=" + userName + ", password=" + password + ", person=" + person + "]";
	}
	
		
}
