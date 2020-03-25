package driversconfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browsers
{
    private WebDriver webDriver;
	private String browserName;
	private String baseURL;

	public Browsers(String browserName, String baseURL) {
    	this.browserName = browserName;
    	this.baseURL = baseURL;
    	init();
    }
    
    public String getBrowserName() {
		return this.browserName;
	}

	public String getBaseURL() {
		return this.baseURL;
	}

	public void init() {
         switch (getBrowserName()) {
             case "Chrome":
            	 ChromeDriverConfigurated configuratedBrowser = new ChromeDriverConfigurated();
                 setWebDriver(configuratedBrowser.getDriver());
              break;
             case "IE":
                 setWebDriver(new InternetExplorerDriver());
             break;
             case "Firefox":
                 setWebDriver(new FirefoxDriver());
             break;
         }
         getWebDriver().manage().window().maximize();
         navigateToURL(getBaseURL());
    }
    
    public WebDriver getWebDriver() {
         return this.webDriver;
    }
    
    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
    
    public void navigateToURL(String url) {
    	getWebDriver().get(url);
    	getWebDriver().manage().window().maximize();
    }
    
}