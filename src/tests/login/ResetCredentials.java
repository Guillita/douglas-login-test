package tests.login;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import User.IUser;
import net.sourceforge.tess4j.*;
import components.CaptchaForm.ICaptchaForm;
import components.HomePage.IHomePage;

public class ResetCredentials {
	 WebDriver webDriver = null;
     
	    // As a user I would like to receive an error message, if I enter wrong credentials.
		@ParameterizedTest		
		public void testLoginResetCredentials(WebDriver webDriver, IHomePage homePage, IUser user, ICaptchaForm captchaForm) throws IOException, TesseractException {

			WebDriverWait wait = new WebDriverWait(webDriver,60);
			wait.until(ExpectedConditions.elementToBeClickable(By.name(homePage.getEmailInputName())));
	        WebElement email = homePage.findElementByName(webDriver, homePage.getEmailInputName());
	        email.click();
	        email.sendKeys(user.getEmail());
			
	        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(homePage.getPasswordForgottenLink())));
	        webDriver.findElement(By.linkText(homePage.getPasswordForgottenLink())).click();
	        
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(captchaForm.getCaptchaImageLocator())));
   
	        sendCaptchaTextKeys(webDriver, captchaForm);
	        
	        WebElement emailSendButton = webDriver.findElement(By.name(captchaForm.getCaptchaSubmitButtonName()));
	        emailSendButton.submit();
	        
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/responsive-design/main/div/div[3]/form/div/div/div[2]/div/div/span/strong")));
	        String bodyText = webDriver.findElement(By.xpath("/html/body/responsive-design/main/div/div[3]/form/div/div/div[2]/div/div/span/strong")).getText();
	        Assert.assertTrue("Text not found!", bodyText.contains(user.getEmail()));
	                
	        webDriver.quit();
		}
		
		private String sendCaptchaTextKeys(WebDriver webDriver, ICaptchaForm captchaForm) throws TesseractException, IOException {
			boolean keepLookingForKeys = true;
			WebElement bidlCode = null;
			WebElement newCaptcha = null;
			String captchaText = "";
			int retry = 0;
	        File src = null;
	        String path = captchaForm.getCaptchaScreenshotPath();
	        ITesseract image = new Tesseract(); 
			while (keepLookingForKeys && retry < 100) {
				src = webDriver.findElement(By.xpath(captchaForm.getCaptchaImageLocator())).getScreenshotAs(OutputType.FILE);
	        	FileHandler.copy(src, new File(path));
				captchaText = image.doOCR(new File(path));
		        bidlCode = captchaForm.findElementByName(webDriver, captchaForm.getCaptchaName());
		        bidlCode.click();
		        bidlCode.sendKeys(captchaText);
		        keepLookingForKeys = webDriver.findElements(By.xpath(captchaForm.getCaptchaSubmitButtonDisabledLocator())).size() > 0;
		        // If tesseract cannot recognize the code we will try again clicking in New Captcha link and tryu¡ing again
		        if (keepLookingForKeys) {
		        	bidlCode.clear();
		        	newCaptcha = webDriver.findElement(By.xpath(captchaForm.getNewCaptchaLink()));
		        	newCaptcha.click();      			        	
		        }
		        retry++;
			}
			return captchaText;
		}

}
