package pageObjects.nopEcommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopEcommerce.admin.AdminDashboardPageUI;
import pageUIs.nopEcommerce.admin.AdminLoginPageUI;

public class AdminDashboardPageObject extends BasePage{
	WebDriver driver;
	
	public AdminDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean isDashboardHeaderDisplay() {
		waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
	}
	
}