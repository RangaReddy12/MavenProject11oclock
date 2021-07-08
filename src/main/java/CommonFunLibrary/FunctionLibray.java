package CommonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import constant.PBconstant;

public class FunctionLibray extends PBconstant{
//method for login
	public static boolean verifyLogin(String username,String password)throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("Enter_Username"))).sendKeys(username);
		driver.findElement(By.xpath(config.getProperty("Enter_Password"))).sendKeys(password);
		driver.findElement(By.xpath(config.getProperty("Click_Loginbtn"))).click();
		Thread.sleep(5000);
		String expected ="adminflow";
		String actual = driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Login Success::"+expected+"  "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail::"+expected+"  "+actual,true);
			return false;
		}
	}
	//click on branches
	public static void clickBranches()throws Throwable
	{
	driver.findElement(By.xpath(config.getProperty("Click_Branches"))).click();
	Thread.sleep(3000);
	}
	//method for branch creation
	public static boolean verifynewBranch(String bName,String Address1,String Address2,
	String Address3,String AreaCode,String ZipCode,String Country,String State,String City)
	throws Throwable{
		driver.findElement(By.xpath(config.getProperty("Click_NewBranch"))).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath(config.getProperty("Enter_Branch"))).sendKeys(bName);
		driver.findElement(By.xpath(config.getProperty("Enter_Address1"))).sendKeys(Address1);
		driver.findElement(By.xpath(config.getProperty("Enter_Address2"))).sendKeys(Address2);
		driver.findElement(By.xpath(config.getProperty("Enter_Address3"))).sendKeys(Address3);
		driver.findElement(By.xpath(config.getProperty("Enter_Area"))).sendKeys(AreaCode);
		driver.findElement(By.xpath(config.getProperty("Enter_ZipCode"))).sendKeys(ZipCode);
		new Select(driver.findElement(By.xpath(config.getProperty("Select_Country")))).selectByVisibleText(Country);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath(config.getProperty("Select_state")))).selectByVisibleText(State);
		Thread.sleep(3000);
		new Select(driver.findElement(By.xpath(config.getProperty("Select_City")))).selectByVisibleText(City);
		Thread.sleep(3000);
		driver.findElement(By.xpath(config.getProperty("Click_Submit"))).click();
		Thread.sleep(3000);
		//capture alert message
		String brancmessage =driver.switchTo().alert().getText();
		Thread.sleep(5000);
		driver.switchTo().alert().accept();
		String expected ="new Branch with";
		if(brancmessage.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log(brancmessage,true);
			return true;
		}
		else
		{
			Reporter.log("New branch creation Fail",true);
			return false;
		}
	}
	//method for branch updation
	public static boolean verifyBranchUpdate(String branchname,String address,String zipcode)
	throws Throwable {
		driver.findElement(By.xpath(config.getProperty("Click_Edit"))).click();
		Thread.sleep(4000);
		WebElement branch = driver.findElement(By.xpath(config.getProperty("Edit_Branch")));
		branch.clear();
		branch.sendKeys(branchname);
		Thread.sleep(4000);
		WebElement add = driver.findElement(By.xpath(config.getProperty("Edit_Address")));
		add.clear();
		add.sendKeys(address);
		Thread.sleep(4000);
		WebElement zip = driver.findElement(By.xpath(config.getProperty("Edit_zipcode")));
		zip.clear();
		zip.sendKeys(zipcode);
		Thread.sleep(4000);
		driver.findElement(By.xpath(config.getProperty("Click_UpdateBtn"))).click();
		Thread.sleep(4000);
		String branchupdate =driver.switchTo().alert().getText();
		Thread.sleep(4000);
		driver.switchTo().alert().accept();
		String expected ="Branch updated";
		if(branchupdate.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log(branchupdate,true);
			return true;
		}
		else
		{
			Reporter.log("Fail To update branch",true);
			return false;
		}
	}
	//method for logout
	public static boolean verifyLogout()throws Throwable
	{
		driver.findElement(By.xpath(config.getProperty("Click_Logout"))).click();
		Thread.sleep(5000);
	if(driver.findElement(By.xpath(config.getProperty("Click_Loginbtn"))).isDisplayed())
	{
		Reporter.log("Logout Success",true);
		return true;
	}
	else
	{
		Reporter.log("Logout Fail",true);
		return false;
	}
	}
}

