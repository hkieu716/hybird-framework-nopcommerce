package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.RewardPointPageUI;

public class RewardPointPageObject extends BasePage{
	WebDriver driver;
	
	public RewardPointPageObject(WebDriver driver) {
		this.driver = driver;
	}
}
