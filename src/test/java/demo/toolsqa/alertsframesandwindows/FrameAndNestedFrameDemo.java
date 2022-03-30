package demo.toolsqa.alertsframesandwindows;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FrameAndNestedFrameDemo {

	WebDriver driver;
	String text = "This is a sample page";

	@BeforeClass
	public void setup() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/frames");
		
	}

	@Test(description = "verify frame Demo work successfully.")
	public void Frame() throws Exception {

		By frameLabel = By.xpath("//div[@class='main-header']");
		By frame1 = new By.ById("frame1");
		By frame2 = new By.ById("frame2");
		By frame1Text = new By.ByCssSelector("h1");
		By frame2Text = new By.ById("sampleHeading");

		Assert.assertEquals(driver.findElement(frameLabel).getText(), "Frames");
		driver.switchTo().frame(driver.findElement(frame1));
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(frame1Text).getText(), text);
		Reporter.log("frame1 switched Successfully..");

		Thread.sleep(2000);
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");

		Thread.sleep(5000);

		driver.switchTo().frame(driver.findElement(frame2));
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(frame2Text).getText(), text);
		Reporter.log("frame2 switched Successfully..");

	}

	@Test(description = "verify Nested frame Demo work successfully.")
	public void NestedFrame() throws Exception {

		driver.get("https://demoqa.com/nestedframes");

		By nestedFrameLabel = By.xpath("//div[@class='main-header']");
		By parentframe = new By.ByXPath("//div[@id='frame1Wrapper']/iframe");
		By childFrame = new By.ByXPath("//body[contains(.,'Parent frame')]/iframe");
		By FrameText = new By.ByTagName("body");

		Assert.assertEquals(driver.findElement(nestedFrameLabel).getText(), "Nested Frames");

		driver.switchTo().frame(driver.findElement(parentframe));
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(FrameText).getText(), "Parent frame");
		Reporter.log("Parent Frame switched Successfully..");

		Thread.sleep(2000);

		driver.switchTo().frame(driver.findElement(childFrame));
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(FrameText).getText(), "Child Iframe");
		Reporter.log("childFrame switched Successfully..");

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
