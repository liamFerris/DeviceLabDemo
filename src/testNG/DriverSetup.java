package testNG;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class DriverSetup {
	
	@SuppressWarnings({ "rawtypes" })
	public static AppiumDriver getDriver(String udid, String os) throws MalformedURLException{
		
		URL SELENIUM_HUB_URL = new URL("http://localhost:4444/wd/hub");
		AppiumDriver driver = null;
		DesiredCapabilities capabilities = new DesiredCapabilities();	
  		capabilities.setCapability("deviceName", udid);
  		capabilities.setCapability("udid", udid);	
		if (os.equals("ANDROID"))
		{   
	  		capabilities.setPlatform(Platform.ANDROID);
	  		capabilities.setCapability("browserName", "Chrome");	
	        driver = new AndroidDriver(SELENIUM_HUB_URL,capabilities);
		}
		else if (os.equals("IOS"))
		{   	
	  		capabilities.setPlatform(Platform.MAC);
	  		capabilities.setCapability("platformName", "iOS");
	  		capabilities.setCapability("browserName", "safari");
	  		capabilities.setCapability("newCommandTimeout", "12000");
	        capabilities.setCapability("launchTimeout", "12000");
	        driver = new IOSDriver(SELENIUM_HUB_URL, capabilities);
		}
        driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
        return driver;        
	}
}

