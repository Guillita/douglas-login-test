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
	IUserPage userPage = new UserPage();
	String loginURL = "https://www.douglas.de/mydouglas/login";
	IHomePage homePage = new HomePage(loginURL);
	
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
	    new WrongCredentials().testLoginWrongCredentials(new Browsers("Chrome", loginURL).getWebDriver(), getHomePage(), getUser());  
		try {
			ICaptchaForm captchaForm = new CaptchaForm();
			new ResetCredentials().testLoginResetCredentials(new Browsers("Chrome", loginURL).getWebDriver(), getHomePage(), getUser(), captchaForm);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TesseractException e)	{
			e.printStackTrace();
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
