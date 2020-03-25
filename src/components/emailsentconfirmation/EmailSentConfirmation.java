package components.emailsentconfirmation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EmailSentConfirmation implements IEmailSentConfirmation {
	
	String emailXpath = "/html/body/responsive-design/main/div/div[3]/form/div/div/div[2]/div/div/span/strong";
	
	public String getEmailXpath() {
		return emailXpath;
	}
	
    public WebElement findElementByXpath(WebDriver webDriver, String xpath) {
    	return  webDriver.findElement(By.xpath(xpath));
    }
}
