import testsSuites.TestSuiteLogin;
import user.IUser;
import user.User;

public class Main {
	
	public static void main(String[] args) {
        IUser user = new User();
        user.setEmail(args[0]);
        user.setPassword(args[1]);
        String loginURL = "https://www.douglas.de/mydouglas/login";
        
        TestSuiteLogin testSuiteLogin = new TestSuiteLogin();
        testSuiteLogin.setUser(user);
        testSuiteLogin.setLoginURL(loginURL);
        testSuiteLogin.testLogin();
        
	}

}
