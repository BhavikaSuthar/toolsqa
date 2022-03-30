package demo.toolsqa.elements;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.channel.MessageSizeEstimator.Handle;

public class LinksDemo {

	public static void main(String[] args) throws InterruptedException {

		By linkTab = By.xpath("//span[contains(.,'Link')]");
		By linkLabel = By.xpath("//div[@class='main-header']");
		By HomeLink = By.linkText("Home");
		By toolsqaImage = By.xpath("//img[contains(@src,'Toolsqa')]");
		By notFoundLink = By.partialLinkText("Found");
		
		WebDriver driver;
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.navigate().to("https://demoqa.com/elements");

		//linkTab
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)", "");

		driver.findElement(linkTab).click();
		Thread.sleep(3000);
		
		String label = driver.findElement(linkLabel).getText();
		Assert.assertEquals(label, "Links");
		
		driver.findElement(HomeLink).click();
		Thread.sleep(3000);
		boolean newTabOpened = driver.findElement(toolsqaImage).isDisplayed();
		
		if(newTabOpened) {
			System.out.println("New Tab Opened Successfully");
		}else {
			System.out.println("link not working properly");
		}

		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	    
		js.executeScript("window.scrollBy(0,300)", "");

	    driver.findElement(notFoundLink).click();
	    Thread.sleep(3000);
	    String response = driver.findElement(By.cssSelector("p[id='linkResponse']")).getText();
		System.out.println(response);
		Assert.assertTrue(response.contains("404") && response.contains("Not Found"), "Displayed Checked Result not contain error message ");
		
		System.out.println("Test completed Successfully");
		
		Thread.sleep(3000);
		
		//broken Link- images
		js.executeScript("window.scrollBy(0,300)", "");
		
		By brokenLinkTmagesTab = By.xpath("//span[contains(.,'Broken Link')]");
		By validImage = By.xpath("(//img[contains(@src,'Toolsqa.jpg')])[2]");
		By brokenImage = By.xpath("//img[contains(@src,'Toolsqa_1.jpg')]");
		By validLink = By.linkText("Click Here for Valid Link");
		By inValidLink = By.partialLinkText("Broken Link");
		By statusCodeError = By.tagName("h3");
		
		driver.findElement(brokenLinkTmagesTab).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(validImage).isDisplayed(), "Valid Image not Displayed");
		Assert.assertTrue(driver.findElement(brokenImage).isDisplayed(), "Broken Image not Displayed");
		
		driver.findElement(validLink).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(toolsqaImage).isDisplayed(), "Valid link not working properly");	
		driver.navigate().back();
		
		js.executeScript("window.scrollBy(0,300)", "");
		Thread.sleep(3000);
		driver.findElement(inValidLink).click();	
		Thread.sleep(2000);
		response = driver.findElement(statusCodeError).getText();
		Assert.assertEquals(response, "Status Codes");
		driver.navigate().back();
		Thread.sleep(3000);
		
		System.out.println("Test completed Successfully");
		
		driver.close();
	}

}
