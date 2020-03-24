import User.IUser;
import User.User;
import testsSuites.TestSuiteLogin;

public class Main {
	
	public static void main(String[] args) {
        IUser user = new User();
        user.setEmail(args[0]);
        user.setPassword(args[1]);
        
        new TestSuiteLogin(user).testLogin();
        
	}

}
