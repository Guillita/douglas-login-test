package components.emailsentconfirmation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface IEmailSentConfirmation {
	public String getEmailXpath();
	public WebElement findElementByXpath(WebDriver webDriver, String xpath);
}
