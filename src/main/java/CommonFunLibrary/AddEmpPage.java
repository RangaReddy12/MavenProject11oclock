package CommonFunLibrary;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

public class AddEmpPage {
WebDriver driver;
public AddEmpPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(id="menu_pim_viewPimModule")
WebElement pimClick;
@FindBy (id="btnAdd")
WebElement clickAdd;
@FindBy(name="firstName")
WebElement EnterFname;
@FindBy(name="lastName")
WebElement EnterLname;
@FindBy(id="btnSave")
WebElement ClickSaveBtn;
@FindBy(xpath="/html[1]/body[1]/div[1]/div[3]/div[1]/div[1]/div[1]/h1[1]")
WebElement empText;
public String verifyAddEmp(String fname,String lname)
{
	String res="";
	String firstn="";
	String lastn="";
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	Actions ac = new Actions(driver);
	ac.moveToElement(pimClick).click().perform();
	ac.moveToElement(clickAdd).click().perform();
	this.EnterFname.sendKeys(fname);
	 firstn=EnterFname.getAttribute("value");
	this.EnterLname.sendKeys(lname);
	 lastn=EnterLname.getAttribute("value");
	ac.moveToElement(this.ClickSaveBtn).click().perform();
	String expected=firstn+" "+lastn;
	String message =this.empText.getText();
	Reporter.log(expected,true);
	if(message.equalsIgnoreCase(expected))
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
