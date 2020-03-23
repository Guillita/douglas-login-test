package components.CaptchaForm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ICaptchaForm {
	
	public WebElement findElementByName(WebDriver webDriver, String emailInputName);

	public String getEmailInputName();
	
	public String getCaptchaName();
	
	public String getCaptchaImageLocator();
	
	public String getCaptchaScreenshotPath();
	
	public String getCaptchaSubmitButtonName();
	
	public String getCaptchaSubmitButtonDisabledLocator();
	
	public String getNewCaptchaLink();

}
