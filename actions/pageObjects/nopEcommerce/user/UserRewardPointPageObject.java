package pageObjects.nopEcommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.nopEcommerce.user.RewardPointPageUI;

public class UserRewardPointPageObject extends BasePage{
	WebDriver driver;
	
	public UserRewardPointPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
