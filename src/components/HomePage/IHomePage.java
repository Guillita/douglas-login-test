package components.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IHomePage {
	
	public String getHomePageUrl();
	    
    public WebElement findElementByName(WebDriver webDriver, String name);
    
    public WebElement findElementByCssSelector(WebDriver webdriver, String cssSelector);
    
	public String getEmailInputName();

	public String getPasswordInputName();
	
	public String getPasswordForgottenLink();
	
	public String getResetLinkClass();
	
	public String getLoginSubmitButtonName();
	
	public String getWrongCredentialsErrorFormName();
	
	public String getWrongCredentialsErrorMessage();
}