package demo.toolsqa.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TextBoxDemo {	

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://demoqa.com/elements");

		// textbox

		driver.findElement(By.xpath("//li[contains(.,'Text Box')]")).click();
		driver.findElement(By.id("userName")).sendKeys("Harry Potter");
		driver.findElement(By.xpath("//input[@placeholder = \"name@example.com\"]")).sendKeys("Harry@email.com");
		driver.findElement(By.cssSelector("textarea[placeholder='Current Address']")).sendKeys("Hogwarts");
		driver.findElement(By.cssSelector("textarea[id='permanentAddress']")).sendKeys("Hogwarts");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		
		driver.findElement(By.id("submit")).click();
		Thread.sleep(3000);
		
		WebElement output= driver.findElement(By.id("output"));
		System.out.println(output.getText());
		
		System.out.println("Test completed Successfully");

		driver.quit();
	}

}
