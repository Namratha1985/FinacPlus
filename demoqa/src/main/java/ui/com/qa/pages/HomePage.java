package ui.com.qa.pages;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.com.qa.util.TestBase;

public class HomePage extends TestBase{
	
	private WebDriver  driver;
	
	@FindBy(xpath = "//h5[text()='Book Store Application']")
	WebElement bookStoreTile;
	
	@FindBy(id = "login")
	WebElement loginButton;
		
	//Initializing page objects
	public HomePage(WebDriver  driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public LoginPage navigateToBookStoreApp() {
		pageDownAction();		
		try {
			 waitForElementToBeClickable(bookStoreTile);
			if (isElementPresent(bookStoreTile)) {
				bookStoreTile.click();		
			}
		} catch (ElementClickInterceptedException e) {
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", bookStoreTile);
		}
		waitForElementToBeClickable(loginButton);
		if (isElementPresent(loginButton)) {
			loginButton.click();		
		}
		return new LoginPage(driver);
	
	}

}
