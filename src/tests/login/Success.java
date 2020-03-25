package tests.login;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import components.homepage.IHomePage;
import components.userpage.IUserPage;
import user.IUser;

/* This class test the following requirement:
 * 
 *  - As an user with correct credentials I would like to be able to login to the webshop
 */
public class Success {
	
    WebDriver webDriver = null;
  
	@ParameterizedTest
	public void testLoginSuccess(WebDriver webDriver, IHomePage homePage, IUser user, IUserPage userPage) {
        WebDriverWait wait = new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.name(homePage.getEmailInputName())));
        
        setUserEmailInHomePage(webDriver, homePage, user);
        setUserPasswordInHomePage(webDriver, homePage, user);
        clickOnLoginSubmitButtonFromHomePage(webDriver, homePage);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userPage.getClientNumberTextLocator())));
        checkClientNumberTextPresence(webDriver, userPage);
                
        webDriver.quit();
	}
	
	public void setUserEmailInHomePage(WebDriver webDriver, IHomePage homePage, IUser user) {
        WebElement email = homePage.findElementByName(webDriver, homePage.getEmailInputName());
		email.click();
        email.sendKeys(user.getEmail());
	}
	
	public void setUserPasswordInHomePage(WebDriver webDriver, IHomePage homePage, IUser user) {
        WebElement password = homePage.findElementByName(webDriver, homePage.getPasswordInputName());
		password.click();
        password.sendKeys(user.getPassword());
	}

    public void clickOnLoginSubmitButtonFromHomePage(WebDriver webDriver, IHomePage homePage) {
    	WebElement loginButton = homePage.findElementByName(webDriver, homePage.getLoginSubmitButtonName());
        loginButton.submit();
    }
    
    public void checkClientNumberTextPresence(WebDriver webDriver, IUserPage userPage) {
    	String bodyText = userPage.findTextByTag(webDriver, "body");
        Assert.assertTrue("Text not found!", bodyText.contains(userPage.getClientNumberText()));
    }


}
