package com.nopcommerce.user;

import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Level_03_Page_Object{ 
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private String projectPath = System.getProperty("user.dir");
	
  @BeforeClass
  public void beforeClass() {
	  System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
	  driver = new ChromeDriver();
	  homePage = new HomePageObject(driver);
	  registerPage = new RegisterPageObject(driver);	
	  firstName = "Kieu";
	  lastName = "Huyen";
	  emailAddress = "kieuhuyen" + gererateFakeNumber() + "@gmail.vn";
	  password = "123456";
	  
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
	 registerPage.clickToRegisterButton();
	 
//	 waitForElementClickable(driver, "//button[@id='register-button']");
//	 clickToElement(driver, "//button[@id='register-button']");
	 
	  System.out.println("RegisterPage - step03: Verify error message");
	  Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");
	  Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");  
  } 
  	
  @Test
  public void TC_02_Register_Invalid_Email() {
	 System.out.println("HomePage - step01: Click to Register link");
	 homePage.clickToRegisterLink();
	 
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
  
  @Test
  public void TC_03_Register_Success() {
	 System.out.println("HomePage - step01: Click to Register link");
	 homePage.clickToRegisterLink();
	 
	 System.out.println("RegisterPage - step02: input the require fields");
	 registerPage.inputToFirstnameTextbox(firstName);
	 registerPage.inputToLastnameTextbox(lastName);
	 registerPage.inputToEmailTextbox(emailAddress);
	 registerPage.inputToPasswordTextbox(password);
	 registerPage.inputToConfirmPasswordTextbox(password);
	  
	 System.out.println("RegisterPage - step03: Click to Register link");
	 registerPage.clickToRegisterButton();
	 
	 System.out.println("RegisterPage - step04: Verify success message displayed");
	 Assert.assertEquals(registerPage.getRegisterSuccesMessage(), "Your registration completed");
	 
  } 
  
  @Test
  public void TC_04_Register_Existing_Email() {
	  System.out.println("HomePage - step01: Click to Register link");
	  homePage.clickToRegisterLink();
	  
	  System.out.println("RegisterPage - step02: input the require fields");
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputToLastnameTextbox(lastName);
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox(password);
	  registerPage.inputToConfirmPasswordTextbox(password);
		 
	  System.out.println("RegisterPage - step03: Click to Register link");
	  registerPage.clickToRegisterButton();
	  
	  System.out.println("RegisterPage - step04: Verify success message displayed");
	  Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");
  }  
  
  @Test
  public void TC_05_Register_Password_Les_Than_6_Chars() {
	  System.out.println("HomePage - step01: Click to Register link");
	  homePage.clickToRegisterLink();
	  
	  System.out.println("RegisterPage - step02: input the require fields");
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputToLastnameTextbox("Huyen");
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox("123");
	  registerPage.inputToConfirmPasswordTextbox("123");
	  
	  System.out.println("RegisterPage - step03: Click to Register link");
	  registerPage.clickToRegisterButton();
	  
	  System.out.println("RegisterPage - step04: Verify error message displayed");
	  Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");
  }   
  
  @Test
  public void TC_06_Register_Invalid_Confirm_Password() {
	  System.out.println("HomePage - step01: Click to Register link");
	  homePage.clickToRegisterLink();
	  
	  System.out.println("RegisterPage - step02: input the require fields");
	  registerPage.inputToFirstnameTextbox(firstName);
	  registerPage.inputToLastnameTextbox("Huyen");
	  registerPage.inputToEmailTextbox(emailAddress);
	  registerPage.inputToPasswordTextbox(password);
	  registerPage.inputToConfirmPasswordTextbox("123789");
	  
	  System.out.println("RegisterPage - step03: Click to Register link");
	  registerPage.clickToRegisterButton();
	  
	  System.out.println("RegisterPage - step04: Verify error message displayed");
	  Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");  

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
  
}
