package demo.toolsqa.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckBoxDemo {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://demoqa.com/elements");

		// Checkbox

		driver.findElement(By.xpath("//span[contains(.,'Check Box')]")).click();

		driver.findElement(By.cssSelector("button[title='Expand all']")).click();
		Thread.sleep(3000);

		WebElement documentCheckBox = driver.findElement(By.xpath("//label[span='Documents']"));

		if (documentCheckBox.isSelected()) {
			System.out.println("Documents checkbox is Selected");
		} else {
			System.out.println("Documents checkbox Not Selected");
			driver.findElement(By.xpath("//label[span='Documents']")).click();
			Thread.sleep(2000);
			WebElement officeCheckBox = driver.findElement(By.xpath("//label[span='Office']"));
			officeCheckBox.click();
			Boolean isOfficeCheckBoxChecked = officeCheckBox.isSelected();
			Assert.assertFalse(isOfficeCheckBoxChecked, "Office CheckBox is not selected");
		}

		Thread.sleep(2000);
		driver.findElement(By.cssSelector("button[title='Collapse all']")).click();
		Thread.sleep(3000);
		String result = driver.findElement(By.id("result")).getText();
		System.out.println(result);
		Assert.assertTrue(result.contains("workspace"), "Displayed Checked Result not contain workspace ");
		Thread.sleep(3000);

		System.out.println("Test completed Successfully");
		
		driver.quit();
	}

}
