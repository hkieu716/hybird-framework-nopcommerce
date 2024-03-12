package com.nopcommerce.user;

import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object{ 
	WebDriver driver;
	String emailAddress;
	String projectPath = System.getProperty("user.dir");
	By logoutButton = By.cssSelector("a.ico-logout");
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  
	  emailAddress = "kieuhuyen" + gererateFakeNumber() + "@gmail.vn";
	  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	  driver.get("https://demo.nopcommerce.com/");
	  driver.manage().window().maximize();
  }

  @Test
  public void TC_01_Register_Empty_Data() {
	 System.out.println("HomePage - step01: Click to Register link");
	 homePage.clickToRegisterLink();
	 
//	 waitForElementClickable(driver, "//a[@class='ico-register']");
//	 clickToElement(driver, "//a[@class='ico-register']");
	 
	 System.out.println("RegisterPage - step02: Click to Register link");
	 registerPage.clickToRegisterLink();
	 
//	 waitForElementClickable(driver, "//button[@id='register-button']");
//	 clickToElement(driver, "//button[@id='register-button']");
	 
	  System.out.println("RegisterPage - step03: Verify error message");
	  Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");  
	  
//	  Assert.assertEquals(getElementText(driver, "//span[@id='FirstName-error']"), "First name is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='LastName-error']"), "Last name is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Email is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='Password-error']"), "Password is required.");
//	  Assert.assertEquals(getElementText(driver, "//span[@id='ConfirmPassword-error']"), "Password is required.");
  } 
  	
  @Test
  public void TC_02_Register_Invalid_Email() {
//	 waitForElementClickable(driver, "//a[@class='ico-register']");
//	 clickToElement(driver, "//a[@class='ico-register']");
	 System.out.println("HomePage - step01: Click to Register link");
	 homePage.clickToRegisterLink();
	 
	 System.out.println("RegisterPage - step02: input the require fields");
	 registerPage.inputToFirstnameTextbox("Kieu");
	 registerPage.inputToLastnameTextbox("Huyen");
	 registerPage.inputToEmailTextbox("123@456#.com");
	 registerPage.inputToPasswordTextbox("123456");
	 registerPage.inputToConfirmPasswordTextbox("123456");
	 
//	 sendkeyToElement(driver, "//input[@id='FirstName']", "Kieu");
//	 sendkeyToElement(driver, "//input[@id='LastName']", "Huyen");
//	 sendkeyToElement(driver, "//input[@id='Email']", "123@456#.com");
//	 sendkeyToElement(driver, "//input[@id='Password']", "123456");
//	 sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
//	 waitForElementClickable(driver, "//button[@id='register-button']");
//	 clickToElement(driver, "//button[@id='register-button']");
	 
	 System.out.println("RegisterPage - step03: Click to Register link");
	 registerPage.clickToRegisterLink();
	  
//	  Assert.assertEquals(getElementText(driver, "//span[@id='Email-error']"), "Wrong email");
	  System.out.println("RegisterPage - step04: Verify error message");
	  Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "Wrong email");
	  
  } 
  
  @Test
  public void TC_03_Register_Success() {
//	 waitForElementClickable(driver, "//a[@class='ico-register']");
//	 clickToElement(driver, "//a[@class='ico-register']");
	  
	 System.out.println("HomePage - step01: Click to Register link");
	 homePage.clickToRegisterLink();
	 
	 System.out.println("RegisterPage - step02: input the require fields");
	 registerPage.inputToFirstnameTextbox("Kieu");
	 registerPage.inputToLastnameTextbox("Huyen");
	 registerPage.inputToEmailTextbox(emailAddress);
	 registerPage.inputToPasswordTextbox("123456");
	 registerPage.inputToConfirmPasswordTextbox("123456");
	 
//	 sendkeyToElement(driver, "//input[@id='FirstName']", "Kieu");
//	 sendkeyToElement(driver, "//input[@id='LastName']", "Huyen");
//	 sendkeyToElement(driver, "//input[@id='Email']", emailAddress);
//	 sendkeyToElement(driver, "//input[@id='Password']", "123456");
//	 sendkeyToElement(driver, "//input[@id='ConfirmPassword']", "123456");
	  
	 System.out.println("RegisterPage - step03: Click to Register link");
	 registerPage.clickToRegisterLink();
	 
//	 waitForElementClickable(driver, "//button[@id='register-button']");
//	 clickToElement(driver, "//button[@id='register-button']");
	  
	 System.out.println("RegisterPage - step04: Verify success message displayed");
	 Assert.assertEquals(registerPage.getRegisterSuccesMessage(), "Your registration completed");
//	 Assert.assertEquals(getElementText(driver, "//div[@class='result']"), "Your registration completed");
	 
  } 
  
  @Test
  public void TC_04_Register_Existing_Email() {
	  System.out.println("HomePage - step01: Click to Register link");
	  homePage.clickToRegisterLink();
	  
	  System.out.println("RegisterPage - step02: input the require fields");
	  registerPage.inputToFirstnameTextbox("Kieu");
	  registerPage.inputToLastnameTextbox("Huyen");
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox("123456");
	  registerPage.inputToConfirmPasswordTextbox("123456");
		 
	  System.out.println("RegisterPage - step03: Click to Register link");
	  registerPage.clickToRegisterLink();
	  
	  System.out.println("RegisterPage - step04: Verify success message displayed");
	  Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
  }  
  
  @Test
  public void TC_05_Register_Password_Les_Than_6_Chars() {
	  System.out.println("HomePage - step01: Click to Register link");
	  homePage.clickToRegisterLink();
	  
	  System.out.println("RegisterPage - step02: input the require fields");
	  registerPage.inputToFirstnameTextbox("Kieu");
	  registerPage.inputToLastnameTextbox("Huyen");
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox("123");
	  registerPage.inputToConfirmPasswordTextbox("123");
	  
	  System.out.println("RegisterPage - step03: Click to Register link");
	  registerPage.clickToRegisterLink();
	  
	  System.out.println("RegisterPage - step04: Verify error message displayed");
	  Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\\nmust have at least 6 characters");
  }   
  
  @Test
  public void TC_06_Register_Invalid_Confirm_Password() {
	  System.out.println("HomePage - step01: Click to Register link");
	  homePage.clickToRegisterLink();
	  
	  System.out.println("RegisterPage - step02: input the require fields");
	  registerPage.inputToFirstnameTextbox("Kieu");
	  registerPage.inputToLastnameTextbox("Huyen");
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox("123456");
	  registerPage.inputToConfirmPasswordTextbox("123789");
	  
	  System.out.println("RegisterPage - step03: Click to Register link");
	  registerPage.clickToRegisterLink();
	  
	  System.out.println("RegisterPage - step04: Verify error message displayed");
	  Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");  
//	  học đến topic 5: 1:15:34
  } 

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
  
  //tạo fake số để làm email
  public int gererateFakeNumber() {
	  Random rand = new Random();
	  return rand.nextInt(9999);
  }	

  HomePageObject homePage;
  RegisterPageObject registerPage;	
}
