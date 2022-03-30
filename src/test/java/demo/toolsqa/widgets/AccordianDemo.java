package demo.toolsqa.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AccordianDemo {

	WebDriver driver;
	String text;

	@BeforeClass
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/accordian");
	}

	@Test(description = "verify Accordian Demo work successfully.")
	public void ModelDialogs() throws Exception {

		By smallModelButton = new By.ById("showSmallModal");
		By largeModelButton = new By.ById("showLargeModal");
		By cancelButton = new By.ByXPath("//span[contains(.,'×')] ");
		By closeButton = new By.ByCssSelector("button#closeLargeModal");
		By smallModelHeaderText= new By.ByXPath("//div[@class='modal-title h4'][contains(.,'Small Modal')]");
		By largeModelHeaderText= new By.ByXPath("//div[@class='modal-title h4'][contains(.,'Large Modal')]");

		Assert.assertTrue(driver.findElement(smallModelButton).isDisplayed(), "small Model button not displayed");
		Assert.assertTrue(driver.findElement(largeModelButton).isDisplayed(), "large Model button not displayed");
	
		driver.findElement(smallModelButton).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(smallModelHeaderText).isDisplayed());
		driver.findElement(cancelButton).click();
		Reporter.log("small model work Successfully..");
		
		Thread.sleep(5000);
		
		driver.findElement(largeModelButton).click();
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(largeModelHeaderText).isDisplayed());
		driver.findElement(closeButton).click();
		Reporter.log("Large model work Successfully..");		
	
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
