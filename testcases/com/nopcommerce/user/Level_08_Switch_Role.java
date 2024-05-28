package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObjects.nopEcommerce.admin.AdminDashboardPageObject;
import pageObjects.nopEcommerce.admin.AdminLoginPageObject;
import pageObjects.nopEcommerce.user.UserHomePageObject;
import pageObjects.nopEcommerce.user.UserLoginPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_08_Switch_Role extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		userEmailAddress = "kieuhuyen124@gmail.vn";
		userValidPassword = "123456";
		adminEmailAddress = "admin@yourstore.com";
		adminValidPassword = "admin";

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@Test
	public void Role_01_User() {
		userLoginPage = userHomePage.clickToLoginLink();
		
		// login as user role
		userHomePage = userLoginPage.loginAsUser(userEmailAddress, userValidPassword);
	
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplay());
	}

	@Test
	public void Role_02_Admin() {
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		//Login as Admin role
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmailAddress, adminValidPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplay());
		//49:50
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	private WebDriver driver;
	private String userEmailAddress, userValidPassword, adminEmailAddress, adminValidPassword;
	private UserHomePageObject userHomePage;
	private AdminLoginPageObject adminLoginPage;
	private UserLoginPageObject userLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
}
