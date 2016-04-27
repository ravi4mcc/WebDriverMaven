package webdriverMaven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class LoginTest 
{
	private static final String SECONDS = null;
	public static WebDriver driver;
	
	
	public void setUp()
	{
		driver = new Chromedriver();
	}
	
	@Test
	public void doLogin()
	{
		driver = new Chromedriver();
		driver.get("http://gmail.com");
		
		driver.findElement(By.id("Email")).sendKeys("ravi4mcc@gmail.com");
		/*driver.findElement(By.id("SignIn")).click();
		driver.findElement(By.id("Passwd")).sendKeys("Meher");*/
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@AfterSuite
	public void close()
	{
		driver.close();
	}
}
