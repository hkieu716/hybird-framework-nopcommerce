package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_06_Page_Generator_Manager_I extends BaseTest {
	private WebDriver driver;
	private String firstName, lastName, invalidEmail, notFoundEmail, existingEmail, invalidPassword, validPassword;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = new HomePageObject(driver);

		driver.get("https://demo.nopcommerce.com/");
		firstName = "Kieu";
		lastName = "Huyen";
		invalidEmail = "huyen@123.@vn";
		notFoundEmail = "hienhien" + gererateFakeNumber() + "@gmail.com";
		existingEmail = "kieuhuyen" + gererateFakeNumber() + "@gmail.vn";
		validPassword = "123456";
		invalidPassword = "4534533";

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		System.out.println("Pre-condition - step01: Click to Register link");
		homePage.clickToRegisterLink();

		// Click register link -> nhảy sang trang register
		registerPage = new RegisterPageObject(driver);

		System.out.println("Pre-condition - step02: Input the require fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(validPassword);
		registerPage.inputToConfirmPasswordTextbox(validPassword);

		System.out.println("Pre-condition - step03: Click to Register link");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition - step04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccesMessage(), "Your registration completed");
	}

	@Test
	public void Login_01_Empty_Data() {
		homePage.clickToLoginLink();

		// Từ trang home -> click login link -> login page
		loginPage = new LoginPageObject(driver);

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();

		// Từ trang home -> click login link -> login page
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(invalidEmail);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter a valid email address.");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		homePage.clickToLoginLink();

		// Từ trang home -> click login link -> login page
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(notFoundEmail);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		homePage.clickToLoginLink();

		// Từ trang home -> click login link -> login page
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(existingEmail);
		loginPage.inputToPasswordTextbox("");

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {
		homePage.clickToLoginLink();

		// Từ trang home -> click login link -> login page
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(existingEmail);
		loginPage.inputToPasswordTextbox(invalidPassword);

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");
	}

	@Test
	public void Login_06_Valid_Email_Password() {
		homePage.clickToLoginLink();

		// Từ trang home -> click login link -> login page
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTexbox(existingEmail);
		loginPage.inputToPasswordTextbox(validPassword);
		
		loginPage.clickToLoginButton();
		
		//login thành công -> homepage
		homePage = new HomePageObject(driver);
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
