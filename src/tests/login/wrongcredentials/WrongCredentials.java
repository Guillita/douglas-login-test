package tests.login.wrongcredentials;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import components.HomePage.IHomePage;

/* This class test the following requirement:
 * 
 *  - As a user I would like to receive an error message, if I enter wrong credentials.
 */
public class WrongCredentials {

    public void clickOnLoginSubmitButtonFromHomePage(WebDriver webDriver, IHomePage homePage) {
    	WebElement loginButton = homePage.findElementByName(webDriver, homePage.getLoginSubmitButtonName());
        loginButton.submit();
    }
    
    public void checkWrongCredentialsErrorMessagePresence(WebDriver webDriver, IHomePage homePage) {
        String bodyText = homePage.findElementByName(webDriver, homePage.getWrongCredentialsErrorFormName()).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(homePage.getWrongCredentialsErrorMessage()));
    }

}
