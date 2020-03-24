package testsSuites;

import java.io.IOException;

import User.IUser;
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

public class TestSuiteLogin {
	
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
	
	/**
	 * We would like to test the login functionality with the following requirements:
	 *	- As an user with correct credentials I would like to be able to login to the webshop
	 *	- As a user I would like to receive an error message, if I enter wrong credentials.
	 *	- As a user, I would like to be able to reset my password if I forget my credentials.
	 *	
	 * @throws IOException
	 * @throws TesseractException
	 */
	public void testLogin() throws IOException, TesseractException {
	    new Success().testLoginSuccess(new Browsers("Chrome", getLoginURL()).getWebDriver(), getHomePage(), getUser(), getUserPage());       
	    new WrongCredentials().testLoginWrongCredentials(new Browsers("Chrome", getLoginURL()).getWebDriver(), getHomePage(), getUser());  
		try {
			ICaptchaForm captchaForm = new CaptchaForm();
			new ResetCredentials().testLoginResetCredentials(new Browsers("Chrome", getLoginURL()).getWebDriver(), getHomePage(), getUser(), captchaForm);
		} catch (IOException e) {
			throw new IOException("Error creating captcha screenshot file.", e);
		} catch (TesseractException e)	{
			throw new TesseractException("Error generating captcha text.", e);
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
