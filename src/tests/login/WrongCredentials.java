package tests.login;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import User.IUser;
import components.HomePage.IHomePage;

public class WrongCredentials {
	
    WebDriver webDriver = null;
      
    // As a user I would like to receive an error message, if I enter wrong credentials.
	@ParameterizedTest
	public void testLoginWrongCredentials(WebDriver webDriver, IHomePage homePage, IUser user) {
        WebDriverWait wait = new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.name(homePage.getEmailInputName())));
        WebElement email = homePage.findElementByName(webDriver, homePage.getEmailInputName());
		email.click();
        email.sendKeys(user.getEmail());
        
        WebElement password = webDriver.findElement(By.name(homePage.getPasswordInputName()));
		password.click();
        password.sendKeys(user.getWrongPassword());
        WebElement loginButton = webDriver.findElement(By.name(homePage.getLoginSubmitButtonName()));
        loginButton.submit();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(homePage.getWrongCredentialsErrorFormName())));
        String bodyText = webDriver.findElement(By.name(homePage.getWrongCredentialsErrorFormName())).getText();
        
        Assert.assertTrue("Text not found!", bodyText.contains(homePage.getWrongCredentialsErrorMessage()));
                
        webDriver.quit();
	}
	


}
