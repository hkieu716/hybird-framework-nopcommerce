package com.nopcommerce.user;

import org.testng.annotations.Test;

import pageObjects.nopEcommerce.user.UserHomePageObject;
import pageObjects.nopEcommerce.user.UserLoginPageObject;
import pageObjects.nopEcommerce.user.UserRegisterPageObject;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object_02 {
	private WebDriver driver;
	private String firstName, lastName, invalidEmail, notFoundEmail, existingEmail, invalidPassword, validPassword;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLoginPageObject loginPage;
	private String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		homePage = new UserHomePageObject(driver);

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
		registerPage = new UserRegisterPageObject(driver);

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
		loginPage = new UserLoginPageObject(driver);

		loginPage.clickToLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		homePage.clickToLoginLink();

		// Từ trang home -> click login link -> login page
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTexbox(invalidEmail);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		homePage.clickToLoginLink();

		// Từ trang home -> click login link -> login page
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTexbox(notFoundEmail);

		loginPage.clickToLoginButton();

		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(),
				"Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		homePage.clickToLoginLink();

		// Từ trang home -> click login link -> login page
		loginPage = new UserLoginPageObject(driver);
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
		loginPage = new UserLoginPageObject(driver);
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
		loginPage = new UserLoginPageObject(driver);
		loginPage.inputToEmailTexbox(existingEmail);
		loginPage.inputToPasswordTextbox(validPassword);
		
		loginPage.clickToLoginButton();
		
		//login thành công -> homepage
		homePage = new UserHomePageObject(driver);
		
		Assert.assertTrue(homePage.isMyAccountLinkDisplay());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int gererateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}
}
