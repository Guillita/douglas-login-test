package components.userpage;

import org.openqa.selenium.WebDriver;

public interface IUserPage {
	public String getClientNumberTextLocator();
	public String getClientNumberText();
    public String findTextByTag(WebDriver webDriver, String tag);
}
