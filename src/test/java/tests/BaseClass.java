package tests;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass {
	
	AndroidDriver<AndroidElement> driver;
	
	//@BeforeTest
	public void setup() {
		
		try {
			DesiredCapabilities dc = new DesiredCapabilities();

	        dc.setCapability(MobileCapabilityType.DEVICE_NAME,  "2a7d6ac8563f7ece"); //2a7d6ac8563f7ece
	        dc.setCapability("platformName", "android");
	        dc.setCapability("appPackage", "com.nightstandreact");
	        dc.setCapability("appActivity", ".MainActivity");

	       driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		}
		catch(Exception e) {
			
		}
		
	}
	
	 
	
	public boolean takeScreenshot(final String name) {
		   String screenshotDirectory = System.getProperty("appium.screenshots.dir", System.getProperty("java.io.tmpdir", ""));
		   File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		   return screenshot.renameTo(new File(screenshotDirectory, String.format("%s.png", name)));
		}
	
	
	
	@Test
	public void interactWithCameraInAndroid() throws MalformedURLException {
		
		DesiredCapabilities dc = new DesiredCapabilities();

		
		/*
		 * dc.setCapability(MobileCapabilityType.DEVICE_NAME, "2a7d6ac8563f7ece");
		 * //2a7d6ac8563f7ece dc.setCapability("platformName", "android");
		 * dc.setCapability("appPackage", "com.nightstandreact");
		 * dc.setCapability("appActivity", ".MainActivity");
		 */
		 

       driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
       //problem
       driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //MobileElement el1 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[3]/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.Button[9]/android.widget.TextView");
        
       //el1.click();
       driver.findElementByAndroidUIAutomator("new UiSelector().text(\"SHOW CAMERA\")").click();
       //driver.findElementByName("SHOW CAMERA").click();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        MobileElement el2 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        el2.click();

        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        MobileElement el3 = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        el3.click();
        
      //problem
        //driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        //MobileElement el4 = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout[3]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.view.ViewGroup/android.widget.ImageButton");
        //el4.click();
        //driver.navigate().back();
        
        takeScreenshot("interactWithCamera");
    }
	
	
	@Test
	 public void testModalAndModeless() throws MalformedURLException, InterruptedException {
		 DesiredCapabilities dc = new DesiredCapabilities();
		 driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		 
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 driver.findElementByAndroidUIAutomator("new UiSelector().text(\"MODAL/MODELESS\")").click();
		 
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	 
		 driver.findElementByAndroidUIAutomator("new UiSelector().text(\"THROW ALERT\")").click();
		 
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);	 
		 MobileElement el3 = (MobileElement) driver.findElementById("android:id/button1");
		 el3.click();
		 TimeUnit.SECONDS.sleep(5);
		 takeScreenshot("testModalAndModeless");
		 
		 
	 }
	
	 
	@Test
	 public void testPerformance() throws MalformedURLException, InterruptedException {
		 DesiredCapabilities dc = new DesiredCapabilities();
		 driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);
		 
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 driver.findElementByAndroidUIAutomator("new UiSelector().text(\"PERFORMANCE\")").click();
		 
		 driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		 MobileElement element = driver.findElement(MobileBy.AndroidUIAutomator(
                 "new UiScrollable(new UiSelector().resourceId(\"android:id/content\")).getChildByText("
                         + "new UiSelector().className(\"android.widget.TextView\"), \"RUN TEST\")"));
		 driver.findElementByAndroidUIAutomator("new UiSelector().text(\"RUN TEST\")").click();
		 
		 TimeUnit.SECONDS.sleep(5);
		 takeScreenshot("testPerformance");
	 }
	

	
	//@AfterTest
	public void teardown() {
		driver.close();
		driver.quit();
		
	}
	
	

}
