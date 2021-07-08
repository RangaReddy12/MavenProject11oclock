package CommonFunLibrary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
WebDriver driver;
public LoginPage(WebDriver driver)
{
	this.driver=driver;
}
//maintain Repository
@FindBy(name ="txtUsername")
WebElement Enteruser;
@FindBy(xpath ="//input[@id='txtPassword']")
WebElement EnterPass;
@FindBy(name ="Submit")
WebElement LoginBtn;
public void verifyLogin(String username,String password)throws Throwable
{
	driver.get("http://orangehrm.qedgetech.com/");
	driver.manage().window().maximize();
	this.Enteruser.sendKeys(username);
	this.EnterPass.sendKeys(password);
	this.LoginBtn.click();
	Thread.sleep(5000);
}
}
