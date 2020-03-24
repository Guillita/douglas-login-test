package tests.login.wrongcredentials;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import User.IUser;
import components.HomePage.IHomePage;

/* This class test the following requirement:
 * 
 *  - As a user I would like to receive an error message, if I enter wrong credentials.
 */
public class WrongEmail extends WrongCredentials {
	
    WebDriver webDriver = null;
      
	@ParameterizedTest
	public void testLoginWrongEmail(WebDriver webDriver, IHomePage homePage, IUser user) {
        WebDriverWait wait = new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.name(homePage.getEmailInputName())));
        
        setUserWrongEmailInHomePage(webDriver, homePage, user);
        setUserPasswordInHomePage(webDriver, homePage, user);
        clickOnLoginSubmitButtonFromHomePage(webDriver, homePage);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(homePage.getWrongCredentialsErrorFormName())));
        checkWrongCredentialsErrorMessagePresence(webDriver, homePage);      
        
        
        webDriver.quit();
	}
	
	public void setUserWrongEmailInHomePage(WebDriver webDriver, IHomePage homePage, IUser user) {
        WebElement email = homePage.findElementByName(webDriver, homePage.getEmailInputName());
		email.click();
        email.sendKeys(user.getWrongEmail());
	}
	
	public void setUserPasswordInHomePage(WebDriver webDriver, IHomePage homePage, IUser user) {
        WebElement password = homePage.findElementByName(webDriver, homePage.getPasswordInputName());
		password.click();
        password.sendKeys(user.getWrongPassword());
	}

}
