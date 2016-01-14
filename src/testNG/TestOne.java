package testNG;

import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TestOne {
	
	@SuppressWarnings("rawtypes")
	private AppiumDriver driver;
	private String udid;
	private String os;
	
  @Test
  public void WillPass() throws MalformedURLException 
  {    
	  try
		{
		  	driver.navigate().to(new URL("http://kainos.com")); 
			if(driver.findElementByCssSelector("#cookies-accept-button-text").getSize().height > 0)
			{
				driver.findElementByCssSelector("#cookies-accept-button-text").click();
			}
			
			for (int i = 0;i<=500; i++)
			{
			    driver.executeScript("window.scrollBy(0,20)", "");
			    Thread.sleep(0,5);
			}
			for (int i = 0;i<=500; i++)
			{
			    driver.executeScript("window.scrollBy(0,-20)", "");
			    Thread.sleep(0,5);
			}
			driver.navigate().to(new URL("http://kainos.com/news"));	
			if(driver.findElementByCssSelector("#cookies-accept-button-text").getSize().height > 0)
			{
				driver.findElementByCssSelector("#cookies-accept-button-text").click();
			}
			
			for (int i = 0;i<=500; i++)
			{
			    driver.executeScript("window.scrollBy(0,20)", "");
			    Thread.sleep(0,5);
			}
			for (int i = 0;i<=500; i++)
			{
			    driver.executeScript("window.scrollBy(0,-20)", "");
			    Thread.sleep(0,5);
			}
			
		}catch(Exception e)
		{
			TakeScreenShotOnFail("WillPass");
			Assert.fail();
		}
	  }
  
  
  
  @Parameters({"udid", "os"})
  @BeforeClass
  public void beforeClass(String udid, String os) throws UnsupportedEncodingException, MalformedURLException, InterruptedException
  {
	 this.udid = udid;
	 this.os = os; 
     System.out.println("Run started for " + udid);
     driver = DriverSetup.getDriver(udid, os);
     if(os.equals("IOS"))
     {
    	 String currenturl = "";
     	 boolean pageHasLoaded = false;
     	 while(!pageHasLoaded)
     	 {
     		 try
     		 {
     			 currenturl = driver.getCurrentUrl();
     		 }
     		 catch(Exception e){}
     		 if(currenturl.equals("http://www.apple.com/"))
     		 {
     			 pageHasLoaded = true; 
     		 }
     	 }
     }
   }

  @AfterClass
  public void afterClass() throws MalformedURLException 
  {
      driver.close();
  }

  public void TakeScreenShotOnFail(String testName)
  {
	  try 
	  {
		  File file  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		  FileUtils.copyFile(file, new File("FAIL | " + udid + " | " + os + " | " + testName + ".jpg"));
	  } 
	  catch (Exception e) 
	  {
		System.out.println(e.getMessage());
	  }	
  }
 
}