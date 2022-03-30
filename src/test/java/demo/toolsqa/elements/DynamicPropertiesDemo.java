package demo.toolsqa.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DynamicPropertiesDemo {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://demoqa.com/elements");

		// Buttons

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		
		driver.findElement(By.xpath("//span[contains(.,'Dynamic')]")).click();

		By disableButton = By.cssSelector("button#enableAfter");
		By visibleButton = By.xpath("//button[contains(.,'Visible After 5 Seconds')]");
		By colorChangeButton = By.id("colorChange"); 
		By colorChangeAfter5SecondButton = By.xpath("//button[contains(@class,'text-danger')]");
		
		WebElement enableIn5SecondButton = driver.findElement(disableButton);	
		WebElement colorChange = driver.findElement(colorChangeButton);	
		
		Assert.assertTrue(enableIn5SecondButton.isDisplayed(), "Enable In 5 Second Button not Displayed.");
		Assert.assertFalse(enableIn5SecondButton.isEnabled(), "Enable In 5 Second Button is Enable.");
		Assert.assertTrue(colorChange.isDisplayed(), "Color change Button not Displayed.");
		
		try {
			driver.findElement(visibleButton).isDisplayed();
		}catch(Exception e) {
			System.out.println("Visible After 5 Seconds Button not Displayed.");
		}
		
		Thread.sleep(5000);
		
		WebElement colorChangeAfter5Seconds = driver.findElement(colorChangeAfter5SecondButton);
		WebElement visibleIn5SecondButton = driver.findElement(visibleButton);

		Assert.assertTrue(enableIn5SecondButton.isEnabled(), "Enable In 5 Second Button is Disable.");
		Assert.assertTrue(visibleIn5SecondButton.isDisplayed(), "Visible After 5 Seconds not Displayed.");
		Assert.assertTrue(colorChangeAfter5Seconds.isDisplayed(), "Color change Button not Displayed.");
		

		System.out.println("Test completed Successfully");
		
		driver.quit();
		}
}
