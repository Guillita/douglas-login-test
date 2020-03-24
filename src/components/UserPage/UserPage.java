package components.userpage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserPage implements IUserPage {
	
	private static final String clientNumberMessage = "Ihre Kundennummer bei Rückfragen:";
	private static final String clientNumberMessageLocator = "//span[contains(text(),'Ihre Kundennummer bei Rückfragen:')]";

	public String getClientNumberTextLocator() {
		return clientNumberMessageLocator;
	}
	
	public String getClientNumberText() {
		return clientNumberMessage;
	}

	public String findTextByTag(WebDriver webDriver, String tag) {
		return webDriver.findElement(By.tagName(tag)).getText();
	}

}
