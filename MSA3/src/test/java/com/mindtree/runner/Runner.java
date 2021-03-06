package com.mindtree.runner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mindtree.pageobjects.HomePageObject;
import com.mindtree.pageobjects.PrivacyPageObject;
import com.mindtree.pageobjects.SearchPageObject;
import com.mindtree.pageobjects.SearchResultPageObject;
import com.mindtree.pageobjects.TravelPageObject;
import com.mindtree.utilities.BaseClass;
import com.mindtree.utilities.ExcelFileRead;
import com.mindtree.utilities.ExtentReporting;
import com.mindtree.utilities.PropertyFileRead;

public class Runner
{
	BaseClass objBase = null;
	WebDriver driver = null;
	ExtentReporting objReport = null;
	String reportPath = "";
	ExcelFileRead objExcel = null;
	PropertyFileRead objProp = null;
	HomePageObject objHome = null;
	TravelPageObject objTravel = null;
	SearchPageObject objSearch = null;
	SearchResultPageObject objSearchResult = null;
	PrivacyPageObject objPrivacy = null;

	@BeforeSuite
	public void makeReport()
	{
		BaseClass.executionFlag = false;
		objReport = new ExtentReporting();
		reportPath = objReport.addExtentReport(reportPath);
	}

	@BeforeMethod
	public void initObjects()
	{
		objBase = new BaseClass();
		objExcel = new ExcelFileRead();
		objProp = new PropertyFileRead();
		objHome = new HomePageObject();
		objTravel = new TravelPageObject();
		objSearch = new SearchPageObject();
		objSearchResult = new SearchResultPageObject();
		objPrivacy = new PrivacyPageObject();
	}

	// Some test cases sometimes work sometimes not.Even after using waits
	@Test // (enabled=false)
	public void clickTravelVerify()
	{
		BaseClass.ts = BaseClass.ext.createTest("Click on Travel link and Verify");
		driver = objBase.initialize(5);
		objBase.navigateUrl(driver, objProp.getFromProperty("Configuration", "Url"));
		objHome.clickOnTravel(driver);
		objTravel.verifyTravelText(driver, objExcel.fetchCellData(2, 'D'));
	}

	@Test // (enabled=false)
	public void searchTextVerify()
	{
		BaseClass.ts = BaseClass.ext.createTest("Search text in search box and verify");
		driver = objBase.initialize(5);
		objBase.navigateUrl(driver, objProp.getFromProperty("Configuration", "Url"));
		objHome.clickSearchBox(driver);
		objSearch.enterIntoSearchBox(driver, objExcel.fetchCellData(2, 'B'));
		objSearchResult.searchTextVerify(driver, objExcel.fetchCellData(2, 'C'));
	}

	@Test // (enabled=false)
	public void clickPrivacyVerify()
	{
		BaseClass.ts = BaseClass.ext.createTest("Click Privacy Policy link and verify");
		driver = objBase.initialize(5);
		objBase.navigateUrl(driver, objProp.getFromProperty("Configuration", "Url"));
		objHome.clickFooterPrivacyLink(driver);
		objPrivacy.verifyPromiseText(driver, objExcel.fetchCellData(2, 'E'));
	}

	@AfterMethod
	public void reset()
	{
		BaseClass.executionFlag = false;
		BaseClass.ext.flush();
	}

}
