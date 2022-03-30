package demo.toolsqa.alertsframesandwindows;

import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserWindowsDemo {

	WebDriver driver;
	String title = "Browser Windows";

	@BeforeClass
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/browser-windows");
	}

	@Test(description = "verify browser windows Demo work successfully.")
	public void BrowserWindows() throws Exception {

		By tabLabel = By.className("main-header");
		By newTAbButton = By.cssSelector("button[id='tabButton']");
		By newWindowButton = By.id("windowButton");
		By newWIndowMessageButton = By.cssSelector("button#messageWindowButton");
		By titleText = By.xpath("//h1[contains(.,'This is a sample page')]");
		By newWindowsMessageText = By.xpath("/html/body");

		String currentTabTitle = driver.findElement(tabLabel).getText();
		Assert.assertEquals(currentTabTitle, title);
		Reporter.log("Open browser windows tab succesfully..");

		Assert.assertTrue(driver.findElement(newTAbButton).isDisplayed(), "new Tab button not displayed");
		Assert.assertTrue(driver.findElement(newWindowButton).isDisplayed(), "new window button not displayed");
		Assert.assertTrue(driver.findElement(newWIndowMessageButton).isDisplayed(),
				"new Window message button not displayed");

		driver.findElement(newTAbButton).click();
		Reporter.log("Click on New Tab Button..");

		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tab.get(1));

		Thread.sleep(3000);
		boolean newTabOpened = driver.findElement(titleText).isDisplayed();

		if (newTabOpened) {
			Reporter.log("New Tab Opened Successfully.");
		} else {
			Reporter.log("New Tab Button not working properly.");
		}

		driver.close();

		driver.switchTo().window(tab.get(0));
		Assert.assertEquals(currentTabTitle, title);
		Reporter.log("Back to browser windows tab.");

		// new Windows

		driver.findElement(newWindowButton).click();
		Reporter.log("Click on New Windows Button..");

		ArrayList<String> currentWindow = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(currentWindow.get(1));

		Reporter.log("Switch on New Windows Successfully..");
		Thread.sleep(5000);
		boolean newWindowOpened = driver.findElement(titleText).isDisplayed();

		if (newWindowOpened) {
			Reporter.log("New window Opened Successfully.");
		} else {
			Reporter.log("New Window Button not working properly.");
		}
		driver.close();

		driver.switchTo().window(currentWindow.get(0));
		Assert.assertEquals(currentTabTitle, title);
		Reporter.log("Back to browser windows tab.");

		// new Windows Message

		
		Thread.sleep(5000);
		
	//	String currentTAb = driver.getWindowHandle();
		
//		driver.findElement(newWIndowMessageButton).click();
//		Reporter.log("Click on New Windows Button..");
//
//		Thread.sleep(5000);
//		ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
//		driver.switchTo().window(tab.get(1));
//
//		Thread.sleep(3000);
//		Assert.assertEquals(driver.findElement(newWindowsMessageText).getText(), "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.");
//		Reporter.log("New Window Messsage Displayed..");
//
//		driver.close();
//
//		driver.switchTo().window(tab.get(0));
//		Assert.assertEquals(currentTabTitle, title);
//		Reporter.log("Back to browser windows tab.");
	
//		Set<String> newTab = driver.getWindowHandles();
//		
//		for (String windowHandle : newTab) {
//			Thread.sleep(2000);
//			if(currentTAb.equals(windowHandle)) {
//				System.out.println("main Tab opened");
//			//	driver.close();
//			}
//			else {
//			driver.switchTo().window(windowHandle);
//			System.out.println("switched Tab opened");
//			Assert.assertEquals(driver.findElement(newWindowsMessageText).getText(), "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.");
//			Reporter.log("New Window Messsage Displayed..");			
//			driver.close();
//			}
//		}

//		Reporter.log("Switch on New Windows Successfully..");
		
//		Assert.assertEquals(driver.findElement(newWindowsMessageText).getText(), "Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.");
//		Reporter.log("New Window Messsage Displayed..");
		
//		driver.switchTo().window(currentWindow.get(0));
//		Assert.assertEquals(currentTabTitle, title);
//		Reporter.log("Back to browser windows tab.");
		
		
		
		Reporter.log("Tab, new Windows , new windows message run Successfully..");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
