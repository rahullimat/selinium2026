package jenkin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class JenkinsSetup {
	
WebDriver driver;
String ExpextedUrl = "https://o2.openmrs.org/openmrs/login.htm";
    @Test (priority =1)	
    public void setup()
	{
    	WebDriverManager.chromedriver().setup();
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("--remote-allow-origins=*");
    	driver = new ChromeDriver(options);
	}

	@Test (priority =2)
	public void launchApplication() throws InterruptedException
	{
	driver.manage().window().maximize();	
	driver.get("https://o2.openmrs.org/openmrs/login.htm");
	}
	
	@Test(priority = 3)
	public void dashBoardValidation() throws IOException, InterruptedException {
		Thread.sleep(4000);
		String currentURL = driver.getCurrentUrl();
		if (currentURL.equals("https://o2.openmrs.org/openmrs/login.htm")) {
			System.out.println("Login successful for: ");
		} else {
			System.out.println("Login failed for: ");
		}
	Assert.assertEquals(currentURL, ExpextedUrl);
	}
	
	@Test (priority =4, dependsOnMethods = { "launchApplication" })
	public void login() throws InterruptedException
	{
	driver.findElement(By.id("username")).sendKeys("admin");
	driver.findElement(By.name("password")).sendKeys("Admin123");
	driver.findElement(By.id("Registration Desk")).click();
	driver.findElement(By.id("loginButton")).click();
	}
	
	@Test (priority =5)
	public void addPatient() throws InterruptedException
	{
	Thread.sleep(2000);	
	driver.findElement(By.id("referenceapplication-registrationapp-registerPatient-homepageLink-referenceapplication-registrationapp-registerPatient-homepageLink-extension")).click();
	driver.findElement(By.name("givenName")).sendKeys("Arun");
	driver.findElement(By.name("middleName")).sendKeys("M");
	driver.findElement(By.name("familyName")).sendKeys("Kumar");
	driver.findElement(By.id("next-button")).click();
	driver.findElement(By.xpath("//*[@id=\"gender-field\"]/option[1]")).click();
	driver.findElement(By.id("next-button")).click();
	driver.findElement(By.id("birthdateYears-field")).sendKeys("27");
	driver.findElement(By.id("birthdateMonths-field")).sendKeys("7");
	driver.findElement(By.id("next-button")).click();
	driver.findElement(By.id("address1")).sendKeys("new street.");
	driver.findElement(By.id("address2")).sendKeys("surandai");
	driver.findElement(By.id("cityVillage")).sendKeys("tenkasi");
	driver.findElement(By.id("stateProvince")).sendKeys("TamilNadu");
	driver.findElement(By.id("country")).sendKeys("india"); 
	driver.findElement(By.cssSelector("#postalCode")).sendKeys("1256376");
	driver.findElement(By.id("next-button")).click();
	driver.findElement(By.name("phoneNumber")).sendKeys("23456889");
	driver.close();			
 }
	
	
}
