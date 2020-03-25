package testssuites;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import components.homepage.HomePage;
import components.homepage.IHomePage;
import components.userpage.IUserPage;
import components.userpage.UserPage;
import driversconfiguration.Browsers;
import net.sourceforge.tess4j.TesseractException;
import tests.login.ResetCredentials;
import tests.login.Success;
import tests.login.wrongcredentials.WrongEmail;
import tests.login.wrongcredentials.WrongPassword;
import user.IUser;

/**
 * We would like to test in this suite the login functionality according to the following requirements:
 *	- As an user with correct credentials, I would like to be able to login to the webshop
 *	- As a user I would like to receive an error message, if I enter wrong credentials.
 *	- As a user, I would like to be able to reset my password if I forget my credentials.
 */
public class TestSuiteLogin {
	
	private static Logger logger = Logger.getLogger("testssuites.TestSuiteLogin");
	
	IUser user = null;
	IUserPage userPage = null;
	String loginURL = "";
	IHomePage homePage = null;
	
	public TestSuiteLogin() {
		init();
	}
	
	private void init() {
		setUserPage(new UserPage());
		setHomePage(new HomePage(getLoginURL()));  
	}
	
	public void testLogin() {
	    new Success().testLoginSuccess(new Browsers("Chrome", getLoginURL()).getWebDriver(), getHomePage(), getUser(), getUserPage());      
	    new WrongEmail().testLoginWrongEmail(new Browsers("Chrome", getLoginURL()).getWebDriver(), getHomePage(), getUser());  
	    new WrongPassword().testLoginWrongPassword(new Browsers("Chrome", getLoginURL()).getWebDriver(), getHomePage(), getUser());  
		try {
			Browsers browser = new Browsers("Chrome", getLoginURL());
			new ResetCredentials().testLoginResetCredentials(browser.getWebDriver(), getHomePage(), getUser());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error ceating captcha screenshot file.");
		} catch (TesseractException e)	{
			logger.log(Level.SEVERE, "Error generating captcha text.");
		} 
	}
	
	public IUser getUser() {
		return user;
	}

	public void setUser(IUser user) {
		this.user = user;
	}

	public IUserPage getUserPage() {
		return userPage;
	}

	public void setUserPage(IUserPage userPage) {
		this.userPage = userPage;
	}

	public String getLoginURL() {
		return loginURL;
	}

	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}

	public IHomePage getHomePage() {
		return homePage;
	}

	public void setHomePage(IHomePage homePage) {
		this.homePage = homePage;
	}

}
