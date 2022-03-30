package demo.toolsqa.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTableDemo {

	public static void main(String[] args) throws InterruptedException {

		
		By editButton = By.xpath("(//span[@title=\"Edit\"])[4]");
		By salaryEditText = By.id("salary");
		By submitButton = By.id("submit");
		By searchEditText = By.cssSelector("input[placeholder=\"Type to search\"]");
		By deleteButton = By.cssSelector("span[title=\"Delete\"]");
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://demoqa.com/elements");

		// webTable

		driver.findElement(By.xpath("//span[contains(.,'Web')]")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("addNewRecordButton")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("firstName")).sendKeys("Harry");
		driver.findElement(By.xpath("//input[@placeholder=\"Last Name\"]")).sendKeys("Potter");
		driver.findElement(By.id("userEmail")).sendKeys("Harry@email.com");
		driver.findElement(By.cssSelector("input[id='age']")).sendKeys("20");
		driver.findElement(salaryEditText).sendKeys("100000");
		driver.findElement(By.id("department")).sendKeys("Magic");
		driver.findElement(submitButton).click();
		
		Thread.sleep(2000);
		
		WebElement addedRowDetails = driver.findElement(By.xpath("//div[@role=\"row\"][contains(.,'Harry') and contains (.,'Magic')]"));
		Assert.assertTrue(addedRowDetails.isDisplayed(), "Added row details not Displayed");
		
		driver.findElement(editButton).click();
		Thread.sleep(2000);
		
		driver.findElement(salaryEditText).clear();
		driver.findElement(salaryEditText).sendKeys("4000000");
		driver.findElement(submitButton).click();
		Thread.sleep(2000);
		
		WebElement updatedRowDetails = driver.findElement(By.xpath("//div[@role=\"row\"][contains(.,'Harry') and contains (.,'4000000')]"));
		Assert.assertTrue(updatedRowDetails.isDisplayed(), "updated Salary details not Displayed");
		
		driver.findElement(searchEditText).sendKeys("Harry");
		Thread.sleep(2000);
		driver.findElement(deleteButton).click();
		Thread.sleep(2000);
		driver.findElement(searchEditText).clear();
		
		try {
			driver.findElement(By.xpath("//div[@role=\"row\"][contains(.,'Harry') and contains (.,'Magic')]")).isDisplayed();
		}catch(Exception e) {
			System.out.println("Row Not Found");
		}
		
		
		System.out.println("Test completed Successfully");
		
		driver.quit();
		}
}
