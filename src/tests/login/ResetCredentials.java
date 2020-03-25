package tests.login;

import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import net.sourceforge.tess4j.*;
import user.IUser;
import components.emailsentconfirmation.EmailSentConfirmation;
import components.emailsentconfirmation.IEmailSentConfirmation;
import components.homepage.IHomePage;
import components.passwordforgotten.PasswordForgotten;
import components.passwordforgotten.IPasswordForgotten;

/* This class test the following requirement:
 * 
 *  - As a user I would like to receive an error message, if I enter wrong credentials.
 */
public class ResetCredentials {
	WebDriver webDriver = null;
	private static Logger logger = Logger.getLogger("tests.login.ResetCredentials");
     
	@ParameterizedTest		
	public void testLoginResetCredentials(WebDriver webDriver, IHomePage homePage, IUser user) throws IOException, TesseractException {

		WebDriverWait wait = new WebDriverWait(webDriver,30);
		// Set user email
		wait.until(ExpectedConditions.elementToBeClickable(By.name(homePage.getEmailInputName())));
		setUserEmailInHomePage(webDriver, homePage, user);		
		// Click on Password forgotten link
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText(homePage.getPasswordForgottenLink())));
        homePage.findElementByLinkText(webDriver, homePage.getPasswordForgottenLink()).click();
        // Open Captcha Form
        IPasswordForgotten passwordForgottenPage = new PasswordForgotten();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(passwordForgottenPage.getCaptchaImageLocator())));
        sendCaptchaTextKeys(webDriver, passwordForgottenPage);
        clickSendEmailButtonFromCaptchaForm(webDriver, passwordForgottenPage);
        // Open Email sent confirmation window
        IEmailSentConfirmation emailSentConfirmation = new EmailSentConfirmation();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(emailSentConfirmation.getEmailXpath())));
        // Check the email in the confirmation window
        checkUserEmailPresenceInConfirmationWindow(webDriver, user, emailSentConfirmation);       
        webDriver.quit();
	}
	
	public void clickSendEmailButtonFromCaptchaForm(WebDriver webDriver, IPasswordForgotten passwordForgottenPage) {
        WebElement emailSendButton = webDriver.findElement(By.name(passwordForgottenPage.getCaptchaSubmitButtonName()));
        emailSendButton.submit();
	}
	
	public void setUserEmailInHomePage(WebDriver webDriver, IHomePage homePage, IUser user) {
        WebElement email = homePage.findElementByName(webDriver, homePage.getEmailInputName());
		email.click();
        email.sendKeys(user.getEmail());
	} 
	
	private String sendCaptchaTextKeys(WebDriver webDriver, IPasswordForgotten passwordForgottenPage) throws TesseractException, IOException, NoSuchElementException {
		boolean keepLookingForNewCode = true;
		WebElement bidlCode = null;
		WebElement newCaptcha = null;
		String captchaText = "";
		int retry = 0;
        File src = null;
        String path = passwordForgottenPage.getCaptchaScreenshotPath();
        ITesseract image = new Tesseract(); 
        bidlCode = passwordForgottenPage.findElementByName(webDriver, passwordForgottenPage.getCaptchaName());
        bidlCode.click();
        // Sometimes the captcha was not being correctly recognized by the tesseract library so I have
        // performed a retry that consistently, with a small number of retries, recognizes the right captcha text
		while (keepLookingForNewCode && retry < 15) {
		  try {	
			src = webDriver.findElement(By.xpath(passwordForgottenPage.getCaptchaImageLocator())).getScreenshotAs(OutputType.FILE);
        	FileHandler.copy(src, new File(path));
			captchaText = image.doOCR(new File(path));
	        bidlCode.sendKeys(captchaText);
	        keepLookingForNewCode = getNewCaptcha(webDriver, passwordForgottenPage);
	        // If tesseract cannot recognize the code we will try again clicking in New Captcha link and trying again
	        if (keepLookingForNewCode) {
	        	bidlCode.clear();
	        	newCaptcha = webDriver.findElement(By.xpath(passwordForgottenPage.getNewCaptchaLink()));
	        	newCaptcha.click();      			        	
	        }
		  } catch (Exception e) {
			  // The variable bidlCode was throwing different exceptions after the element was found by findElementByName and clicked, which makes no sense.  
			  // I prevented that exceptions to stop the test at this point, that is a retry point of the code in which I generate new captchas and 
			  // have nothing to do with the real functionality that I am testing, for the main elements of the functionality exceptions will not be never catched
			  // without a very very good reason
			  logger.log(Level.SEVERE, "RETRY CAPTCHA TEXT GENERATOR: Element Not Found");
		  } 
		  retry++;
		}
		return captchaText;
	}
	
	public void checkUserEmailPresenceInConfirmationWindow(WebDriver webDriver, IUser user, IEmailSentConfirmation emailSentConfirmation) {
		String bodyText = emailSentConfirmation.findElementByXpath(webDriver, emailSentConfirmation.getEmailXpath()).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(user.getEmail()));
	}
	
    private boolean getNewCaptcha(WebDriver webDriver, IPasswordForgotten passwordForgottenPage) {
    	boolean keepLookingForKeys = false;
    	boolean keepLookingForNewCaptcha = false;
    	WebElement errorMessage = passwordForgottenPage.findElementByXpath(webDriver, passwordForgottenPage.getErrorFloatingLabelXpath());
    	if (errorMessage != null) keepLookingForKeys = true; 
    	keepLookingForNewCaptcha = webDriver.findElements(By.xpath(passwordForgottenPage.getCaptchaSubmitButtonDisabledLocator())).size() > 0;
    	
    	return keepLookingForNewCaptcha || keepLookingForKeys;
    }
        
		
}
