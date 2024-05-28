package pageObjects.nopEcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopEcommerce.user.CustomerInfoPageUI;
import pageUIs.nopEcommerce.user.HomePageUI;

public class UserCustomerInfoPageObject extends BasePage {
	WebDriver driver;
	
	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToNewsletterCheckbox() {
		// TODO Auto-generated method stub
		
	}

	public boolean isCustomerInfoPageDisplay() {
		waitForElementVisible(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
		return isElementDisplayed(driver, CustomerInfoPageUI.CUSTOMER_INFO_HEADER);
	}

}
