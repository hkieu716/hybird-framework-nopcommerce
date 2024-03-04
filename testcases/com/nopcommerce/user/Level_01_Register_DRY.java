package com.nopcommerce.user;

import org.testng.annotations.Test;
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

public class Level_01_Register_DRY{ 
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
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#FirstName-error")).getText(), "First name is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#LastName-error")).getText(), "Last name is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Email is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Password-error")).getText(), "Password is required.");
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#ConfirmPassword-error")).getText(), "Password is required.");
  } 
  
  @Test
  public void TC_02_Register_Invalid_Email() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Kieu");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("Huyen");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys("123@456#.com");
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("span#Email-error")).getText(), "Wrong email");
	  
  } 
  
  @Test
  public void TC_03_Register_Success() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Kieu");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("Huyen");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");
	 
  } 
  
  @Test
  public void TC_04_Register_Existing_Email() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Kieu");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("Huyen");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123456");
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("div.message-error li")).getText(), "The specified email already exists");
  } 
  
  @Test
  public void TC_05_Register_Password_Les_Than_6_Chars() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Kieu");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("Huyen");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123");
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("#Password-error")).getText(), "Password must meet the following rules:\n"+ "must have at least 6 characters");
  } 
  
  @Test
  public void TC_06_Register_Invalid_Confirm_Password() {
	  driver.findElement(By.cssSelector("a.ico-register")).click();
	  
	  driver.findElement(By.cssSelector("input#FirstName")).sendKeys("Kieu");
	  driver.findElement(By.cssSelector("input#LastName")).sendKeys("Huyen");
	  driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
	  driver.findElement(By.cssSelector("input#Password")).sendKeys("123456");
	  driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys("123457");
	  
	  driver.findElement(By.cssSelector("button#register-button")).click();
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("#ConfirmPassword-error")).getText(), "The password and confirmation password do not match.");
  } 
  
  @AfterClass
  public void afterClass() {
  }
  
  //tạo fake số để làm email
  public int gererateFakeNumber() {
	  Random rand = new Random();
	  return rand.nextInt(9999);
  }

}
