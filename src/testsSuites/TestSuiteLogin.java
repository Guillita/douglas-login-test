package testsSuites;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

import User.IUser;
import components.HomePage.HomePage;
import components.HomePage.IHomePage;
import components.UserPage.IUserPage;
import components.UserPage.UserPage;
import driversConfiguration.Browsers;
import net.sourceforge.tess4j.TesseractException;
import tests.login.ResetCredentials;
import tests.login.Success;
import tests.login.wrongcredentials.WrongEmail;
import tests.login.wrongcredentials.WrongPassword;

/**
 * We would like to test in this suite the login functionality according to the following requirements:
 *	- As an user with correct credentials, I would like to be able to login to the webshop
 *	- As a user I would like to receive an error message, if I enter wrong credentials.
 *	- As a user, I would like to be able to reset my password if I forget my credentials.
 */
public class TestSuiteLogin {
	
	private static Logger logger = Logger.getLogger("testsSuites.TestSuiteLogin");
	
	IUser user = null;
	IUserPage userPage = null;
	String loginURL = "";
	IHomePage homePage = null;
	
	public TestSuiteLogin(IUser user) {
		this.user = user;
		init();
	}
	
	private void init() {
		setUserPage(new UserPage());
		setLoginURL("https://www.douglas.de/mydouglas/login"); 
		setHomePage(new HomePage(loginURL));  
	}
	
	public void testLogin() {
	    new Success().testLoginSuccess(new Browsers("Chrome", getLoginURL()).getWebDriver(), getHomePage(), getUser(), getUserPage());      
	    new WrongEmail().testLoginWrongEmail(new Browsers("Chrome", getLoginURL()).getWebDriver(), getHomePage(), getUser());  
	    new WrongPassword().testLoginWrongPassword(new Browsers("Chrome", getLoginURL()).getWebDriver(), getHomePage(), getUser());  
		try {
			new ResetCredentials().testLoginResetCredentials(new Browsers("Chrome", getLoginURL()).getWebDriver(), getHomePage(), getUser());
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Error ceating captcha screenshot file.");
		} catch (TesseractException e)	{
			logger.log(Level.SEVERE, "Error generating captcha text.");
		} 
	}
	
	protected IUser getUser() {
		return user;
	}

	protected void setUser(IUserPage user) {
		this.userPage = user;
	}

	protected IUserPage getUserPage() {
		return userPage;
	}

	protected void setUserPage(IUserPage userPage) {
		this.userPage = userPage;
	}

	protected String getLoginURL() {
		return loginURL;
	}

	protected void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}

	protected IHomePage getHomePage() {
		return homePage;
	}

	protected void setHomePage(IHomePage homePage) {
		this.homePage = homePage;
	}

}
