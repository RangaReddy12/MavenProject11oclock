package DriverFactory;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunLibrary.FunctionLibray;
import Utilities.ExcelFileUtil;
import constant.PBconstant;

public class AppTest extends PBconstant{
	String inputpath ="D:\\1130Selenium\\OrangrHRM_Maven\\TestInput\\HybridTest.xlsx";
	String outputpath = "D:\\1130Selenium\\OrangrHRM_Maven\\TestOutPut\\HybridResults.xlsx";
	String TCSheet ="TestCases";
	String TSSheet ="TestSteps";
	ExtentReports report;
	ExtentTest test;
	@Test
	public void starTest()throws Throwable
	{
		report= new ExtentReports("./Reports/Hybrid.html");
		boolean res=false;
		String tcres ="";
		//access excel methods
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no f rows in TCSheet
		int TCCount =xl.rowCount(TCSheet);
		//count no f rows in TSSheet
		int TSCount =xl.rowCount(TSSheet);
		Reporter.log("No of rows in TCSheet::"+TCCount+"   "+"No of rows in TSSheet::"+TSCount,true);
		//iterate all rows in TCSheet
		for(int i=1;i<=TCCount; i++)
		{
			test= report.startTest("HybridTest");
			//read execute cell
			String execute = xl.getCellData(TCSheet, i, 2);
			if(execute.equalsIgnoreCase("Y"))
			{
				//read tcid and store
				String tcid = xl.getCellData(TCSheet, i, 0);
				//iterate all rows in TSSheet
				for(int j=1; j<=TSCount;j++)
				{
					//read description cell
					String Description =xl.getCellData(TSSheet, j, 2);
					//read tsid cell from TSSheet
					String tsid =xl.getCellData(TSSheet, j, 0);
					if(tcid.equalsIgnoreCase(tsid))
					{
						//read keyword cell from TSSheet
						String keyword =xl.getCellData(TSSheet, j, 4);
						//call methods
						if(keyword.equalsIgnoreCase("AdminLogin"))
						{
							String username = xl.getCellData(TSSheet, j, 5);
							String password = xl.getCellData(TSSheet, j, 6);
							res =FunctionLibray.verifyLogin(username, password);
							test.log(LogStatus.INFO, Description);
						}
						else if(keyword.equalsIgnoreCase("NewBranchCreation"))
						{
							String bname = xl.getCellData(TSSheet, j, 5);
							String Address1 = xl.getCellData(TSSheet, j, 6);
							String Address2 = xl.getCellData(TSSheet, j, 7);
							String Address3 = xl.getCellData(TSSheet, j, 8);
							String Area = xl.getCellData(TSSheet, j, 9);
							String zipcode = xl.getCellData(TSSheet, j, 10);
							String country = xl.getCellData(TSSheet, j, 11);
							String state = xl.getCellData(TSSheet, j, 12);
							String city = xl.getCellData(TSSheet, j, 13);
							FunctionLibray.clickBranches();
							res =FunctionLibray.verifynewBranch(bname, Address1, Address2, Address3, Area, zipcode, country, state, city);
							test.log(LogStatus.INFO, Description);
						}
						else if(keyword.equalsIgnoreCase("UpdateBranch"))
						{
							String branchname =xl.getCellData(TSSheet, j, 5);
							String address =xl.getCellData(TSSheet, j, 6);
							String zipcode =xl.getCellData(TSSheet, j, 10);
							FunctionLibray.clickBranches();
							res =FunctionLibray.verifyBranchUpdate(branchname, address, zipcode);
							test.log(LogStatus.INFO, Description);
						}
						else if(keyword.equalsIgnoreCase("AdminLogout"))
						{
							res =FunctionLibray.verifyLogout();
							test.log(LogStatus.INFO, Description);
						}
						String tsres ="";
						if(res)
						{
							tsres ="Pass";
							//write as pass into result cell
							xl.setCellData(TSSheet, j, 3, tsres, outputpath);
							test.log(LogStatus.PASS, Description);
						}
						else
						{
							tsres="Fail";
							//write as fail into result cell
							xl.setCellData(TSSheet, j, 3, tsres, outputpath);
							test.log(LogStatus.FAIL, Description);
						}
						tcres=tsres;
					}
					report.endTest(test);
					report.flush();
				}
				//write as pass or fail into TCSheet Results cell
				xl.setCellData(TCSheet, i, 3, tcres, outputpath);
			}
			else
			{
				//which are flaged N should write blocked in TCSheet
				xl.setCellData(TCSheet, i, 3, "Blocked", outputpath);
			}
		}
	}
}
