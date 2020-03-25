package user;

public class User implements IUser {
	
	String email = "";
	String password = "";
	String wrongEmail = "wrongEmail@gmail.com";
	String wrongPassword = "WrongCredentials";
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getWrongEmail() {
		return wrongEmail;
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
