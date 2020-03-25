package components.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IHomePage {
	
	public String getHomePageUrl();
	    
    public WebElement findElementByName(WebDriver webDriver, String name);
    
    public WebElement findElementByLinkText(WebDriver webdriver, String link);
    
	public String getEmailInputName();

	public String getPasswordInputName();
	
	public String getPasswordForgottenLink();
		
	public String getLoginSubmitButtonName();
	
	public String getWrongCredentialsErrorFormName();
	
	public String getWrongCredentialsErrorMessage();
}