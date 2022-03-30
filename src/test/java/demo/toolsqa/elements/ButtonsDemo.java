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

public class ButtonsDemo {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://demoqa.com/elements");

		// Buttons

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		
		driver.findElement(By.xpath("//div[@class='element-list collapse show']//span[contains(.,'Buttons')]")).click();

		By doubleClickButton = By.cssSelector("button[id=\"doubleClickBtn\"]");
		By rightClickButton = By.xpath("(//button[@Class=\"btn btn-primary\"])[2]");
		By clickMeButton = By.xpath("(//button[contains(.,'Click Me')])[3]");
		
		WebElement doubleClick = driver.findElement(doubleClickButton);
		WebElement rightClick = driver.findElement(rightClickButton);
		WebElement clickMe = driver.findElement(clickMeButton);
	
		Thread.sleep(3000);
		Actions action = new Actions(driver);
		action.doubleClick(doubleClick).build().perform();		
		WebElement doubleClickMessage = driver.findElement(By.xpath("//p[@id=\"doubleClickMessage\"]"));
		Assert.assertTrue(doubleClickMessage.isDisplayed(), "Double Click message not Displayed.");
		Thread.sleep(2000);
		
		action.contextClick(rightClick).perform();
		WebElement rightClickMessage = driver.findElement(By.xpath("//p[@id=\"rightClickMessage\"]"));
		Assert.assertTrue(rightClickMessage.isDisplayed(), "Right Click message not Displayed.");
		Thread.sleep(2000);
		
		action.click(clickMe).perform();
		WebElement clickMeMessage = driver.findElement(By.xpath("//p[@id=\"dynamicClickMessage\"]"));
		Assert.assertTrue(clickMeMessage.isDisplayed(), "Click Me message not Displayed.");
		Thread.sleep(2000);

		System.out.println("Test completed Successfully");
		
		driver.quit();
		}
}
