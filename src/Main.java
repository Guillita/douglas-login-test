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

public class Main {
	
	public static void main(String[] args) {
        IUser user = new User();
        IUserPage userPage = new UserPage();
        user.setEmail(args[0]);
        user.setPassword(args[1]);
		String loginURL = "https://www.douglas.de/mydouglas/login";
		IHomePage homePage = new HomePage(loginURL);  
        new Success().testLoginSuccess(new Browsers("Chrome", loginURL).getWebDriver(), homePage, user, userPage);       
        new WrongCredentials().testLoginWrongCredentials(new Browsers("Chrome", loginURL).getWebDriver(), homePage, user);  
		try {
			ICaptchaForm captchaForm = new CaptchaForm();
			new ResetCredentials().testLoginResetCredentials(new Browsers("Chrome", loginURL).getWebDriver(), homePage, user, captchaForm);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TesseractException e)	{
			e.printStackTrace();
		} 
	}

}
