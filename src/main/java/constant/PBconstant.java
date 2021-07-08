package constant;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class PBconstant {
public static WebDriver driver;
public static Properties config;
@BeforeMethod
public void setUp()throws Throwable
{
	config = new Properties();
	config.load(new FileInputStream("D:\\1130Selenium\\OrangrHRM_Maven\\PropertyFile\\Environment.properties"));
	if(config.getProperty("Browser").equalsIgnoreCase("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", "D:\\1130Selenium\\OrangrHRM_Maven\\CommonDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(config.getProperty("Url"));
	}
	else if(config.getProperty("Browser").equalsIgnoreCase("firefox"))
	{
		System.setProperty("webdriver.gecko.driver", "D:\\1130Selenium\\OrangrHRM_Maven\\CommonDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get(config.getProperty("Url"));
	}
	else
	{
		Reporter.log("Browser value is Not Matching",true);
	}
}
@AfterMethod
public void tearDown()
{
	driver.close();
}
}
