package CommonFunLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddUserPage {
WebDriver driver;
public AddUserPage(WebDriver driver)
{
	this.driver=driver;
	
}
@FindBy(id ="menu_admin_viewAdminModule")
WebElement AdminClick;
@FindBy(id ="menu_admin_UserManagement")
WebElement usermngntClick;
@FindBy(id ="menu_admin_viewSystemUsers")
WebElement usersClick;
@FindBy(id ="btnAdd")
WebElement ClickaddBtn;
@FindBy(xpath="//input[@id='systemUser_employeeName_empName']")
WebElement EnterEname;
@FindBy(xpath="//input[@id='systemUser_userName']")
WebElement EnterUsername;
@FindBy(xpath="//input[@id='systemUser_password']")
WebElement Enterpassword;
@FindBy(xpath="//input[@id='systemUser_confirmPassword']")
WebElement Entercpassword;
@FindBy(xpath="//input[@id='btnSave']")
WebElement clickSaveBtn;
public String verifyAddUser(String ename,String username,String password,String cpassword)throws Throwable
{
	String res="";
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	Actions ac = new Actions(driver);
	ac.moveToElement(AdminClick).perform();
	ac.moveToElement(usermngntClick).perform();
	ac.moveToElement(usersClick).click().perform();
	ac.moveToElement(this.ClickaddBtn).click().perform();
	this.EnterEname.sendKeys(ename);
	this.EnterUsername.sendKeys(username);
	this.Enterpassword.sendKeys(password);
	this.Entercpassword.sendKeys(cpassword);
	ac.moveToElement(this.clickSaveBtn).click().perform();
	Thread.sleep(5000);
	String expected ="viewSystemUsers";
	String actual =driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		res="Pass";
	}
	else
	{
		res="Fail";
	}
	return res;
}
}
