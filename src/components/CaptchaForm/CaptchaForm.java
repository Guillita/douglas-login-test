package components.CaptchaForm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CaptchaForm implements ICaptchaForm {

	String emailInputName = "email";
	String captchaName = "captcha";
	String captchaImageLocator = "//img[contains(@src,'cart/captcha')]";
	String captchaScreenshotPath = System.getProperty("user.dir") + "/screeshots/captcha.png";
	String captchaSubmitButtonName = "LoginForm|SubmitChanges";
	String captchaSubmitButtonDisabledLocator = "//button[@class='rd__button rd__button--primary rd__button--xl rd__button--disabled']";
    String newCaptchaLink = "//a[contains(@data-wt-component,'New Captcha')]";

	public WebElement findElementByName(WebDriver webDriver, String name) {
    	return  webDriver.findElement(By.name(name));
    }
    
	public String getEmailInputName() {
		return this.emailInputName;
	}
	
	public String getCaptchaName() {
		return this.captchaName;
	}
	
	public String getCaptchaImageLocator() {
		return this.captchaImageLocator;
	}
	
	public String getCaptchaScreenshotPath() {
		return this.captchaScreenshotPath;
	} 
	
	public String getCaptchaSubmitButtonName() {
		return this.captchaSubmitButtonName;
	}

	public String getCaptchaSubmitButtonDisabledLocator() {
		return this.captchaSubmitButtonDisabledLocator;
	}

	public String getNewCaptchaLink() {
		return this.newCaptchaLink;
	}
}
