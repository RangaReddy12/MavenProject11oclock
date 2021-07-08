package CommonFunLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
WebDriver driver;
public LogoutPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(id="welcome")
WebElement clickwelcome;
@FindBy(xpath="//a[contains(text(),'Logout')]")
WebElement clicklogout;
public void verifyLogout()throws Throwable
{
	
	this.clickwelcome.click();
	Thread.sleep(4000);
	this.clicklogout.click();
	Thread.sleep(4000);
}
}
