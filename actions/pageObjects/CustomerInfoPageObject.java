package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.CustomerInfoPageUI;
import pageUIs.HomePageUI;

public class CustomerInfoPageObject extends BasePage {
	WebDriver driver;
	
	public CustomerInfoPageObject(WebDriver driver) {
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
