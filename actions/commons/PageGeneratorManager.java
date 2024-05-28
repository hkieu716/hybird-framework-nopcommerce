package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.nopEcommerce.admin.AdminDashboardPageObject;
import pageObjects.nopEcommerce.admin.AdminLoginPageObject;
import pageObjects.nopEcommerce.user.UserAddressPageObject;
import pageObjects.nopEcommerce.user.UserCustomerInfoPageObject;
import pageObjects.nopEcommerce.user.UserHomePageObject;
import pageObjects.nopEcommerce.user.UserLoginPageObject;
import pageObjects.nopEcommerce.user.UserMyProductReviewPageObject;
import pageObjects.nopEcommerce.user.UserRegisterPageObject;
import pageObjects.nopEcommerce.user.UserRewardPointPageObject;

public class PageGeneratorManager {
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	
	public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
		return new UserLoginPageObject(driver);
	}
	
	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	
	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}
	
	public static UserAddressPageObject getUserAddressPage(WebDriver driver) {
		return new UserAddressPageObject(driver);
	}
	
	public static UserMyProductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
		return new UserMyProductReviewPageObject(driver);
	}
	
	public static UserRewardPointPageObject getUserRewardPointPage(WebDriver driver) {
		return new UserRewardPointPageObject(driver);
	}
	
	public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPageObject(driver);
	}
	
	public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPageObject(driver);
	}
}
