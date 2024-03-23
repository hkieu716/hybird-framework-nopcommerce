package com.nopcommerce.user;

import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_04_Multiple_Browser extends BaseTest{
	private WebDriver driver;
	private String firstName, lastName;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		
		firstName = "Kieu";
		lastName = "Huyen";
		homePage = new HomePageObject(driver);
		driver.manage().window().maximize();
	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("HomePage - step01: Click to Register link");
		homePage.clickToRegisterLink();

		registerPage = new RegisterPageObject(driver);

		System.out.println("RegisterPage - step02: Click to Register link");
		registerPage.clickToRegisterButton();

		System.out.println("RegisterPage - step03: Verify error message");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		System.out.println("HomePage - step01: Click to Register link");
		homePage.clickToRegisterLink();

		// Click register link -> nhảy sang trang register
		registerPage = new RegisterPageObject(driver);
		System.out.println("RegisterPage - step02: input the require fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox("123@456#.com");
		registerPage.inputToPasswordTextbox("123456");
		registerPage.inputToConfirmPasswordTextbox("123456");

		System.out.println("RegisterPage - step03: Click to Register link");
		registerPage.clickToRegisterButton();

		System.out.println("RegisterPage - step04: Verify error message");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");

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
