package demo.toolsqa.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UploadDownloadDemo {

	public static void main(String[] args) throws InterruptedException {
		
		By uploadDownloadLabel = By.xpath("//div[@class='main-header']");
		By labelUpload =By.xpath("(//div[contains(.,'Download')])[13]/div[2]//label");
		By uploadButton =By.xpath("(//div[contains(.,'Download')])[13]/div[2]//input");
				//By.cssSelector("input[class=\"form-control-file\"]");
		By downloadButton = By.linkText("Download");
				//By.cssSelector("button[id=\"downloadButton\"]");
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://demoqa.com/elements");

		// Upload&Download Buttons

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		
		driver.findElement(By.xpath("//span[contains(.,'Upload and Download')]")).click();
		
		String label = driver.findElement(uploadDownloadLabel).getText();
		Assert.assertEquals(label, "Upload and Download");				
		
		WebElement upload = driver.findElement(uploadButton);
		WebElement download = driver.findElement(downloadButton);
		WebElement labelText = driver.findElement(labelUpload);
		
		Assert.assertTrue(upload.isDisplayed(), "upload Button not Displayed.");
		Assert.assertTrue(download.isDisplayed(), "download Button not Displayed.");
		Thread.sleep(2000);		
		download.click();
		   
		driver.navigate().to("https://demo.guru99.com/test/upload/");
		
		By uploadImage  = By.xpath("//input[@type='file']");
		By acceptTermchk = By.className("field_check");
		By submitButton = By.id("submitbutton");
		
		WebElement uploadSampleImage = driver.findElement(uploadImage);
		WebElement acceptTerm = driver.findElement(acceptTermchk);
		WebElement submit = driver.findElement(submitButton);
		
//		Thread.sleep(3000);
//		uploadSampleImage.click();
//		Thread.sleep(3000);
//		uploadSampleImage.sendKeys("‪E:\\Download\\sampleFile.jpeg");
		Thread.sleep(5000);
		acceptTerm.click();
		Thread.sleep(3000);
		submit.click();
		Thread.sleep(3000);
		
		System.out.println("Test completed Successfully");
		
		driver.quit();
		}
}
