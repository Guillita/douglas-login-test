package driversConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverConfigurated {
	
	WebDriver driver = null;
	
	public WebDriver getDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Guillita\\eclipse-workspace\\qa-recruitment-test\\douglas-login-test\\drivers\\chromedriver.exe");   
		// Initialize browser
		WebDriver driver = new ChromeDriver(inizializeBrowser());
		return driver;
	}
	
	private ChromeOptions inizializeBrowser() {
		ChromeOptions options = new ChromeOptions();
	    options.addArguments("--disable-extensions"); // disabling extensions
	    options.addArguments("--disable-gpu"); // applicable to windows os only
	    options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
	    options.addArguments("--no-sandbox");
	    options.addArguments("--remote-debugging-port=9225");
		return options;
	}

}
