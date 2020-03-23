package User;

public class User implements IUser {
	
	String email = "";
	String password = "";
	String wrongPassword = "WrongCredentials";
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getWrongPassword() {
		return wrongPassword;
	}
	
}
