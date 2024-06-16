package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObjects.nopEcommerce.user.UserAddressPageObject;
import pageObjects.nopEcommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopEcommerce.user.UserHomePageObject;
import pageObjects.nopEcommerce.user.UserLoginPageObject;
import pageObjects.nopEcommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopEcommerce.user.UserRegisterPageObject;
import pageObjects.nopEcommerce.user.UserRewardPointPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_07_Switch_Page extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		driver.get("https://demo.nopcommerce.com/");
		firstName = "Kieu";
		lastName = "Huyen";
		emailAddress = "kieuhuyen" + gererateFakeNumber() + "@gmail.vn";
		validPassword = "123456";

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@Test
	public void User_01_Register() {
		registerPage = homePage.clickToRegisterLink();
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(emailAddress);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccesMessage(), "Your registration completed");
		
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.clickToLoginLink();

		loginPage.inputToEmailTexbox(emailAddress);
		loginPage.inputToPasswordTextbox(validPassword);
		
		homePage = loginPage.clickToLoginButton();	
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());
	}

	@Test
	public void User_03_Customer_Info() {
		customerInfoPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInfoPage.isCustomerInfoPageDisplay());
	}

	@Test
	public void User_04_Switch_Page() {
		//customer info -> address
		addressPage = customerInfoPage.openAddressPage(driver);
		
		//address -> My Product Review
		myProductReviewPage = addressPage.openMyProductReviewPage(driver);
		
		//My product review -> Reward Point
		rewardPointPage = myProductReviewPage.openRewardPoint(driver);
		
		//Reward Point -> Address
		addressPage = rewardPointPage.openAddressPage(driver);
		
		//Address -> Reward Point
		rewardPointPage = addressPage.openRewardPoint(driver);
		
		//Reward Point -> My product review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);
		
		//My product review -> address
		addressPage = myProductReviewPage.openAddressPage(driver);
			
		customerInfoPage = addressPage.openCustomerInfoPage(driver);
		
		myProductReviewPage = customerInfoPage.openMyProductReviewPage(driver);
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	private WebDriver driver;
	private String firstName, lastName, emailAddress, validPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private UserCustomerInfoPageObject customerInfoPage;
	private UserAddressPageObject addressPage;
	private UserMyProductReviewPageObject myProductReviewPage;
	private UserRewardPointPageObject rewardPointPage;

}
