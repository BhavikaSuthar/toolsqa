package demo.toolsqa.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RadioButtonDemo {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://demoqa.com/elements");

		// RadioButton

		driver.findElement(By.xpath("//span[contains(.,'Radio Button')]")).click();

		WebElement noButtonEnabled = driver.findElement(By.xpath("//label[@for='noRadio']"));

		if (noButtonEnabled.isSelected()) {
			System.out.println("No button is Selected");
		} else {
			System.out.println("No Button is not Selected");
			WebElement yesButton = driver.findElement(By.xpath("//label[@for='yesRadio']"));	
			yesButton.click();
		}

		Thread.sleep(2000);
		String result = driver.findElement(By.tagName("p")).getText();
		System.out.println(result);
		Assert.assertTrue(result.contains("Yes"), "Selected radio button Result not contain Yes");
		Thread.sleep(3000);
		
		System.out.println("Test completed Successfully");

		driver.quit();
		}
}
