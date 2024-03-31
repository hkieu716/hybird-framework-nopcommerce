package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory{
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="Email")
	private WebElement emailTextbox;
	
	@FindBy(id="Password")
	private WebElement passwordTextBox;
	
	@FindBy(xpath="//button[contains(@class,'login-button')]")
	private WebElement loginButton;
	
	@FindBy(xpath="//span[@id='Email-error']")
	private WebElement emailErrorMessage;
	
	@FindBy(xpath="//div[contains(@class,'validation-summary-errors')]")
	private WebElement unsuccessfullErrorMessage;
	
	//PageObject/Action
	
	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementClickable(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public void inputToEmailTexbox(String invalidEmail) {
		waitForElementClickable(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, invalidEmail);
	}

	public String getErrorMessageUnsuccessfull() {
		waitForElementClickable(driver, unsuccessfullErrorMessage);
		return getElementText(driver, unsuccessfullErrorMessage);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementClickable(driver, passwordTextBox);
		sendkeyToElement(driver, passwordTextBox, password);
	}
}
