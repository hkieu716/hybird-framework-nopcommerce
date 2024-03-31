package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class RegisterPageObject extends BasePageFactory{
	private WebDriver driver;
	
	public RegisterPageObject(WebDriver driver) {
		//biến local
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="FirstName")
	private WebElement firstNameTextBox;
	
	@FindBy(id="LastName")
	private WebElement lastNameTextBox;
	
	@FindBy(id="Email")
	private WebElement emailTextBox;
	
	@FindBy(id="Password")
	private WebElement passwordTextBox;
	
	@FindBy(xpath="//input[@id='ConfirmPassword']")
	private WebElement confirmPasswordTextBox;
	
	@FindBy(xpath="//button[@id='register-button']")
	private WebElement registerButton;

	@FindBy(xpath="//span[@id='FirstName-error']")
	private WebElement firstNameErrorMessage;
	
	@FindBy(xpath="//span[@id='LastName-error']")
	private WebElement lastNameErrorMessage;
	
	@FindBy(xpath="//span[@id='Email-error']")
	private WebElement emailErrorMessage;
	
	@FindBy(xpath="//span[@id='Password-error']")
	private WebElement passwordErrorMessage;
	
	@FindBy(xpath="//span[@id='ConfirmPassword-error']")
	private WebElement confirmPasswordErrorMessage;
	
	@FindBy(xpath="//div[@class='result']")
	private WebElement registerSuccessMessage;
	
	@FindBy(xpath="//div[contains(@class,'message-error')]//li")
	private WebElement existingEmailErrorMessage;

	//PageObject/Action
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}

	public String getErrorMessageAtFirstnameTextbox() {
		waitForElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getErrorMessageAtLastnameTextbox() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getErrorMessageAtPasswordTextbox() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getErrorMessageAtConfirmPasswordTextbox() {
		waitForElementVisible(driver, confirmPasswordErrorMessage);
		return getElementText(driver, confirmPasswordErrorMessage);
	}

	public void inputToFirstnameTextbox(String firstName) {
		waitForElementVisible(driver, firstNameTextBox);
		sendkeyToElement(driver, firstNameTextBox, firstName);
		
	}

	public void inputToLastnameTextbox(String lastName) {
		waitForElementVisible(driver, lastNameTextBox);
		sendkeyToElement(driver, lastNameTextBox, lastName);
		
	}

	public void inputToEmailTextbox(String emailAddress) {
		waitForElementVisible(driver, emailTextBox);
		sendkeyToElement(driver, emailTextBox, emailAddress);
		
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextBox);
		sendkeyToElement(driver, passwordTextBox, password);
		
	}

	public void inputToConfirmPasswordTextbox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextBox);
		sendkeyToElement(driver, confirmPasswordTextBox, confirmPassword);
		
	}

	public String getRegisterSuccesMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}

	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}

}
