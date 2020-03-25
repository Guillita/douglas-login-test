package components.homepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage implements IHomePage {
	 
     String emailInputName = "email";
     String passwordInputName = "password";
     String loginButtonName = "LoginForm|SubmitChanges";
     String wrongCredentialsErrorForm = "LoginForm|Error";
     String wrongCredentialsErrorMessage = "Ihre Eingabedaten sind leider fehlerhaft, stimmen Benutzername und Passwort?";
     String passwordForgottenLinkText = "Passwort vergessen?";

	String homePageUrl = "";
 	
    public HomePage(String url) {
    	this.homePageUrl = url;
    }
	
	public String getHomePageUrl() {
		return this.homePageUrl;
	}
    
    public WebElement findElementByName(WebDriver webDriver, String name) {
    	return  webDriver.findElement(By.name(name));
    }
    
	public String getEmailInputName() {
		return this.emailInputName;
	}

	public String getPasswordInputName() {
		return this.passwordInputName;
	}
	
	public String getPasswordForgottenLink() {
		return this.passwordForgottenLinkText;
	}

    public String getLoginSubmitButtonName() {
		return loginButtonName;
	}

	public String getResetLinkClass() {
		return "html body.rd__view-helper responsive-design main.rd__my-douglas-login.rd__fade-on-load div.rd__container div.rd__my-douglas div.rd__user-credentials-box div.rd__row div.rd__col.rd__col--sm-12.rd__col--md-6 div.rd__user-credentials-box__content-box form div.rd__form-field.rd__form-field--floating-label.rd__form-field--error span.rd__copytext.rd__copytext--30 span.rd__copytext.rd__copytext--30 a.rd__link.rd__link--underline;";
	}

	public WebElement findElementByLinkText(WebDriver webDriver, String link) {
		return  webDriver.findElement(By.linkText(link));
	}
	
	public String getWrongCredentialsErrorFormName() {
		return wrongCredentialsErrorForm;
	}

	public String getWrongCredentialsErrorMessage() {
		return wrongCredentialsErrorMessage;
	}

}
