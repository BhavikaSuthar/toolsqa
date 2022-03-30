package demo.toolsqa.alertsframesandwindows;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AlertsDemo {

	WebDriver driver;
	String text;
	String name = "Harry" ; 

	@BeforeClass
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/alerts");
	}

	@Test(description = "verify Alert Demo work successfully.")
	public void BrowserWindows() throws Exception {

		By alertButton = new By.ById("alertButton");
		By timerAlertButton = new By.ById("timerAlertButton");
		By confirmButton = new By.ByCssSelector("button#confirmButton");
		By promptButton = new By.ByCssSelector("#promtButton");
		By confirmResultText= new By.ByXPath("//span[@id='confirmResult'][contains(.,\"Cancel\")]");
		By promptResultText= new By.ByXPath("//span[@id='promptResult'][contains(.,\"Harry\")]");

		Assert.assertTrue(driver.findElement(alertButton).isDisplayed(), "alert button not displayed");
		Assert.assertTrue(driver.findElement(timerAlertButton).isDisplayed(), "timer Alert button not displayed");
		Assert.assertTrue(driver.findElement(confirmButton).isDisplayed(), "confirm button not displayed");
		Assert.assertTrue(driver.findElement(promptButton).isDisplayed(), "prompt button not displayed");
		
		driver.findElement(alertButton).click();
		Alert alert = driver.switchTo().alert();
		Thread.sleep(5000);
		text = alert.getText();
		Assert.assertEquals("You clicked a button", text);
		alert.accept();
		Reporter.log("simple alert accepted Successfully..");
		
		Thread.sleep(5000);
		driver.findElement(timerAlertButton).click();
		Thread.sleep(5000);
		alert = driver.switchTo().alert();
		text = alert.getText();
		Assert.assertEquals("This alert appeared after 5 seconds", text);
		alert.accept();
		Reporter.log("simple alert accepted Successfully..");
		

		Thread.sleep(5000);
		driver.findElement(confirmButton).click();
		alert = driver.switchTo().alert();
		text = alert.getText();
		Assert.assertEquals("Do you confirm action?", text);
		alert.dismiss();
		Assert.assertTrue(driver.findElement(confirmResultText).isDisplayed(), "You selected cancel text not displayed");
		Reporter.log("confirm alert dissmissed Successfully..");
		
		Thread.sleep(5000);
		driver.findElement(promptButton).click();
		alert = driver.switchTo().alert();
		alert.sendKeys(name);
		alert.accept();
		Assert.assertTrue(driver.findElement(promptResultText).isDisplayed(), "You entered Harry text not displayed");
		Reporter.log(" Your prompt alert entered  text accepted Successfully..");
		
	
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
