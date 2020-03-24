import java.io.IOException;

import User.IUser;
import User.User;
import components.CaptchaForm.CaptchaForm;
import components.CaptchaForm.ICaptchaForm;
import components.HomePage.HomePage;
import components.HomePage.IHomePage;
import components.UserPage.IUserPage;
import components.UserPage.UserPage;
import driversConfiguration.Browsers;
import net.sourceforge.tess4j.TesseractException;
import tests.login.ResetCredentials;
import tests.login.Success;
import tests.login.WrongCredentials;
import testsSuites.TestSuiteLogin;

public class Main {
	
	public static void main(String[] args) {
        IUser user = new User();
        user.setEmail(args[0]);
        user.setPassword(args[1]);
        
        new TestSuiteLogin(user).testLogin();
        
	}

}
