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
import components.UserPage.IUserPage;

public class Success {
	
    WebDriver webDriver = null;
  
	// As an user with correct credentials I would like to be able to login to the webshop    
	@ParameterizedTest
	public void testLoginSuccess(WebDriver webDriver, IHomePage homePage, IUser user, IUserPage userPage) {
        WebDriverWait wait = new WebDriverWait(webDriver,30);
        wait.until(ExpectedConditions.elementToBeClickable(By.name(homePage.getEmailInputName())));
        WebElement email = homePage.findElementByName(webDriver, homePage.getEmailInputName());
		email.click();
        email.sendKeys(user.getEmail());
        
        WebElement password = (webDriver).findElement(By.name((homePage.getPasswordInputName())));
		password.click();
        password.sendKeys(user.getPassword());
        
        WebElement loginButton = (webDriver).findElement(By.name(homePage.getLoginSubmitButtonName()));
        loginButton.submit();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userPage.getClientNumberMessageLocator())));
        String bodyText = webDriver.findElement(By.tagName("body")).getText();
        
        Assert.assertTrue("Text not found!", bodyText.contains(userPage.getClientNumberMessage()));
                
        webDriver.quit();
	}
	


}
